package cs213.photoAlbum.model;

import java.util.HashMap;

public class Album {
	/**
	 * albumName - name of album
	 * photolist - hashmap (key=file name of photo, 
	 * value=Photo object)
	 * storing photos in album
	 */
	String albumName;
	HashMap<String,Photo> photolist;
	
/**
 * constructor creating new instantiation of Album with album name	
 * given by user input
 * @param albumName - name of album, a single user may not have duplicate
 * album names
 */
public Album(String albumName){
	this.albumName=albumName;
}

}
