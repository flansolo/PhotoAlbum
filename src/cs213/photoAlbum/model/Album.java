package cs213.photoAlbum.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import cs213.photoAlbum.util.ALStack;
/**
 * 
 * @author Tian Qi Xiong
 * stores photos, has album name,
 * functionality to add and delete photos to/from albums,
 * and to recaption photos
 *
 */

public class Album implements AlbumInterface, Serializable{
	/**
	 * albumName - name of album
	 * photolist - hashmap (key=file name of photo, 
	 * value=Photo object)
	 * storing photos in album
	 */
	public String albumName;
	public HashMap<String,Photo> photolist;
	public ALStack<Photo> photostack;
	public Calendar startDate;
	public Calendar endDate;
	
/**
 * constructor creating new instantiation of Album with album name	
 * given by user input
 * @param albumName - name of album, a single user may not have duplicate
 * album names
 */
public Album(String albumName){
	this.albumName=albumName;
	this.photolist = new HashMap<String,Photo>();
}

/**
 * checks if another album is same/identical to this album
 * @param album1- compares with album2
 * @param album2- compares with album1
 * @return true if albums are identical, false if not
 */
public boolean identicalAlbums(Album anotherAlbum){
	if(equals(anotherAlbum)){
		return true;
	}
	return false;
}

/** 
 * removes photo from specific album OR
 * advises photo not in album
 * @param photo - photo being removed
 * @param album - album photo is being removed from

 */
public void removePhotoFromAlbum (String photoname) {
	if(!photolist.containsKey(photoname)){
		System.out.print("Photo "+photoname+" is not in album "+albumName);
		return;
	}
	Photo temp= photolist.get(photoname);
	Photo phototime=null;
	photolist.remove(photoname);
	if(photolist.isEmpty()){
		endDate=null;
		startDate=null;
	}else{
		if(temp.dateTime.equals(startDate)){
			for(Entry<String, Photo> entry: photolist.entrySet()){
				if(phototime==null){
					phototime=entry.getValue();
				}else{
					if(entry.getValue().dateTime.before(phototime.dateTime)){
						phototime=entry.getValue();
					}
				}
			}
			startDate=phototime.dateTime;
			
		}else if(temp.dateTime.equals(endDate)){
			for(Entry<String, Photo> entry: photolist.entrySet()){
				if(phototime==null){
					phototime=entry.getValue();
				}else{
					if(entry.getValue().dateTime.after(phototime.dateTime)){
						phototime=entry.getValue();
					}
				}
			}
			endDate=phototime.dateTime;
		}
	}
	System.out.println("Removed photo:");
	System.out.println(photoname+"-"+"From album "+albumName);
}

/**
 * lists all photos in album
 * @param album - gets album from user
 * 
 */
public void listPhotos () {
	SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
	
	if(photolist.isEmpty()){
		System.out.println("No photos in this album");
		return;
	}
	System.out.println("Photos for album "+albumName+":");
	for(Entry<String, Photo> entry: photolist.entrySet()){
		System.out.println(
		entry.getKey() +" - "+ format.format(entry.getValue().dateTime.getTime())); 
	}
}

/**
 * adds photo with fileName,caption,and calendar date to album, or indicates no photo exists or that
 * photo already exists in album
 * @param fileName - name of photo
 * @param caption - caption of photo
 * @param album - album photo is being added to
 * 
 */
public void addPhoto (String fileName, String caption) {
	if(photolist.containsKey(fileName)){
		System.out.println("Photo "+fileName+" already exists in album "+albumName);
		return;
	}
	File f=new File(fileName);
	if(!f.exists()){
		System.out.println("File "+fileName+" does not exist");
		return;
	}
	Photo temp= new Photo(fileName,caption,Calendar.getInstance());
	temp.dateTime.set(Calendar.MILLISECOND,0);
	temp.dateTime.getTime();
	photolist.put(fileName, temp);
	if(photolist.size()==1){
		startDate=temp.dateTime;
		endDate=temp.dateTime;
	}else{
		if(temp.dateTime.after(endDate)){
			endDate=temp.dateTime;
		}else if(temp.dateTime.before(startDate)){
			startDate=temp.dateTime;
		}
	}
	System.out.println("Added photo "+fileName+":\n"
			+caption+"- Album: "+albumName);
	
}





}
