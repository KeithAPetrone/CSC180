
public class AndPredicate<T> implements Predicate<T>
{
	private Predicate<T> one;
	private Predicate<T> two;
	
	public AndPredicate(Predicate<T> one, Predicate<T> two)
	{
		this.one = one;
		this.two = two;
	}
	
	public boolean evaluate(T t)
	{
		if (one.evaluate(t) && two.evaluate(t))
		{
			return true;
		}
		/*boolean andCheck = false;
		if (criteria.contains("AND"))
		{
			String[] parts = criteria.split(" AND ");
			for (int i = 0; i < parts.length; i+=2)
			{
				if ((t.getName().toLowerCase().contains(parts[i].toLowerCase()) && t.getName().toLowerCase().contains(parts[i+1].toLowerCase())) || (t.getDescription().toLowerCase().contains(parts[i].toLowerCase()) && t.getDescription().toLowerCase().contains(parts[i+1].toLowerCase())))
				{
					andCheck = true;
				}
				else
				{
					andCheck = false;
				}
			}
			if (!criteria.contains("OR") && andCheck)
			{
				return true;
			}
		}*/
		return false;
	}
}
