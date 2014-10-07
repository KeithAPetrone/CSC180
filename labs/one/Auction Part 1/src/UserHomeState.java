
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
		System.out.println(username + ", what would you like to search for? (hit enter to log out)");
	}

	public Event next()
	{
		String criteria = scanner.nextLine();
		if (criteria.equals(""))
		{
			return null;
		}
		return new SearchResultsState(username, criteria, service);
	}
}
