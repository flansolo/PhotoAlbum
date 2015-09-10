package cs213.photoAlbum.util;

/**
 * 
 * @author Bill Baker
 *
 * @param <T>
 */
public interface Stack<T> {
	/**
	 * adds an item to stack
	 * @param item - item to be added to stack
	 */
	public void push(T t);
	
	/**
	 * returns an item from top of stack
	 * @return T - item
	 */
	public T pop();
	
	

}
