
public interface AuctionService
{
	AuctionItem[] search(String criteria);
	void bid(String username, int itemId);
}
