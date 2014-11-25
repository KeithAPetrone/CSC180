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
			if (toDo.peek() == null)
			{
				toDo.poll();
			}
			if (!toDo.isEmpty())
			{
				if (toDo.peek() == null)
				{
					toDo.poll();
				}
				toDo.element().show();
				done.add(toDo.poll().next());
			}
			else
			{
				toDo.addAll(done);
				if (toDo.peek() == null)
				{
					toDo.poll();
				}
				toDo.add(new DefaultState(toDo.peek().getService()));
				done.clear();
			}
		}
	}
}

