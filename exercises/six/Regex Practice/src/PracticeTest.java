import org.junit.Test;


public class PracticeTest
{
	@Test
	public void test()
	{
		Practice practice = new Practice();
		System.out.println(practice.extractEmails("kpetrone@student.neumont.edu somebodycool@gmail.com"));
		System.out.println(practice.extractListBody("<li>This is a sweet tag!</li>"));
		System.out.println(practice.extractNumbers("3 + 2 / 4.0 * 1 ^ 17.34"));
		System.out.println(practice.extractOperators("3 + 2 / 4.0 * 1 ^ 17.34"));
	}
}
