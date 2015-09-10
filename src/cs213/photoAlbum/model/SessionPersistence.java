package cs213.photoAlbum.model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * @author Tian Qi Xiong 
 * Back-end of Model class,
 * reads users, writes users, deletes a user identified by userID,
 * gets lists of existing users, identified by userID's
 *
 */

public class SessionPersistence implements Model, Serializable{	
	/**
	 * users- an ArrayList containing all user objects
	 * storeDir- used for serialization
	 * storeFile- used for serialization
	 */
	
	private static final long serialVersionUID = 1L;

	
	public static final String storeDir="Data";
	public static final String storeFile="users.dat";
	public ArrayList<User> users;

	
	

/**
 * Constructor for SessionPersistence with no arguments to create new 
 * instantiation of SessionPersistence object 
 */
public SessionPersistence(){
	users= new ArrayList<User>();
	
}




/**
 * 
 * reads in a user from storage into memory	
 * @param userID - unique ID of user used to log in
 * @return User object of user with this ID containing 
 * all constituent user data
 * @throws IOException 
 * @throws ClassNotFoundException
 */
@SuppressWarnings("resource")
public SessionPersistence readUser() throws IOException, 
	ClassNotFoundException{
	ObjectInputStream ois= new ObjectInputStream(
			new FileInputStream(storeDir+File.separator+storeFile));
			return (SessionPersistence)ois.readObject();
	
}

/**
 * writes users in the users ArrayList
 */
public void writeUsers(){
	for(User u: users){
		System.out.println(u);
	}
}


/**
 * 
 * writes a user to storage from memory
 * @param user - user object containing all constituent user data
 *  to move to storage from memory
 * @throws IOException
 */

@SuppressWarnings("resource")
public void writeUser(SessionPersistence sp) throws IOException{
	ObjectOutputStream oos= new ObjectOutputStream(
			new FileOutputStream(storeDir+File.separator+storeFile));
	oos.writeObject(sp);

}





/**
 * adds a user, creates new user with unique user ID and puts in userlist
 * hashmap
 * @param userID- unique ID used for newly created user used for login
 */
public void addUser(String userID, String username){
	for(int i=0;i<users.size();i++){
		if(users.get(i).userID.equals(userID)){
			System.out.println("Error: User " + userID + " already exists!");
			return;
		}
	}

		User temp = new User(userID);
		temp.userName=username;
		users.add(temp);
		System.out.println("created user "+ 
				userID+" with name "+username);
	
}

/**
 *
 * deletes specified user
 * @param userID - ID of user to be deleted
 */
public void deleteUser(String userID){
	for(int i=0; i<users.size(); i++){
		if(users.get(i).userID.equals(userID)){
			users.remove(i);
			System.out.println("deleted user "+userID);
			return;
		}
	}

		System.out.println("Error: User " + userID + " does not exist!");
}

/**
 * 
 * get a list of existing users, identified by user ID's
 * @return
 */
public ArrayList<User> getUsers(){
	return users;
}


}
