import java.util.Scanner;

import org.junit.Test;


public class TableRowAuctionConverterTest {

	@SuppressWarnings("resource")
	@Test
	public void test() {
		Converter<Auction> auctionConverter = new TableRowAuctionConverter();
		String item = "123,chicken,500,A delicous feast";
		Auction auction = auctionConverter.parse(item);
		System.out.println(auction.toString());
		item = auctionConverter.format(auction);
		System.out.println(item);
		auctionConverter = new TableRowAuctionConverter("n,d,c,i");
		System.out.println(auctionConverter.format(auction));
		System.out.println("\n\nType the details of an auction item in this format: n,d,c,i\nIt will be printed out in this format: d,n,i,c");
		item = new Scanner(System.in).nextLine();
		auction = auctionConverter.parse(item);
		auctionConverter = new TableRowAuctionConverter("d,n,i,c");
		System.out.println(auctionConverter.format(auction));
	}

}
