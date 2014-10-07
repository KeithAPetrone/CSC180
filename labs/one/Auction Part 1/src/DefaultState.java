
public class DefaultState implements Event
{
	InMemoryAuctionService service;
	
	public DefaultState(InMemoryAuctionService service)
	{
		this.service = service;
	}
	
	public void show()
	{
		System.out.println("New User, would you like to log in? (hit enter to decline)\n");
	}

	public Event next()
	{
		String username = scanner.nextLine();
		if (username.equals(""))
		{
			return null;
		}
		return new UserHomeState(username, service);
	}
}
