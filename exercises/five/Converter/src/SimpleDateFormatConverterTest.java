import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;


public class SimpleDateFormatConverterTest {

	@Test
	public void test() {
		Converter<Date> dates = new SimpleDateFormatConverter();
		Converter<Date> dates2 = new SimpleDateFormatConverter(new SimpleDateFormat("yyyy d M"));
		Converter<Date> dates3 = new SimpleDateFormatConverter(new SimpleDateFormat("yyyy MMMMM d"), new SimpleDateFormat("yyyy d MMMMM"));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DATE, 21);
		Date d = cal.getTime();
		String formatted = dates.format(d);
		System.out.println(formatted);
		Assert.assertEquals("21 October 2015", formatted);
		formatted = dates2.format(d);
		System.out.println(formatted);
		Assert.assertEquals("2015 21 10", formatted);
		formatted = dates3.format(d);
		System.out.println(formatted);
		Assert.assertEquals("2015 21 October", formatted);
		System.out.println("Enter a date in this format: yyyy d M");
		@SuppressWarnings("resource")
		String parseTester = new Scanner(System.in).nextLine();
		d = dates2.parse(parseTester);
		parseTester = dates.format(d);
		System.out.println(parseTester);
	}

}
