
public class AuctionItem
{
	private int id;
	private int currentBid;
	private String owner;
	private String name;
	
	public AuctionItem(int id, String name, int currentBid)
	{
		this.id = id;
		this.name = name;
		this.currentBid = currentBid;
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

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean equals(AuctionItem secondItem)
	{
		if (this.getId() == secondItem.getId())
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
