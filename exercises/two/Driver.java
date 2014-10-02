
public class Driver
{
	public static void main(String[] args)
	{
		InMemoryAuctionService service = new InMemoryAuctionService();
		
		DisplayAuction(service);
		//Attempt a bid on a nonexisting item.
		service.bid("Keith", 321);
		DisplayAuction(service);
		//Successful bid on Dirty Shoes
		service.bid("Joe", 123);
		DisplayAuction(service);
		
		//Search for Dirty Shoe and print it
		AuctionItem[] list = service.search("Dirty Shoe");
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				System.out.println(list[i].toString());
			}
		}
		
		//Check if two items are equal
		System.out.println(service.firstItem.equals(service.secondItem));
		System.out.println(service.firstItem.equals(service.firstItem));
		System.out.println(service.firstItem.equals(service.thirdItem));
	}
	
	public static void DisplayAuction(InMemoryAuctionService service)
	{
		System.out.println(service.firstItem.toString());
		System.out.println(service.secondItem.toString());
		System.out.println(service.thirdItem.toString());
	}
}
