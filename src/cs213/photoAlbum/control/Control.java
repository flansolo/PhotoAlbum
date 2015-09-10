package cs213.photoAlbum.control;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.User;
import cs213.photoAlbum.util.CustomComparator;
import cs213.photoAlbum.model.SessionPersistence;
import cs213.photoAlbum.model.Tag;


/**
 * @author Team 61 Tian Qi Xiong / William Baker
 * Control connects the View class to the Model class.
 * The control (or controller) does all manipulation/processing 
 * of data within the program including the creation (but not storage) 
 * and deletion of new objects, sorting/filtering data on various 
 * dimensions, and checking data validity that goes beyond syntactic 
 * correctness of input commands. (For instance, checking dates for 
 * validity.)
 *
 */

public  class Control{

	public User user;
	public SessionPersistence session= new SessionPersistence();
	public boolean interactive;

/**
 * constructor sending the control the User information
 * @throws ClassNotFoundException 
 * @throws IOException 
 * 
 */
public Control () throws IOException, ClassNotFoundException {
	interactive = false;
	session= session.readUser();
	
}

/**
 * Gets user
 * @return user
 */
public User getUser(){
	return this.user;
}

/**
 * logs in a user with userID
 * @param userID- unique userID for user, used to log in
 * @throws IOException
 * @throws ClassNotFoundException
 */
public void loginUser(String userID) throws IOException, ClassNotFoundException{
	
	
	if(interactive==false)
	{
	if(session.users.isEmpty()){
		System.out.println("user " +userID+" does not exist");
	}else{
		for(int i=0; i<session.users.size();i++){
			if(session.users.get(i).userID.equals(userID)){
				this.user=session.users.get(i);
				interactive=true;
				return;
			}
		}
		System.out.println("user " +userID+" does not exist");

	}
	}
	else
	{
		System.out.println("Error: Must logout first");
	}

}

/**
 * logout User
 * @throws IOException
 */
public void logoutUser() throws IOException{
	session.writeUser(session);
	interactive = false;
	
	
}



/**
 * gets list of all users
 */
public ArrayList<User> listUsers(){
	return session.getUsers();
}

/**
 * adds a user
 * @param userID-unique id of user
 * @param userName- first and last name of user
 */
public void addUser(String userID, String userName){
	if(interactive)
	{
		System.out.println("Error: you are in interactive mode, please log out");
	}
	else
	{
		session.addUser(userID, userName);
	}
}

/**
 * deletes user
 * @param userID - userID of user to be deleted
 */
public void deleteUser(String userID) {
	if(interactive)
	{
		System.out.println("Error: you are in interactive mode, please log out");
	}
	else
	{
		session.deleteUser(userID);
	}
}

/**
 * lists all photos with specific tag information in chronological order
 * @param tagType - string with tagType
 * @param tagValue - string with tagValue
 * 
 */
public ArrayList<Photo> getPhotosByTag (String tagType, String tagValue,String searchstring) {
	Tag tempTag= new Tag(tagType,tagValue);
	ArrayList<Photo> temp= new ArrayList<Photo>();
	if(!interactive){
		System.out.println("must login to perform this action");
		return null;
	}
	if(tagType==" "){
		for(Entry<String, Album> entry: user.albumlist.entrySet()){
			for(Entry<String, Photo> entry1: entry.getValue().photolist.entrySet()){
				for(Entry<String,Tag> entry2: entry1.getValue().tags.entrySet()){
					if(entry2.getValue().tagValue.equals(tagValue)){
						temp.add(entry1.getValue());
						break;
					}
				}
			}
		}
	}else{
		for(Entry<String, Album> entry: user.albumlist.entrySet()){
			for(Entry<String, Photo> entry1: entry.getValue().photolist.entrySet()){
				for(Entry<String,Tag> entry2: entry1.getValue().tags.entrySet()){
					if(entry2.getValue().equalsTag(tempTag)){
						temp.add(entry1.getValue());
						break;
					}
				}
			}
		}
	}
	return temp;
	
	
	
	

}

/**
 * lists all photos within date range of startDate to endDate
 * @param startDate - Date to begin search
 * @param endDate - Date to end search
 * 
 */
public void getPhotosByDate (Date startDate, Date endDate) {
	ArrayList<Photo> temp= new ArrayList<Photo>();
	if(interactive)
	{
		Set<String> keys = user.albumlist.keySet();
		for(String key: keys)
			{
				Set<String> albumKeys = user.albumlist.get(key).photolist.keySet();
				for(String photokey: albumKeys)
				{
					Photo tempPhoto = user.albumlist.get(key).photolist.get(photokey);
					
						if((tempPhoto.dateTime.after(startDate)&&tempPhoto.dateTime.before(endDate))
								|| tempPhoto.dateTime.equals(startDate)
								|| tempPhoto.dateTime.equals(endDate))
						{
							temp.add(tempPhoto);
							
						}
					
				}
			}
		Collections.sort(temp,new CustomComparator());
		if(temp.isEmpty())
		{
			System.out.println("Error: No Photos found");
		}
		else
		{
			System.out.println("Photos for user " + user + " in range " +startDate+ " to " + endDate+":");
			for(int i=0;i<temp.size();i++)
			{
				Photo tempphoto=temp.get(i);
				String tempalbs=" ";
				for(Entry<String, Album> entry: user.albumlist.entrySet()){
					for(Entry<String, Photo> entry1: entry.getValue().photolist.entrySet()){
						if(entry1.getValue().fileName.equals(tempphoto.fileName)){
							tempalbs=tempalbs+", "+entry.getValue().albumName;
						}
					}
				}
				
				tempalbs=tempalbs.substring(2);

				System.out.println(temp.get(i).caption + " - Album: " + tempalbs + " Date: " +" - Date:"+ temp.get(i).getDate().toString());
			}
		}
	}
	else
	{
		System.out.println("Error: You need to login");
	}
}




/**
 * moves photo from previous album to new album OR
 * photo does not exist in old album
 * @param photo - photo being moved
 * @param oldAlbum - previous album
 * @param newAlbum - new album
 * 
 */
public void movePhoto (String photoname, String oldAlbum, String newAlbum) {
	if(interactive==false)
	{
		System.out.println("Must login to perform this action");
	}
	else
	{
	
		if(!user.albumlist.get(oldAlbum).photolist.containsKey(photoname))
		{
			System.out.println("Photo "+photoname+" does not exist in "+oldAlbum);
		}
		else{
			if(user.albumlist.get(newAlbum).photolist.isEmpty()){
				Photo temp=user.albumlist.get(oldAlbum).photolist.get(photoname);

				if(user.albumlist.get(newAlbum).photolist.containsKey(photoname)){
					System.out.println("Photo "+photoname+" already exists in album "+newAlbum);
					return;
				}
				File f=new File(photoname);
				if(!f.exists()){
					System.out.println("File "+photoname+" does not exist");
					return;
				}
				user.albumlist.get(newAlbum).photolist.put(photoname, temp);
				if(user.albumlist.get(newAlbum).photolist.size()==1){
					user.albumlist.get(newAlbum).startDate=temp.dateTime;
					user.albumlist.get(newAlbum).endDate=temp.dateTime;
				}else{
					if(temp.dateTime.after(user.albumlist.get(newAlbum).endDate)){
						user.albumlist.get(newAlbum).endDate=temp.dateTime;
					}else if(temp.dateTime.before(user.albumlist.get(newAlbum).startDate)){
						user.albumlist.get(newAlbum).startDate=temp.dateTime;
					}
				}	

				Photo phototime=null;
				user.albumlist.get(oldAlbum).photolist.remove(photoname);
				if(user.albumlist.get(oldAlbum).photolist.isEmpty()){
					user.albumlist.get(oldAlbum).endDate=null;
					user.albumlist.get(oldAlbum).startDate=null;
				}else{
					if(temp.dateTime.equals(user.albumlist.get(oldAlbum).startDate)){
						for(Entry<String, Photo> entry: user.albumlist.get(oldAlbum).photolist.entrySet()){
							if(phototime==null){
								phototime=entry.getValue();
							}else{
								if(entry.getValue().dateTime.before(phototime.dateTime)){
									phototime=entry.getValue();
								}
							}
						}
						user.albumlist.get(oldAlbum).startDate=phototime.dateTime;
						
					}else if(temp.dateTime.equals(user.albumlist.get(oldAlbum).endDate)){
						for(Entry<String, Photo> entry: user.albumlist.get(oldAlbum).photolist.entrySet()){
							if(phototime==null){
								phototime=entry.getValue();
							}else{
								if(entry.getValue().dateTime.after(phototime.dateTime)){
									phototime=entry.getValue();
								}
							}
						}
						user.albumlist.get(oldAlbum).endDate=phototime.dateTime;
					}
				}

			}
			System.out.println("Moved photo " + photoname + ":");
			System.out.println(photoname+ " - From album " + oldAlbum + " to album " + newAlbum);
			
				
		}
	
	}
}

/**
 * Returns photo with this filename
 * @param filename- name of photo/filename/path of photo
 * @return photo with this name
 */
public Photo getPhoto(String filename){
	if(user.albumlist.isEmpty()){
		System.out.println("Photo "+filename+" does not exist");
		return null;
	}
	
	for(Entry<String, Album> entry: user.albumlist.entrySet()){
		for(Entry<String, Photo> entry1: entry.getValue().photolist.entrySet()){
			if(entry1.getValue().fileName.equals(filename)){
				return entry1.getValue();
				
			}
		}
	}
	System.out.println("Photo "+filename+" does not exist");
	return null;
}





}




