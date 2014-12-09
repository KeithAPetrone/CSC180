import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RemoteClientAuctionService implements AuctionService
{
	private List<Integer> deadIDs = new ArrayList<Integer>();
	Scanner scanner = new Scanner(System.in);
	
	public List<Integer> getDeadIDs() {
		return deadIDs;
	}
	
	public void startServer() throws IOException, ParseException, ClassNotFoundException
	{
		this.load();
		int userNumber = 1;
		ServerThread t = new ServerThread(this, userNumber);
		t.run();
	}
	
	private JTextField nameField = new JTextField("Name");
	private JTextField descriptionField = new JTextField("Description");
	private JTextField valueField = new JTextField("Value");
	private JTextField dateField = new JTextField("mm/dd/yyyy");
	private int id = 0;
	private Auction a = null;
	public void connectToServer()
	{
		JButton createAuction = new JButton("Create Auction");
		JButton confirm = new JButton("Create Auction");
		final JPanel clientMenu = new JPanel();
		final JPanel auctionForm = new JPanel();
		auctionForm.add(nameField);
		auctionForm.add(descriptionField);
		auctionForm.add(valueField);
		auctionForm.add(dateField);
		auctionForm.add(confirm);
		Driver.frame.add(clientMenu);
		Driver.frame.add(auctionForm);
		clientMenu.setVisible(true);
		auctionForm.setVisible(false);
		ActionListener confirmAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					Socket socket = new Socket("localhost", 30000);
					String input = "c";
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					pw.println(input);
					pw.flush();
					pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
					String name = nameField.getText();
	
					String description = descriptionField.getText();
					
					String currentBid = valueField.getText();
	
					String endDate = dateField.getText();
					Date date = null;
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
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if (endDate.length() == 5)
						{
							try {
								date = 	new SimpleDateFormat("MM/dd", Locale.ENGLISH).parse(endDate);
								date.setYear(new Date().getYear());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							date = new Date();
							date.setDate(date.getDate() + 7);
						}
					}
					a = new Auction(id, name, Integer.parseInt(currentBid), description, date, "System");
					id ++;
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(a);
					oos.flush();
				}
				catch (Exception e5)
				{
					
				}
			}
		};
		confirm.addActionListener(confirmAction);
		ActionListener auctionCreation = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clientMenu.setVisible(false);
				auctionForm.setVisible(true);
			}
		};
		createAuction.addActionListener(auctionCreation);
		clientMenu.add(createAuction);
		Socket socket;
		a = null;
		String input = "";
		try {
			socket = new Socket("localhost", 30000);
			while (true)
			{
				System.out.println("\nDelete - d(id)\nRetrieve r(id)\nBid - b(id)\nCreate - c\nUpdate - u(id)\nPerform Search - s");

				
				
				if (input.contains("s"))
				{
					System.out.println("What do you want to search for?\n");
					input = scanner.nextLine();

					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pw.println(input);
					pw.flush();
					ois = new ObjectInputStream(socket.getInputStream());
					Auction[] auctions = (Auction[]) ois.readObject();
					
					System.out.println("System" + ", here are your search results:\n");
					System.out.println("====================================================\n");
					System.out.println("=========          Search Results          =========\n");
					System.out.println("====================================================\n");
					System.out.println("\tID\t\tName\t\tCurrent Bid\t\tBid Count\t\tOwner\t\tEnds By");
					for (int i = 0; i < auctions.length; i++)
					{
						try
						{
							if (auctions[i] != null)
							{
								System.out.println(auctions[i].toString());
							}
						}
						catch (NullPointerException e)
						{
							//Is null
							System.out.println("Nope");
						}
					}
				}
				else if (input.contains("b"))
				{
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pw.println(input);
					pw.flush();
				}
				else if (input.contains("r"))
				{
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pw.println(input);
					pw.flush();
					System.out.println("System" + ", here are your search results:\n");
					System.out.println("====================================================\n");
					System.out.println("=========          Search Results          =========\n");
					System.out.println("====================================================\n");
					System.out.println("\tID\t\tName\t\tCurrent Bid\t\tBid Count\t\tOwner\t\tEnds By");
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println(br.readLine());
				}
				else if (input.contains("c"))
				{
					
				}
				else if (input.contains("u"))
				{
					socket = new Socket("localhost", 30000);
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println(br.readLine());
					pw.println(input);
					pw.flush();
					System.out.println("\nEnter replacement auction name:");
					String name = scanner.nextLine();
					System.out.println("Enter replacement auction description:");
					String description = scanner.nextLine();
					System.out.println("Enter replacement auction value:");
					String currentBid = scanner.nextLine();
					System.out.println("When does the replacement auction end? Type in the following format: mm/dd/yyyy with or without the year\n");
					String endDate = scanner.nextLine();
					Date date = null;
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
					a = new Auction(id, name, Integer.parseInt(currentBid), description, date, "System");
					id++;
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(a);
					oos.flush();
				}
				else
				{
					socket = new Socket("localhost", 30000);
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					ObjectOutputStream oos;
					ObjectInputStream ois;
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					pw.flush();
					System.out.println(br.readLine());
					pw.println(input);
					pw.flush();
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	CollectionUtils collectionUtils = new CollectionUtils();
	
	@Override
	public Auction[] search(String criteria) {
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

	@Override
	public Auction bid(String username, Integer id) {
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
			map.get(id).setLastModifiedDate(new Date());
			item = map.get(id);
		}
		else
		{
			throw new ObjectNotFoundException();
		}
		return item;
	}

	@Override
	public void create(Auction item) {
		if (map.get(item.getId()) == null)
		{
			map.put(item.getId(), item);
		}
		save();
	}

	@Override
	public void delete(Integer id) {
		if (map.get(id) != null && map.get(id).getNumberOfBidsRemaining() == 0 && !map.get(id).isDead())
		{
			this.deadIDs.add(id);
			map.get(id).setDead(true);
		}
		save();
	}

	@Override
	public Auction retreive(Integer id) {
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

	@Override
	public Auction update(Auction item, Integer id) {
		// TODO Auto-generated method stub
		map.get(id).setLastModifiedDate(new Date());
		return null;
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
