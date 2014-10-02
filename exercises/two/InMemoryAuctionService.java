
public class InMemoryAuctionService implements AuctionService
{
	@SuppressWarnings("null")
	public AuctionItem[] search(String criteria)
	{
		AuctionItem[] list = new AuctionItem[3];
		if (criteria == null)
		{
			return list;
		}		
		if (criteria == firstItem.getName())
		{
			if (list != null)
			{
				list[0] = firstItem;
			}
			else 
			{
				list[list.length+1] = firstItem;
			}
		}
		if (criteria == secondItem.getName())
		{
			if (list != null)
			{
				list[0] = secondItem;
			}
			else 
			{
				list[list.length+1] = secondItem;
			}
		}
		if (criteria == thirdItem.getName())
		{
			if (list != null)
			{
				list[0] = thirdItem;
			}
			else 
			{
				list[list.length+1] = thirdItem;
			}
		}
		return list;
	}

	public void bid(String username, int itemId)
	{
		String s = null;
		try { 
			  s = (String)Integer.toString(itemId);
			}
			catch (NumberFormatException e)
			{
			  return;
			}
		if (username == null)
		{
			return;
		}
		if (itemId == firstItem.getId())
		{
			firstItem.setCurrentBid(firstItem.getCurrentBid()+1);
			firstItem.setOwner(username);
		}
		else if (itemId == secondItem.getId())
		{
			secondItem.setCurrentBid(secondItem.getCurrentBid()+1);
			secondItem.setOwner(username);
		}
		else if (itemId == thirdItem.getId())
		{
			thirdItem.setCurrentBid(thirdItem.getCurrentBid()+1);
			thirdItem.setOwner(username);
		}
	}
	
	AuctionItem firstItem = new AuctionItem(123, "Dirty Shoe", 5);
	AuctionItem secondItem = new AuctionItem(456, "Game Boy", 20);
	AuctionItem thirdItem = new AuctionItem(789, "Cardboard Box", 50);
}
