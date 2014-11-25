import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class Driver
{
	public static void main(String[] args) throws ParseException, ClassNotFoundException
	{
		RemoteClientAuctionService service = new RemoteClientAuctionService();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Pick one of the following\n\n1 - Server\n2 - Client\n\n\n");
		String input = scanner.nextLine();
		if (input.contains("1"))
		{
			try {
				System.out.println("Server is running");
				service.startServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (input.contains("2"))
		{
			service.connectToServer();
		}
	}
}
