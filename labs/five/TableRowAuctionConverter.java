import java.io.Serializable;



public class TableRowAuctionConverter implements Converter<Auction>, Serializable
{
	private String creator;
	private AuctionFormat formatter = new AuctionFormat("i,n,c,b,o,t", creator);
	
	public TableRowAuctionConverter()
	{
		
	}
	
	public TableRowAuctionConverter(String format, String creator)
	{
		this.creator = creator;
		if (!format.equals(""))
		{
			this.formatter = new AuctionFormat(format, creator);
		}
	}
	
	public String format(Auction fromObject)
	{
		String formatted = "";
		if (!(fromObject == null))
		{
			formatted = formatter.format(fromObject);
		}
		return formatted;
	}

	public Auction parse(String fromString)
	{
		return formatter.parse(fromString);
	}
}
