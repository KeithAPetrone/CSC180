
public class AuctionCreateState implements Event
{
	String username;
	InMemoryAuctionService service;
	
	public AuctionCreateState(String username, InMemoryAuctionService service)
	{
		this.username = username;
		this.service = service;
	}

	public void show()
	{
		System.out.println(username + ", what would you like to name your product? (hit enter to log out)");
	}

	public Event next()
	{
		String name = scanner.nextLine();
		if (name.equals(""))
		{
			return new UserHomeState(username, service);
		}
		System.out.println("\nDescribe this product\n");
		String description = scanner.nextLine();
		if (description.equals(""))
		{
			return new UserHomeState(username, service);
		}
		System.out.println("\nWhat's the starting value?\n");
		String startingPrice = scanner.nextLine();
		if (startingPrice == null || startingPrice.equals(""))
		{
			startingPrice = "1";
		}
		service.create(new Auction(service.map.size(), name, Integer.valueOf(startingPrice), description));
		return new UserHomeState(username, service);
	}
	
}
