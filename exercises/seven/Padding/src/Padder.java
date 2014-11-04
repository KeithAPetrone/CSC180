
public class Padder
{
	public String pad(Object obj, int len)
	{
		String s = "";
		if (obj != null)
		{
			s = obj.toString();
		}
		if (s.length() < len)
		{
			while (s.length() < len)
			{
				s = s + "~";
			}
		}
		else if (s.length() > len)
		{
			while (s.length() > len)
			{
				s = s.substring(0, s.length() - 1);
			}
		}
		return s;
	}
	
	public Object unpad(String str)
	{
		String s = str;
		Object obj = null;
		if (str.contains("~"))
		{
			s = s.replace("~", "");
			try
			{
				if (Integer.parseInt(s) < Long.MIN_VALUE)
				{
					obj = Long.parseLong(s);
				}
			}
			catch(NumberFormatException e)
			{
				//Not a long
			}
			if (s.contains("."))
			{
				obj = Double.parseDouble(s);
			}
			else if (s.contains("[0-9]+")) 
			{
				obj = Integer.parseInt(s);
			}
			else
			{
				obj = s;
			}
		}
		if (obj == null)
		{
			obj = "";
		}
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T unpad(String str, Class<T> clazz)
	{
		String s = str;
		Object obj = null;
		if (str.contains("~"))
		{
			s = s.replace("~", "");
		}
		if (clazz.equals(Long.class))
		{
			try
			{
				if (Long.parseLong(s) > Integer.MAX_VALUE)
				{
					obj = Long.parseLong(s);
				}
			}
			catch(NumberFormatException e)
			{
				//Not a long
			}
		}
		else if (clazz.equals(Double.class))
		{
			obj = Double.parseDouble(s);
		}
		else if (clazz.equals(Integer.class))
		{
			if (!s.contains("[0-9]+")) 
			{
				obj = Integer.parseInt(s);
			}
		}
		else if (clazz.equals(String.class))
		{
			obj = s;
		}
		return (T) obj;
	}
}
