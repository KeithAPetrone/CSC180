

public class OrPredicate<T> implements Predicate<T>
{
	private Predicate<T> one;
	private Predicate<T> two;
	
	public OrPredicate(Predicate<T> one, Predicate<T> two)
	{
		this.one = one;
		this.two = two;
	}
	
	public boolean evaluate(T t)
	{
		if (one.evaluate(t) || two.evaluate(t))
		{
			return true;
		}
		
		/*ArrayList<String> advancedCriteria = new ArrayList<String>();
		if (criteria.contains("OR"))
		{
			String[] parts = criteria.split(" OR ");
			for (int i = 0; i < parts.length; i++)
			{
				advancedCriteria.add(parts[i]);
			}
			
			if (!criteria.contains("AND"))
			{
				for (String s : advancedCriteria)
				{
					if (t.getName().toLowerCase().contains(s.toLowerCase()) || t.getDescription().toLowerCase().contains(s.toLowerCase()))
					{
						return true;
					}
				}
			}
			ArrayList<String> specialCriteria = new ArrayList<String>();
			for (int i = 0; i < advancedCriteria.size(); i++)
			{
				if (((String) advancedCriteria.toArray()[i]).contains("AND"))
				{
					specialCriteria.add(((String) advancedCriteria.toArray()[i]));
				}
			}
			for (int i = 0; i < specialCriteria.size(); i++)
			{
				parts = ((String) specialCriteria.toArray()[i]).split(" AND ");
				String[] parts2 = new String[specialCriteria.size()];
				for (int j = 0; j < specialCriteria.size(); j++)
				{
					parts2[j] = (String) specialCriteria.toArray()[j];
				}
				parts2[i] = parts[0];
				specialCriteria = new ArrayList<String>();
				for (int j = 0; j < parts2.length; j++)
				{
					specialCriteria.add(parts2[j]);
				}
				try
				{
					specialCriteria.add(parts[1]);
				}
				catch(IndexOutOfBoundsException e)
				{
					//Prevent Index Out of Bounds
				}
			}
			for (int i = 0; i < specialCriteria.size(); i++)
			{
				if (t.getName().toLowerCase().contains(((String) specialCriteria.toArray()[i]).toLowerCase()) || t.getDescription().toLowerCase().contains(((String) specialCriteria.toArray()[i]).toLowerCase()))
				{
					return true;
				}
			}*/
			
			return false;
	}
}

