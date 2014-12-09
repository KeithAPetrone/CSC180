import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;




public class AuctionFormat implements Serializable
{
	private String format;
	private String owner;
	
	public AuctionFormat(String format, String owner)
	{
		this.format = format;
		this.owner = owner;
	}
	
	public String format(Auction fromObject)
	{
		String result = "";
		for (int i = 0; i < format.length(); i++)
		{
			if (format.charAt(i) == 'i')
			{
				result = result + "\t" + fromObject.getId();
			}
			if (format.charAt(i) == 'n')
			{
				result = result + "\t" + fromObject.getName();
			}
			if (format.charAt(i) == 'c')
			{
				result = result + "\t$" + fromObject.getCurrentBid();
			}
			if (format.charAt(i) == 'b')
			{
				result = result + "\t" + fromObject.getNumberOfBidsRemaining();
			}
			if (format.charAt(i) == 'o')
			{
				result = result + "\t" + fromObject.getOwner();
			}
			if (format.charAt(i) == 't')
			{
				result = result + "\t" + new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(fromObject.getEndsBy());
			}
			if (i < format.length() - 1 && format.charAt(i) != ',')
			{
				result = result + "\t";
			}
		}
		return result;
	}

	public Auction parse(String fromString)
	{
		String name = null;
		String currentBid = null;
		String id = null;
		String description = null;
		String[] formatArray = format.split(",");
		String[] auctionArray = fromString.split(",");
		for (int i = 0; i < auctionArray.length; i++)
		{
			if (formatArray[i].equalsIgnoreCase("i"))
			{
				id = auctionArray[i];
			}
			if (formatArray[i].equalsIgnoreCase("n"))
			{
				name = auctionArray[i];
			}
			if (formatArray[i].equalsIgnoreCase("c"))
			{
				currentBid = auctionArray[i];
			}
			if (formatArray[i].equalsIgnoreCase("d"))
			{
				description = auctionArray[i];
			}
		}
		try
		{
			return new Auction(Integer.parseInt(id), name, Integer.parseInt(currentBid), description, this.owner);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}
}
