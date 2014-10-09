import java.util.HashMap;


public interface AuctionService
{
	HashMap<java.lang.Integer, AuctionItem> map = new HashMap<java.lang.Integer, AuctionItem>();
	AuctionItem[] search(java.lang.String criteria);
	AuctionItem bid(java.lang.String username, java.lang.Integer id);
	void create(AuctionItem item);
	void delete(java.lang.Integer id);
	AuctionItem retreive(java.lang.Integer id);
	AuctionItem update(AuctionItem item, java.lang.Integer id);
}
