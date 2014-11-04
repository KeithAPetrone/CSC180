import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;


public class PadderTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test() {
		Padder padder = new Padder();
		
		System.out.println(padder.pad(1234, 20));
		System.out.println(padder.pad("Hello, there!", 10));
		System.out.println(padder.pad(null, 10));
		System.out.println(padder.pad(null, 0));
		System.out.println(padder.pad(1234, 0));
		
		System.out.println(padder.unpad("1234~~~~~~~~~"));
		System.out.println(padder.unpad("34.45~~~~"));
		System.out.println(padder.unpad("Howdy, Stranger!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
		System.out.println(padder.unpad("1234123412341234~~~~"));
		System.out.println(padder.unpad(""));

		thrown.expect(NumberFormatException.class);
		System.out.println(padder.unpad("1234~~~~~~~~~", Double.class));
		System.out.println(padder.unpad("34.45~~~~", Integer.class));
		System.out.println(padder.unpad("Ora Viva!~~~~~", Long.class));
		System.out.println(padder.unpad("", Integer.class));
	}

}
