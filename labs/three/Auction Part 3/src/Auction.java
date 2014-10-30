import java.util.Date;


public class Auction
{
	private int id;
	private int currentBid;
	private String owner;
	private String name;
	private String description;
	private Date endsBy;
	private int numberOfBidsRemaining;
	private long timeLeftInMillis;
	private java.util.Map<java.lang.String,java.lang.Object> properties;
	TableRowAuctionConverter converter = new TableRowAuctionConverter();
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
	}
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, Date endDate)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
		this.endsBy = endDate;
		this.numberOfBidsRemaining = 1;
	}
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, Date endDate, int numberOfBidsRemaining)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
		this.endsBy = endDate;
		this.numberOfBidsRemaining = numberOfBidsRemaining;
		Date currentDate = new Date();
		currentDate.setDate(currentDate.getDate() + 7);
		this.timeLeftInMillis = currentDate.getTime();
		this.endsBy = new Date(this.timeLeftInMillis);
	}
	
	public String getDescription() {
		return description;
	}

	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, java.util.Map<java.lang.String,java.lang.Object> properties)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
		this.properties = properties;
	}

	public int getId() {
		return id;
	}

	public int getCurrentBid() {
		return currentBid;
	}

	public String getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public void setCurrentBid(int currentBid) {
		this.currentBid = currentBid;
	}

	public java.util.Map<java.lang.String, java.lang.Object> getProperties() {
		return properties;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public java.lang.Object getProperty(java.lang.String propertyName)
	{
		return properties.get(propertyName);
	}
	
	public <T> T getProperty(java.lang.String propertyName, java.lang.Class<T> propertyType)
	{
		return propertyType.cast(properties.get(propertyName));
	}
	
	public void setProperty(java.lang.String propertyName, java.lang.Object propertyValue)
	{
		properties.put(propertyName, propertyValue);
	}
	
	@Override
	public int hashCode()
	{
		return id;
	}
	
	@Override
	public boolean equals(java.lang.Object obj)
	{
		if (this.getId() == ((Auction) obj).getId())
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		String s = "";
		s = converter.format(this);
		return s;
	}

	public Date getEndsBy() {
		return endsBy;
	}

	public int getNumberOfBidsRemaining() {
		return numberOfBidsRemaining;
	}

	public long getTimeLeftInMillis() {
		return timeLeftInMillis;
	}
}
