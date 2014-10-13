

public class ContainsPredicate implements Predicate<Auction>
{
	private String criteria;
	
	public ContainsPredicate(String criteria)
	{
		this.criteria = criteria;
	}
	
	public boolean evaluate(Auction t)
	{		
		return t.getName().contains(criteria);
	}
}
