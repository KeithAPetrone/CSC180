import java.util.*;

public class CollectionUtils
{
	public static int cardinality(java.lang.Object obj, Collection<?> coll)
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
}
