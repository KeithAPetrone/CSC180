import java.util.Scanner;


public interface Event
{
	void show(); // prints a message to the screen indicating what the user should do
	Event next(); // takes in the user’s next request and decides what state the user is now in
	Scanner scanner = new Scanner(System.in);
}
