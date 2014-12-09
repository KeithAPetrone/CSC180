

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
		System.out.println("====================================================\n");
		System.out.println("=========          Search Results          =========\n");
		System.out.println("====================================================\n");
		System.out.println("\t\tID\t\tName\t\tCurrent Bid\t\tBid Count\t\tOwner\t\tEnds By");
		Auction[] items = service.search(criteria);
		String areYouTheCreator = "\t";
		for (int i = 0; i < items.length; i++)
		{
			try
			{
				if (items[i] != null)
				{
					if (items[i].getCreator() == username)
					{
						areYouTheCreator = "You made this: ";
					}
					System.out.println(areYouTheCreator + items[i].toString());
				}
			}
			catch (NullPointerException e)
			{
				//Is null
				System.out.println("Nope");
			}
		}
		System.out.println("====================================================");
		System.out.println("\nEnter the item id to increase the bid by $1. If you made it you can edit it by typing edit[ID] ie. edit123\nDelete with delete[ID]\nOtherwise, enter another search: (hit enter to go back to home page)\n");
	}

	public Event next()
	{
		String id = scanner.nextLine();
		if (id.equals(""))
		{
			return new UserHomeState(username, service);
		}
		if (id.contains("edit"))
		{
			id = id.replace("edit", "");
			try
			{
				int idNum = Integer.parseInt(id);
				service.edit(idNum, username);
			}
			catch (NumberFormatException e)
			{
				return new SearchResultsState(username, id, service);
			}
		}
		else if (id.contains("delete"))
		{
			id = id.replace("delete", "");
			try
			{
				int idNum = Integer.parseInt(id);
				service.delete(idNum);
			}
			catch (NumberFormatException e)
			{
				return new SearchResultsState(username, id, service);
			}
		}
		else
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



