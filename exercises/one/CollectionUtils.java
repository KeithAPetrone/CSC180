import java.util.ArrayList;
import java.util.Collection;


public class CollectionUtils
{
	public static int cardinality(java.lang.Object obj, java.util.Collection coll)
	{
		int i = 0;
		for (int j = 0; j < coll.size(); j++)
		{
			if (coll.toArray()[j].getClass() == obj.getClass())
			{
				i++;
			}
		}
		return i;
	}
	
	public static void main(String[] args)
	{
		//Adds three strings to collection. Counts the number of strings in the collection and returns 3.
		//There are 0 ints in the collection and 0 booleans.
		Collection<String> collection1 = new ArrayList<String>();
		collection1.add("String1");
		collection1.add("String2");
		collection1.add("String3");
		String s = new String();
		int i = 0;
		boolean b = false;
		System.out.println("There are " + cardinality(s, collection1) + " strings in collection1");
		System.out.println("There are " + cardinality(i, collection1) + " ints in collection1");
		System.out.println("There are " + cardinality(b, collection1) + " booleans in collection1");
	}
}
