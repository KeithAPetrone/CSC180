import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class InMemoryAuctionService implements AuctionService
{
	CollectionUtils collectionUtils = new CollectionUtils();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Auction[] search(java.lang.String criteria)
	{
		List<Auction> temp = new ArrayList<Auction>(map.values());
		String[] criteriaArray = criteria.split(" ");
		@SuppressWarnings("rawtypes")
		Stack<Predicate> predicateStack = new Stack<Predicate>();
		Stack<String> operatorStack = new Stack<String>();
		for (int i = 0; i < criteriaArray.length; i++)
		{
			if (criteriaArray[i].contains("OR"))
			{
				if (!predicateStack.peek().getClass().equals(new AndPredicate(new ContainsPredicate(criteria), new ContainsPredicate(criteria)).getClass()))
				{
					operatorStack.add("OR");
				}
				else
				{
					predicateStack.add(new AndPredicate(predicateStack.pop(), predicateStack.pop()));
					operatorStack.pop();
				}
			}
			else if (criteriaArray[i].contains("AND"))
			{
				operatorStack.add("AND");
			}
			else
			{
				predicateStack.add(new ContainsPredicate(criteriaArray[i]));
			}
		}
		while (!operatorStack.isEmpty())
		{
			if (operatorStack.pop().equals("AND"))
			{
				predicateStack.add(new AndPredicate(predicateStack.pop(), predicateStack.pop()));
			}
			if (!operatorStack.isEmpty())
			{
				if (operatorStack.pop().equals("OR"))
				{
					predicateStack.add(new OrPredicate(predicateStack.pop(), predicateStack.pop()));
				}
			}
		}
		Object[] tempArray = (collectionUtils.filter(temp, predicateStack.pop())).toArray();
		Auction[] auctionArray = new Auction[tempArray.length];
		for (int i = 0; i < tempArray.length; i++)
		{
			auctionArray[i] = (Auction) tempArray[i];
		}
		return auctionArray;
	}

	public Auction bid(java.lang.String username, java.lang.Integer id)
	{
		if (username == null || id == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		Auction item = null;
		if (map.get(id) != null)
		{
			map.get(id).setCurrentBid(map.get(id).getCurrentBid() + 1);
			map.get(id).setOwner(username);
			item = map.get(id);
		}
		else
		{
			throw new ObjectNotFoundException();
		}
		return item;
	}

	public void create(Auction item)
	{
		if (map.get(item.getId()) == null)
		{
			map.put(item.getId(), item);
		}
	}
	
	public void delete(java.lang.Integer id)
	{
		if (map.get(id) != null)
		{
			map.remove(map.remove(id));
		}
	}
	
	public Auction retreive(java.lang.Integer id)
	{
		Auction item = null;
		if (map.get(id) != null)
		{
			item = map.get(id);
		}
		else
		{
			throw new ObjectNotFoundException();
		}
		return item;
	}
	
	public Auction update(Auction item, java.lang.Integer id)
	{
		if (id != item.getId())
		{
			throw new IdMismatchException();
		}
		if (map.get(id) != null)
		{
			map.remove(id);
			map.put(id, item);
			item = map.get(id);
		}
		return item;
	}
}
