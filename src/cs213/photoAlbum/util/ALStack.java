package cs213.photoAlbum.util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * 
 * @author Bill Baker
 *
 * @param <T>
 */

public class ALStack<T> implements Stack<T>{
	/**
	 * Array List Stack storing items
	 */
	private ArrayList<T> items;
	
	/**
	 * constructor creating a new empty array list(stack) of items 
	 */
	public ALStack(){
		items= new ArrayList<T>();
	}
	
	/**
	 * adds an item to stack
	 * @param item - item to be added to stack
	 */
	public void push(T item) {
		items.add(item);
	}
	
	/**
	 * returns an item from top of stack
	 * @return T - item
	 */
	public T pop() 
	throws NoSuchElementException {
		if (items.isEmpty()) {
			throw new NoSuchElementException("can't pop from an empty stack");
		}
		return items.remove(items.size()-1);
	}
	


}
