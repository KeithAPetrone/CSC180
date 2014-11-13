import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InMemoryAuctionService implements AuctionService
{
	private List<Integer> deadIDs = new ArrayList<Integer>();
	
	public List<Integer> getDeadIDs() {
		return deadIDs;
	}

	public InMemoryAuctionService()
	{
		
	}
	
	public InMemoryAuctionService(Path p)
	{
		byte[] b = null;
		try {
			b = Files.readAllBytes(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = new String(b);
		//Regex for pulling information
		
		//ID
		// [0-9]+</li>
		
		//Name
		// "img" alt="(?:[^\\"]+|\\.)*"
		
		//Date
		// Get it on or before <b>[a-zA-Z0-9,?.? ?]+
		
		//Number of bids
		// <span>+[0-9]+ bid
	
		//Current Bid
		// [$]+[0-9]+.+ 
		
		String ID = "";
		String Name = "";
		String Description = "car";
		String Date = "";
		String NumOfBids = "";
		String CurrentBid = "";
		
		String[] stringArray = text.split("<h3 class=\"lv[a-zA-Z0-9\"]+><a href=\"");
		
		for (int i = 1; i < stringArray.length; i++)
		{
			Pattern pattern = Pattern.compile("[0-9]+</li>");
			Matcher matcher = pattern.matcher(stringArray[i]);
			if (matcher.find()) {
			    ID = (matcher.group());
			    ID = ID.replace("</li>", "");
			}
			pattern = Pattern.compile("\"img\" alt=\"(?:[^\\\"]+|\\.)*\"");
			matcher = pattern.matcher(stringArray[i]);
			if (matcher.find()) {
			    Name = (matcher.group());
			    Name = Name.replace("\"img\" alt=\"", "");
			}
			pattern = Pattern.compile("Get it on or before <b>[a-zA-Z0-9,?.? ?]+");
			matcher = pattern.matcher(stringArray[i]);
			if (matcher.find()) {
			    Date = (matcher.group());
			}
			pattern = Pattern.compile("<span>+[0-9]+ bid");
			matcher = pattern.matcher(stringArray[i]);
			if (matcher.find()) {
			    NumOfBids = (matcher.group());
			    NumOfBids = NumOfBids.replace("<span>", "");
			    NumOfBids = NumOfBids.replace(" bid", "");
			}
			pattern = Pattern.compile("[$]+[0-9]+.+ ");
			matcher = pattern.matcher(stringArray[i]);
			if (matcher.find()) {
			    CurrentBid = (matcher.group());
			    CurrentBid = CurrentBid.replace("$", "");
			    CurrentBid = CurrentBid.replace(".", "");
			}
			Date date = new Date();
			date.setMonth(date.getMonth() + 1);
//			try {
//				date = 	new SimpleDateFormat("EEE, MMM. d", Locale.ENGLISH).parse(Date);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			this.create(new Auction(123, Name, 5, "car", date, 10));
		}
		save();
	}
	
	CollectionUtils collectionUtils = new CollectionUtils();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Auction[] search(java.lang.String criteria)
	{
		List<Auction> temp = new ArrayList<Auction>(map.values());
		String[] criteriaArray = criteria.split(" ");
		Stack<Predicate> predicateStack = new Stack<Predicate>();
		Stack<String> operatorStack = new Stack<String>();
		for (int i = 0; i < criteriaArray.length; i++)
		{
			if (criteriaArray[i].contains("OR"))
			{
				if (!predicateStack.peek().getClass().equals(new AndPredicate(new ContainsPredicate(criteria), new ContainsPredicate(criteria)).getClass()))
				{
					operatorStack.add("OR");
				}			
				while (predicateStack.peek().getClass().equals(new AndPredicate(new ContainsPredicate(criteria), new ContainsPredicate(criteria)).getClass()))
				{
					predicateStack.add(new AndPredicate(predicateStack.pop(), predicateStack.pop()));
					operatorStack.pop();
				}
			}
			else if (criteriaArray[i].contains("AND"))
			{
				operatorStack.add("AND");
			}
			else
			{
				predicateStack.add(new ContainsPredicate(criteriaArray[i]));
			}
		}
		while (!operatorStack.isEmpty())
		{
			String operator = operatorStack.pop();
			if (operator.equals("AND"))
			{
				predicateStack.add(new AndPredicate(predicateStack.pop(), predicateStack.pop()));
			}
			if (!operatorStack.isEmpty())
			{
				if (operator.equals("OR"))
				{
					predicateStack.add(new OrPredicate(predicateStack.pop(), predicateStack.pop()));
				}
			}
		}
		Object[] tempArray = (collectionUtils.filter(temp, predicateStack.pop())).toArray();
		Auction[] auctionArray = new Auction[tempArray.length];
		for (int i = 0; i < tempArray.length; i++)
		{
			if (!((Auction) tempArray[i]).isDead())
			auctionArray[i] = (Auction) tempArray[i];
		}
		return auctionArray;
	}

	public Auction bid(java.lang.String username, java.lang.Integer id)
	{
		if (!map.get(id).getEndsBy().after(new Date()))
		{
			throw new ExpiredBidException();
		}
		if (username == null || id == null)
		{
			throw new java.lang.IllegalArgumentException();
		}
		Auction item = null;
		if (map.get(id) != null)
		{
			map.get(id).setCurrentBid(map.get(id).getCurrentBid() + 1);
			map.get(id).setNumberOfBidsRemaining((map.get(id).getNumberOfBidsRemaining() + 1));
			map.get(id).setOwner(username);
			item = map.get(id);
		}
		else
		{
			throw new ObjectNotFoundException();
		}
		save();
		return item;
	}

	public void create(Auction item)
	{
		if (map.get(item.getId()) == null)
		{
			map.put(item.getId(), item);
		}
		save();
	}
	
	@SuppressWarnings("deprecation")
	public void edit(int id, String username)
	{
		if (username.equals(map.get(id).getCreator()))
		{
			Scanner scanner = new Scanner(System.in);
			if (map.get(id).getNumberOfBidsRemaining() == 0)
			{
				System.out.println("Enter new item name:\n");
				String name = scanner.nextLine();
				map.get(id).setName(name);
				System.out.println("Enter new price:\n");
				int price = scanner.nextInt();
				map.get(id).setCurrentBid(price);
				Date date = null;
				System.out.println("Enter new end date: Type in the following format: mm/dd/yyyy with or without the year\n");
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
				map.get(id).setEndsBy(date);
			}
			System.out.println("Enter new description:\n");
			String description = scanner.nextLine();
			map.get(id).setDescription(description);
		}
		save();
	}
	
	public void delete(java.lang.Integer id)
	{
		if (map.get(id) != null && map.get(id).getNumberOfBidsRemaining() == 0 && !map.get(id).isDead())
		{
			this.deadIDs.add(id);
			map.get(id).setDead(true);
		}
		save();
	}
	
	public Auction retreive(java.lang.Integer id)
	{
		Auction item = null;
		if (map.get(id) != null && !map.get(id).isDead())
		{
			item = map.get(id);
		}
		else
		{
			throw new ObjectNotFoundException();
		}
		return item;
	}
	
	public Auction update(Auction item, java.lang.Integer id)
	{
		if (id != item.getId())
		{
			throw new IdMismatchException();
		}
		if (map.get(id) != null && !map.get(id).isDead())
		{
			map.remove(id);
			map.put(id, item);
			item = map.get(id);
		}
		save();
		return item;
	}
	
	public void save()
	{
		new FileBasedDatasource(map).save();
	}
	
	public InMemoryAuctionService load()
	{
		return new FileBasedDatasource(map).load();
	}
}
