import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class InMemoryAuctionServiceTest {

	@Test
	public void test() {
		InMemoryAuctionService service = new InMemoryAuctionService();
		AuctionItem item = new AuctionItem(123, "Shoe", 25);
		service.create(item);
		System.out.println(service.retreive(123).toString());
		service.bid("Keith", 123);
		System.out.println(service.retreive(123).toString());
		AuctionItem[] thing = service.search("sHoE");
		for (int i = 0; i < thing.length; i++)
		{
			System.out.println(thing[i].toString());
		}
		item = new AuctionItem(123, "Cactus", 50);
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
		System.out.println(item.equals(new AuctionItem(456, "Chicken Wing", 999)));
		if (item.equals(new AuctionItem(456, "Chicken Wing", 999)))
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
