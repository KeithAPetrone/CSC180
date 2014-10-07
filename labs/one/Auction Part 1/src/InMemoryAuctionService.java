
public class InMemoryAuctionService implements AuctionService
{
	AuctionItem[] list = new AuctionItem[3];
	
	public InMemoryAuctionService()
	{
		list[0] = firstItem;
		list[1] = secondItem;
		list[2] = thirdItem;
	}
	
	@SuppressWarnings("null")
	public AuctionItem[] search(String criteria)
	{
		AuctionItem[] tempList = new AuctionItem[3];	
		for (int i = 0; i < list.length; i++)
		{
			if (list[i].getName().toLowerCase().contains(criteria.toLowerCase()))
			{
				tempList[i] = list[i];
			}
		}
		return tempList;
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
		for (int i = 0; i < list.length; i++)
		{
			if (list[i].getId() == itemId)
			{
				list[i].setCurrentBid(list[i].getCurrentBid() + 1);
				list[i].setOwner(username);
			}
		}
	}
	
	AuctionItem firstItem = new AuctionItem(123, "Dirty Shoe", 5);
	AuctionItem secondItem = new AuctionItem(456, "Game Boy", 20);
	AuctionItem thirdItem = new AuctionItem(789, "Cardboard Box", 50);
}
