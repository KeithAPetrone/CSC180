import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleDateFormatConverter implements Converter<Date>
{
	private SimpleDateFormat forParsing = new SimpleDateFormat("d MMMMM yyyy");
	private SimpleDateFormat forFormatting = new SimpleDateFormat("d MMMMM yyyy");
	
	public Date parse(String fromString)
	{
		try {
			return forParsing.parse(fromString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String format(Date fromObject)
	{
		return forFormatting.format(fromObject);
	}
	
	public SimpleDateFormatConverter()
	{
		//Leave everything as default
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat sdf)
	{
		if (sdf != null)
		{
			this.forFormatting = sdf;
			this.forParsing = sdf;
		}
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat forParsing, SimpleDateFormat forFormatting)
	{
		if (forParsing != null)
		{
			this.forParsing = forParsing;
		}
		if (forFormatting != null)
		{
			this.forFormatting = forFormatting;
		}
	}
}
