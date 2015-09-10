package cs213.photoAlbum.model;

import java.util.ArrayList;
import java.util.Calendar;

public class Photo {
	/**
	 * fileName - unique (per user) file name of photo
	 * caption - a caption of photo
	 * dateTime - date and time photo was taken
	 * tags - array lit storing tags of photo
	 */
	String fileName;
	String caption;
	Calendar dateTime;
	ArrayList<Tag> tags;

/**
 * constructor creating new instantiation of Photo with file name
 * from user input	
 * @param fileName - specific file name for photo
 */
public Photo(String fileName){
	this.fileName=fileName;
}
	


	
}
