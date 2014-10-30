import java.text.SimpleDateFormat;
import java.util.Locale;




public class AuctionFormat
{
	private String format;
	
	public AuctionFormat(String format)
	{
		this.format = format;
	}
	
	public String format(Auction fromObject)
	{
		String result = "";
		for (int i = 0; i < format.length(); i++)
		{
			if (format.charAt(i) == 'i')
			{
				result = result + " " + fromObject.getId();
			}
			if (format.charAt(i) == 'n')
			{
				result = result + " " + fromObject.getName();
			}
			if (format.charAt(i) == 'c')
			{
				result = result + "     " + fromObject.getCurrentBid();
			}
			if (format.charAt(i) == 'b')
			{
				result = result + "       " + fromObject.getNumberOfBidsRemaining();
			}
			if (format.charAt(i) == 'o')
			{
				result = result + "     " + fromObject.getOwner();
			}
			if (format.charAt(i) == 't')
			{
				result = result + "      " + new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).format(fromObject.getEndsBy());
			}
			if (i < format.length() - 1 && format.charAt(i) != ',')
			{
				result = result + "     ";
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
			return new Auction(Integer.parseInt(id), name, Integer.parseInt(currentBid), description);
		}
		catch (NumberFormatException e)
		{
			return null;
		}
	}
}
