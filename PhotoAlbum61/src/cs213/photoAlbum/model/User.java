package cs213.photoAlbum.model;

import java.util.HashMap;


public class User {
	/**
	 * userID - user's unique ID used to log in
	 * userName - user's full name
	 * albumlist - hashmap (key=album name, value= Album object)
	 * storing albums under user
	 */
	String userID;
	String userName;
	HashMap<String,Album> albumlist;

/**
 * constructor creating new instantiation of User with user ID given
 * by user input	
 * @param userID - unique ID for each user, used to log in
 */
public User(String userID){
	
}

}
