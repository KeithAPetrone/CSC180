import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Practice
{
	public List<Float> extractNumbers(String equation)
	{
		List<Float> list = new ArrayList<Float>();
		Pattern p = Pattern.compile("[-+]?([0-9]*\\.[0-9]+|[0-9]+)");
		Matcher m = p.matcher(equation);
		while (m.find())
		{
		    Float f = Float.parseFloat(m.group());
		    list.add(f);
		}
		return list;
	}
	
	public List<String> extractOperators(String equation)
	{
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("[$-/:-?{-~!\"^_`\\[\\]]");
		Matcher m = p.matcher(equation);
		while (m.find())
		{
		    String s = m.group();
		    list.add(s);
		}
		return list;
	}
	
	public List<String> extractEmails(String message)
	{
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}");
		Matcher m = p.matcher(message);
		while (m.find())
		{
		    String s = m.group();
		    list.add(s);
		}
		return list;
	}
	
	public List<String> extractListBody(String html)
	{
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile("<li>(.*)<\\/li>");
		Matcher m = p.matcher(html);
		while (m.find())
		{
		    String s = m.group();
		    s = s.replace("<li>", "");
		    s = s.replace("</li>", "");
		    list.add(s);
		}
		return list;
	}
}
