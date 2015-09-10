package cs213.photoAlbum.model;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


/**
 * 
 * @author Tian Qi Xiong
 * contains user's unique ID as a string, used to log in,
 * user's full name,
 * user's albums stored efficiently,
 * functionality to add, delete and rename albums
 *
 */

public class User implements UserInterface, Serializable {
	/**
	 * userID - user's unique ID used to log in
	 * userName - user's full name
	 * albumlist - hashmap (key=album name, value= Album object)
	 * storing albums under user
	 */
	public String userID=null;
	public String userName=null;
	public HashMap<String,Album> albumlist;

/**
 * constructor creating new instantiation of User with user ID given
 * by user input	
 * @param userID - unique ID for each user, used to log in
 */
public User(String userID){
	albumlist = new HashMap<String,Album>();
	this.userID = userID;
}

/**
 * creates album with a name for user, if album does not already 
 * exist
 * @param name - name of album 
 * 
 */
public void createAlbum (String name) {
	
	
	if(this.albumlist.isEmpty())
	{
		albumlist.put(name, new Album(name));
		
		return;
	}
	else if(albumlist.containsKey(name)){
		System.out.println("Album already exists");
		return;
	}
	else
	{
	albumlist.put(name, new Album(name));
	}
}

/** deletes album with name name, if album exists
 * @param name - name of album
 * 
 */
public void deleteAlbum (String name) {
		albumlist.remove(name);
	
}

/**
 *  lists all albums for user or indicates no albums exist
 */
public void listAllAlbums () {
	SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
	
	if(albumlist.isEmpty()){
		System.out.println("No albums exist for user "+ userID);
		return;
	}
	System.out.println("Albums for user "+userID+":");
	for(Entry<String, Album> entry: albumlist.entrySet()){
		if(albumlist.get(entry.getKey()).photolist.size()==0){
			System.out.println(
					entry.getKey() +" number of photos: "+ albumlist.get(entry.getKey()).photolist.size()); 
		}else{
			System.out.println(
					entry.getKey() +" number of photos: "+ albumlist.get(entry.getKey()).photolist.size()+" "+
					format.format(entry.getValue().startDate.getTime())+" - "+format.format(entry.getValue().endDate.getTime())); 
		}
	}
}

/**
 * renames album
 * @param album - album to be renamed
 * @param newName - new name to replace old name of album
 */
public void renameAlbum(String albumname, String newName){
	albumlist.get(albumname).albumName=newName;
}

}
