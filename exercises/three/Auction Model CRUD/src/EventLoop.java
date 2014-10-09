import java.util.LinkedList;
import java.util.Queue;


public class EventLoop
{
	Queue<Event> toDo = new LinkedList<Event>();
	Queue<Event> done = new LinkedList<Event>();
	
	void begin()
	{
		InMemoryAuctionService service = new InMemoryAuctionService();
		DefaultState defaultState = new DefaultState(service);
		defaultState.show();
		toDo.add(defaultState);
		toDo.add(defaultState.next());
		while(true)
		{
			if (!toDo.isEmpty())
			{
				toDo.element().show();
				toDo.add(toDo.element().next());
				done.add(toDo.element().next());
				done.add(toDo.element());
				toDo.poll();
			}			
			else
			{
				toDo.addAll(done);
				done.clear();
			}
		}
	}
}

