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
		
		System.out.println(padder.unpad("1234\b\b\b\b"));
		System.out.println(padder.unpad("34.45\b\b\b"));
		System.out.println(padder.unpad("Howdy, Stranger!\b\b\b\b\b\b\b\b\b\b"));
		System.out.println(padder.unpad("1234123412341234\b\b\b\b\b"));
		System.out.println(padder.unpad(""));

		thrown.expect(NumberFormatException.class);
		System.out.println(padder.unpad("1234\b\b\b\b", Double.class));
		System.out.println(padder.unpad("34.45\b\b\b\b", Integer.class));
		System.out.println(padder.unpad("Ora Viva!\b\b\b\b\b", Long.class));
		System.out.println(padder.unpad("", Integer.class));
	}

}
