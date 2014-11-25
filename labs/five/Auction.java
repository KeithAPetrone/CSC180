import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable
{ 
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int id;
	private int externalID;
	private int currentBid;
	private String owner;
	private String name;
	private String description;
	private Date endsBy;
	private int numberOfBidsRemaining;
	private String creator;
	private long timeLeftInMillis;
	private boolean dead = false;
	private java.util.Map<java.lang.String,java.lang.Object> properties;
	TableRowAuctionConverter converter = new TableRowAuctionConverter();
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, String creator)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
		this.creator = creator;
	}
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, Date endDate, String creator)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
		this.description = description;
		this.endsBy = endDate;
		this.numberOfBidsRemaining = 0;
		this.creator = creator;
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
		this.creator = "system";
	}
	
	public Auction(java.lang.Integer id, java.lang.String name, java.lang.Integer currentBid, String description, Date endDate, int numberOfBidsRemaining, String owner)
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
		this.creator = "system";
		this.owner = owner;
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
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
	
	public void setEndsBy(Date endsBy) {
		this.endsBy = endsBy;
	}

	public int getNumberOfBidsRemaining() {
		return numberOfBidsRemaining;
	}

	public long getTimeLeftInMillis() {
		return timeLeftInMillis;
	}
	
	public String getCreator() {
		return creator;
	}

	public void setNumberOfBidsRemaining(int numberOfBidsRemaining) {
		this.numberOfBidsRemaining = numberOfBidsRemaining;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
