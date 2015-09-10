package cs213.photoAlbum.model;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import cs213.photoAlbum.util.ALStack;
/**
 * @author Team 61 Tian Qi Xiong / William Baker
 *
 */

public class SessionPersistence implements Model, Serializable{	

	


/**
 * reads in a user from storage into memory	
 * @param userID - unique ID of user used to log in
 * @return User object of user with this ID containing 
 * all constituent user data
 * @throws IOException 
 * @throws ClassNotFoundException
 */
public static User readUser(String userID) throws IOException, 
	ClassNotFoundException{
	return null;
}

/**
 * writes a user to storage from memory
 * @param user - user object containing all constituent user data
 *  to move to storage from memory
 * @throws IOException
 */
public void writeUser(User user)throws IOException{
	
}

/**
 * deletes specified user
 * @param userID - ID of user to be deleted
 */
public void deleteUser(String userID){
	
}

/**
 * get a list of existing users, identified by user ID's
 * @return
 */
public ALStack<String> getUsers(){
	return null;
}


}
