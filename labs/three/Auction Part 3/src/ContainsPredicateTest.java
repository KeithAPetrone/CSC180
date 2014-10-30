import org.junit.Test;


public class ContainsPredicateTest
{
	@Test
	public void test()
	{
		InMemoryAuctionService service = new InMemoryAuctionService();
		service.create(new Auction(123, "Car", 5, "Don't drive me"));
		service.create(new Auction(456, "Automatic Car", 20, "Don't drive me either"));
		service.create(new Auction(789, "Automatic", 1, "What?"));
		service.create(new Auction(789, "Pizza", 4, "Mmmm"));
		service.create(new Auction(789, "Pepperoni", 2, "Tasty"));
		service.create(new Auction(789, "Pepperoni Pizza", 100, "The Best!"));
		Auction[] list;
		
		list = service.search("Automatic AND Car OR Pepperoni AND Pizza");
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
			{
				System.out.println(list[i].toString());
			}
		}
	}
}
