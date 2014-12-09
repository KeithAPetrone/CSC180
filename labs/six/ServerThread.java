import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerThread implements Runnable
{
	RemoteClientAuctionService service;
	ServerSocket ss;
	int userNumber;
	
	public ServerThread(RemoteClientAuctionService service, int userNumber) {
		this.service = service;
		this.userNumber = userNumber;
		try {
			this. ss = ss = new ServerSocket(30000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true)
		{
			try
			{
			userNumber = userNumber + 1;
			String username = "User: " + userNumber;
			Socket socket = ss.accept();
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			ObjectInputStream ois;
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String input = br.readLine();
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			if (input.contains("d(") && input.contains(")"))
			{
				input = input.replace("(", "");
				input = input.replace(")", "");
				input = input.replace("d", "");
				service.delete(Integer.parseInt(input));
				pw.println("Auction deleted...");
				pw.flush();
			}
			else if (input.contains("r(") && input.contains(")"))
			{
				input = input.replace("(", "");
				input = input.replace(")", "");
				input = input.replace("r", "");
				try
				{
					pw.println(service.retreive(Integer.parseInt(input)).toString());
					pw.flush();
				}
				catch(ObjectNotFoundException e)
				{
					pw.println("No auction found");
					pw.flush();
				}
			}
			else if (input.contains("b(") && input.contains(")"))
			{
				input = input.replace("(", "");
				input = input.replace(")", "");
				input = input.replace("b", "");
				pw.println(service.bid(username, Integer.parseInt(input)).toString());
				pw.flush();
			}
			else if (input.contains("c"))
			{
				
				ois = new ObjectInputStream(socket.getInputStream());
				Auction auction = (Auction) ois.readObject();
				service.create(auction);
				service.save();                                                            
				pw.println(auction.toString());
				pw.flush();
			}
			else if (input.contains("u(") && input.contains(")"))
			{
				input = input.replace("(", "");
				input = input.replace(")", "");
				input = input.replace("u", "");
				int id = Integer.parseInt(input);
				Auction old = service.retreive(id);
				service.delete(id);
				ois = new ObjectInputStream(socket.getInputStream());
				Auction auction = (Auction) ois.readObject();
				if (old.getCurrentBid() != 0)
				{
					old.setDescription(auction.getDescription());
					auction = old;
				}
				service.create(auction);
				pw.println(auction.toString());
				pw.flush();
			}
			else
			{
				Auction[] results = service.search(input);
				Object o = results;
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(o);
				oos.flush();
			}
			service.save();
			}
			catch (IOException | ClassNotFoundException e)
			{
				
			}
		}
	}

}
