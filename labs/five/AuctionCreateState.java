import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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

	@SuppressWarnings({ "static-access", "deprecation" })
	public Event next()
	{
		Date date = null;
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
		System.out.println("\nWhen does the auction end? Type in the following format: mm/dd/yyyy with or without the year\n");
		String endDate = scanner.nextLine();
		if (endDate == null || endDate.equals(""))
		{
			date = new Date();
			date.setDate(date.getDate() + 7);
		}
		else
		{
			if (endDate.length() == 10)
			{
				try {
					date = 	new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(endDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (endDate.length() == 5)
			{
				try {
					date = 	new SimpleDateFormat("MM/dd", Locale.ENGLISH).parse(endDate);
					date.setYear(new Date().getYear());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				date = new Date();
				date.setDate(date.getDate() + 7);
			}
		}
		int id = service.map.size();
		boolean dead = true;
		do
		{
			for (int i = 0; i < service.getDeadIDs().size(); i++)
			{
				if (id == service.getDeadIDs().get(i))
				{
					id++;
				}
				else
				{
					dead = false;
				}
			}
		} while(dead && !service.getDeadIDs().isEmpty());
		service.create(new Auction(id, name, Integer.valueOf(startingPrice), description, date, username));
		return new UserHomeState(username, service);
	}
	
	public InMemoryAuctionService getService() {
		return service;
	}
}
