
public class AuctionItem
{
	private int id;
	private int currentBid;
	private String owner;
	private String name;
	private java.util.Map<java.lang.String,java.lang.Object> properties;
	
	public AuctionItem(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
	}
	
	public AuctionItem(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, java.util.Map<java.lang.String,java.lang.Object> properties)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
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
		if (this.getId() == ((AuctionItem) obj).getId())
		{
			return true;
		}
		return false;
	}
	
	public String toString()
	{
		String s = "";
		s = s + "ID: " + this.getId() + "        Current Bid: " + this.getCurrentBid() + "        Owner: " + this.getOwner() + "        Name: " + this.getName() + "\n\n";
		return s;
	}
}
