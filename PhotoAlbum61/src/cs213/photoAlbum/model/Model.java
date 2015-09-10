package cs213.photoAlbum.model;

import java.io.IOException;
import cs213.photoAlbum.util.ALStack;

public interface Model {
	public void writeUser(User user)throws IOException;
	public void deleteUser(String userID);
	public ALStack<String> getUsers();
	

}
