package msu.cse.turtlegraphics;

import java.util.List;
import java.util.LinkedList;
import java.util.Observer;

public class ObservableCommandList<E> extends LinkedList<E>
{
    public LinkedList<CommandListObserver> listObservers;
    
    public ObservableCommandList()
    {
	super();
	listObservers = new LinkedList<CommandListObserver>();
    }
    
    synchronized public boolean add(E e)
    {
	super.add(e);
	for (CommandListObserver i : listObservers) {
	    i.handleCommandListChanged(this);
	}
	return true;
    }
    
    synchronized public void addObserver(CommandListObserver observer)
    {
	listObservers.add(observer);
    }
    
    synchronized public void removeObserver(CommandListObserver observer)
    {
	listObservers.remove(observer);
    }
}
