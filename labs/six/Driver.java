import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Driver
{
	public static JFrame frame;
	
	public static void main(String[] args) throws ParseException, ClassNotFoundException
	{
		RemoteClientAuctionService service = new RemoteClientAuctionService();
		System.out.println("Pick one of the following\n\n1 - Server\n2 - Client\n\n\n");
		frame = new JFrame("Auction Part 6");
		JPanel mainMenu = new JPanel();
		JButton serverButton = new JButton("Start a Server");
		ActionListener startServer = new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					System.out.println("Server is running");
					service.startServer();
				} catch (IOException | ClassNotFoundException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		serverButton.addActionListener(startServer);
		JButton clientButton = new JButton("Connect to Server");
		ActionListener runClient = new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainMenu.setVisible(false);
				frame.repaint();
				service.connectToServer();
			}
		};
		clientButton.addActionListener(runClient);
		mainMenu.add(serverButton);
		mainMenu.add(clientButton);
		frame.add(mainMenu);
		frame.pack();
		frame.setVisible(true);
	}
}
