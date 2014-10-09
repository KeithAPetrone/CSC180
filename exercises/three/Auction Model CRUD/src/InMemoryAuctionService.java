import java.util.ArrayList;


public class InMemoryAuctionService implements AuctionService
{
	public AuctionItem[] search(java.lang.String criteria)
	{
		AuctionItem[] tempList = new AuctionItem[map.size()];
		ArrayList<AuctionItem> list = new ArrayList<AuctionItem>(map.values());
		tempList = (AuctionItem[]) list.toArray(new AuctionItem[list.size()]);
		for (int i = 0; i < tempList.length; i++)
		{
			if (tempList[i].getName().toLowerCase().contains(criteria.toLowerCase()))
			{
				tempList[i] = tempList[i];
			}
		}
		return tempList;
	}

	public AuctionItem bid(java.lang.String username, java.lang.Integer id)
	{
		if (username == null || id == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		AuctionItem item = null;
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

	public void create(AuctionItem item)
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
	
	public AuctionItem retreive(java.lang.Integer id)
	{
		AuctionItem item = null;
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
	
	public AuctionItem update(AuctionItem item, java.lang.Integer id)
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
