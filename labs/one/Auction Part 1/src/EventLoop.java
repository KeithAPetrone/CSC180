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
		//defaultState.show();
		toDo.offer(defaultState);
		toDo.offer(defaultState);
		//toDo.offer(defaultState.next());
		while(true)
		{
			if (toDo.isEmpty() == true)
			{
				toDo.addAll(done);
				toDo.offer(defaultState);
				done.clear();
			}
			
			if(toDo.peek() == null)
			{
				toDo.remove();
			}
			else
			{
				toDo.element().show();
				done.offer(toDo.poll().next());
			}
		}
	}
}

