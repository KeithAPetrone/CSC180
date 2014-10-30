import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InMemoryAuctionService implements AuctionService
{
	public InMemoryAuctionService()
	{
		
	}
	
	public InMemoryAuctionService(Path p)
	{
		byte[] b = null;
		try {
			b = Files.readAllBytes(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = new String(b);
		//Regex for pulling information
		
		//ID
		// [0-9]+</li>
		
		//Name
		// "img" alt="(?:[^\\"]+|\\.)*"
		
		//Date
		// Get it on or before <b>[a-zA-Z0-9,?.? ?]+
		
		//Number of bids
		// <span>+[0-9]+ bid
	
		//Current Bid
		// [$]+[0-9]+.+ 
		
		String ID = "";
		String Name = "";
		String Description = "car";
		String Date = "";
		String NumOfBids = "";
		String CurrentBid = "";
		
		String[] stringArray = text.split("<li>\n\t\t\tItem: ");
		
		for (int i = 1; i < stringArray.length; i++)
		{
			Pattern pattern = Pattern.compile("[0-9]+</li>");
			Matcher matcher = pattern.matcher(ID = stringArray[i]);
			if (matcher.find()) {
			    ID = (matcher.group(1));
			}
			pattern = Pattern.compile("\"img\" alt=\"(?:[^\\\"]+|\\.)*\"");
			matcher = pattern.matcher(ID = stringArray[i]);
			if (matcher.find()) {
			    Name = (matcher.group(1));
			}
			pattern = Pattern.compile("Get it on or before <b>[a-zA-Z0-9,?.? ?]+");
			matcher = pattern.matcher(ID = stringArray[i]);
			if (matcher.find()) {
			    Date = (matcher.group(1));
			}
			pattern = Pattern.compile("<span>+[0-9]+ bid");
			matcher = pattern.matcher(ID = stringArray[i]);
			if (matcher.find()) {
			    NumOfBids = (matcher.group(1));
			}
			pattern = Pattern.compile("[$]+[0-9]+.+ ");
			matcher = pattern.matcher(ID = stringArray[i]);
			if (matcher.find()) {
			    CurrentBid = (matcher.group(1));
			}
			Date date = null;
			try {
				date = 	new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(Date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.create(new Auction(Integer.parseInt(ID), Name, Integer.parseInt(CurrentBid), Description, date, Integer.parseInt(NumOfBids)));
		}
	}
	
	CollectionUtils collectionUtils = new CollectionUtils();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Auction[] search(java.lang.String criteria)
	{
		List<Auction> temp = new ArrayList<Auction>(map.values());
		String[] criteriaArray = criteria.split(" ");
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
				while (predicateStack.peek().getClass().equals(new AndPredicate(new ContainsPredicate(criteria), new ContainsPredicate(criteria)).getClass()))
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
			String operator = operatorStack.pop();
			if (operator.equals("AND"))
			{
				predicateStack.add(new AndPredicate(predicateStack.pop(), predicateStack.pop()));
			}
			if (!operatorStack.isEmpty())
			{
				if (operator.equals("OR"))
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
