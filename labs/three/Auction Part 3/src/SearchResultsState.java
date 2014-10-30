
public class SearchResultsState implements Event
{
	String username;
	String criteria;
	InMemoryAuctionService service;
	
	public SearchResultsState(String username, String criteria, InMemoryAuctionService service)
	{
		this.username = username;
		this.criteria = criteria;
		this.service = service;
	}
		
	public void show()
	{
		System.out.println(username + ", here are your search results:\n");
		System.out.println("========================================\n");
		System.out.println("===          Search Results          ===\n");
		System.out.println("========================================\n");
		System.out.println("ID     Name     Current Bid     Bid Count     Owner     Ends By");
		Auction[] items = service.search(criteria);
		for (int i = 0; i < items.length; i++)
		{
			try
			{
				if (items[i] != null)
				{
					System.out.println(items[i].toString());
				}
			}
			catch (NullPointerException e)
			{
				//Is null
				System.out.println("Nope");
			}
		}
		System.out.println("========================================");
		
		System.out.println("\nEnter the item id to increase the bid by $1.  Otherwise, enter another search: (hit enter to go back to home page)\n");
	}

	public Event next()
	{
		String id = scanner.nextLine();
		if (id.equals(""))
		{
			return new UserHomeState(username, service);
		}
		try
		{
			service.bid(username, Integer.parseInt(id));
		}
		catch (NumberFormatException e)
		{
			return new SearchResultsState(username, id, service);
		}
		return new SearchResultsState(username, criteria, service);
	}
	
	public InMemoryAuctionService getService() {
		return service;
	}
}
