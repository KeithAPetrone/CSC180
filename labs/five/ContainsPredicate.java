import java.util.Date;




public class ContainsPredicate implements Predicate<Auction>
{
	private String criteria;
	
	public ContainsPredicate(String criteria)
	{
		this.criteria = criteria;
	}
	
	public boolean evaluate(Auction t)
	{	
		/*if (criteria.contains("OR") || criteria.contains("AND"))
		{
			boolean andCheck = false;
			boolean orCheck = false;
			
			ArrayList<String> advancedCriteria = new ArrayList<String>();
			if (criteria.contains("OR"))
			{
				String[] parts = criteria.split(" OR ");
				for (int i = 0; i < parts.length; i++)
				{
					advancedCriteria.add(parts[i]);
				}
				
				orCheck = true;
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
						andCheck = true;
						return true;
					}
					else
					{
						andCheck = false;
					}
				}
			}
			
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
			}
			
			if (andCheck && orCheck)
			{
				return true;
			}
		}
		*/
		return ((t.getName().toLowerCase().contains(criteria.toLowerCase()) || t.getDescription().toLowerCase().contains(criteria.toLowerCase())) && t.getEndsBy().after(new Date()));
	}
}
