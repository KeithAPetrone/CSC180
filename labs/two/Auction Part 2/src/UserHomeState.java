
public class UserHomeState implements Event
{
	String username;
	InMemoryAuctionService service;
	
	public UserHomeState(String username, InMemoryAuctionService service)
	{
		this.username = username;
		this.service = service;
	}
	
	public void show()
	{
		System.out.println(username + ", what would you like to do? (hit enter to log out)\n\n1 - Search for an item\n2 - Create an auction");
	}

	public Event next()
	{
		String criteria = scanner.nextLine();
		if (criteria.equals(""))
		{
			return new DefaultState(service);
		}
		else if (criteria.equals("1"))
		{
			System.out.println(username + ", what would you like to search for? (hit enter to log out)\n");
			criteria = scanner.nextLine();
			if (criteria.equals(""))
			{
				return new DefaultState(service);
			}
			return new SearchResultsState(username, criteria, service);
		}
		else if (criteria.equals("2"))
		{
			return new AuctionCreateState(username, service);
		}
		return this.next();
	}
}
