import java.util.HashMap;


public interface AuctionService
{
	HashMap<java.lang.Integer, Auction> map = new HashMap<java.lang.Integer, Auction>();
	Auction[] search(java.lang.String criteria);
	Auction bid(java.lang.String username, java.lang.Integer id);
	void create(Auction item);
	void delete(java.lang.Integer id);
	Auction retreive(java.lang.Integer id);
	Auction update(Auction item, java.lang.Integer id);
}
