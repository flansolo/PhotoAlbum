package cs213.photoAlbum.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ALStack<T> implements Stack<T>{
	
	private ArrayList<T> items;
	
	public ALStack(){
		items= new ArrayList<T>();
	}
	
	public void push(T item) {
		items.add(item);
	}
	
	public T pop() 
	throws NoSuchElementException {
		if (items.isEmpty()) {
			throw new NoSuchElementException("can't pop from an empty stack");
		}
		return items.remove(items.size()-1);
	}

}
