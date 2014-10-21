
public interface Converter<T>
{
	 T parse(String fromString); // turn a string into a T
	 String format(T fromObject); // turn a T into a string
}
