import java.util.ArrayList;
import java.util.List;


public class InMemoryAuctionService implements AuctionService
{
	CollectionUtils collectionUtils = new CollectionUtils();
	
	public Auction[] search(java.lang.String criteria)
	{
		List<Auction> temp = new ArrayList<Auction>(map.values());
		return (collectionUtils.filter(temp, new ContainsPredicate(criteria))).toArray(new Auction[temp.size()]);
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
