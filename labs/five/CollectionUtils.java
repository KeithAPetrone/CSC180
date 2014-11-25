import java.util.ArrayList;
import java.util.Collection;


public class CollectionUtils
{
	@SuppressWarnings("rawtypes")
	public static int cardinality(java.lang.Object obj, Collection coll)
	{
		int i = 0;
		for (int j = 0; j < coll.size(); j++)
		{
			if (obj.getClass() == null)
			{
				return 0;
			}
			if (coll.toArray()[j].getClass() == obj.getClass())
			{
				i++;
			}
		}
		return i;
	}
	
	public <T> Collection<T> filter(Collection<T> coll, Predicate<T> pred)
	{
		Collection<T> collTwo = new ArrayList<T>();
		for (T t : coll)
		{
			if (pred.evaluate(t))
			{
				collTwo.add(t);
			}
		}
		return collTwo;
	}
}
