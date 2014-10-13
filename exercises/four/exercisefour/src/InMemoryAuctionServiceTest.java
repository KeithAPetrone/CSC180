import org.junit.Test;


public class InMemoryAuctionServiceTest {

	@Test
	public void test() {
		InMemoryAuctionService service = new InMemoryAuctionService();
		Auction item = new Auction(123, "Shoe", 25);
		service.create(item);
		System.out.println(service.retreive(123).toString());
		service.bid("Keith", 123);
		System.out.println(service.retreive(123).toString());
		Auction[] thing = service.search("sHoE");
		for (Auction auction : thing) {
			if (auction != null)
			{
				System.out.println(auction.toString());
			}			
		}
		item = new Auction(123, "Cactus", 50);
		service.update(item, 123);
		System.out.println(service.retreive(123).toString());
		service.delete(123);
		try
		{
			System.out.println(service.retreive(123).toString());
		}
		catch(Exception e)
		{
			System.out.println("Delete was successful");
		}
		System.out.println(item.equals(item));
		if (item.equals(item))
		{
			System.out.println("Yes!");
		}
		else
		{
			System.out.println("No!");
		}
		System.out.println(item.equals(new Auction(456, "Chicken Wing", 999)));
		if (item.equals(new Auction(456, "Chicken Wing", 999)))
		{
			System.out.println("Yes!");
		}
		else
		{
			System.out.println("No!");
		}
		System.out.println(item.hashCode());
	}

}
