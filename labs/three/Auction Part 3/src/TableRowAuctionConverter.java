

public class TableRowAuctionConverter implements Converter<Auction>
{
	private AuctionFormat formatter = new AuctionFormat("i,n,c,b,o,t");
	
	public TableRowAuctionConverter()
	{
		
	}
	
	public TableRowAuctionConverter(String format)
	{
		if (!format.equals(""))
		{
			this.formatter = new AuctionFormat(format);
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
