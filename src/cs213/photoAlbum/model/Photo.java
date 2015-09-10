package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * 
 * @author Bill Baker
 * stores information of photos, and their tags
 * has a caption, calendar instance, functionality to edit attributes
 * of the photo
 *
 */
public class Photo implements PhotoInterface, Serializable {
	/**
	 * fileName - unique (per user) file name of photo
	 * caption - a caption of photo
	 * dateTime - date and time photo was taken
	 * tags - array lit storing tags of photo
	 */
	public String fileName;
	public String caption;
	public Calendar dateTime;
	public HashMap<String,Tag> tags= new HashMap<String,Tag>();
	public ArrayList<String> tagtypes= new ArrayList<String>();
	
	
	

/**
 * constructor creating new instantiation of Photo with file name
 * from user input	
 * @param fileName - specific file name for photo
 */
public Photo(String fileName){
	this.fileName=fileName;
	this.dateTime.set(Calendar.MILLISECOND, 0);
	this.tags = new HashMap <String,Tag> ();
}
/**
 * 
 * @return date of photo
 */
public String getDate() {
	return dateTime.toString();
}
/**
 * returns caption of photo
 */
public String toString()
{
	return this.caption;
}
/**
 * filename of this photo
 * @return
 */
public String getFileName()
{
	return this.fileName;
}


/**
 * alternate constructor creating new instantiation of Photo with file
 * name and caption from user input
 * @param fileName- specific file name for photo
 * @param caption- caption for photo
 */
public Photo(String fileName, String caption,Calendar dateTime){
	this.fileName = fileName;
	this.caption = caption;
	this.dateTime = dateTime;
	this.dateTime.set(Calendar.MILLISECOND,0);
	this.tags = new HashMap <String,Tag> ();
}
	
/**
 * checks if another photo is identical to this photo
 * @param photo1- compared with this photo
 * @return true if photos are identical, false if not identical
 */
public boolean identicalPhotos(Photo photo1){
	if(photo1.equals(this.fileName))
	{
		return true;
	}
	return false;
	
}

/**
 * adds tag to photo OR 
 * advises tag already exists
 * @param tagType - string with tagType
 * @param tagValue - string with tagValue
 *  
 */
public void addTag (String tagType, String tagValue) {
	Tag temp = new Tag(tagType, tagValue);
	if(tags.isEmpty())
	{
		tags.put(temp.tagValue, temp);
		for(int i=0; i<tagtypes.size();i++){
			if(tagtypes.get(i).equals(tagType)){
				System.out.println("Added tag:");
				System.out.println(fileName+" "+tagType+":"+tagValue);
				return;
			}
		}
		tagtypes.add(tagType);
		System.out.println("Added tag:");
		System.out.println(fileName+" "+tagType+":"+tagValue);
		return;
	}

	if(tags.containsKey(tagValue))
			{
		System.out.println("Tag already exists for " + fileName+" "+tagType+":"+tagValue);
		return;
			
			}else{
				tags.put(temp.tagValue, temp);
				for(int i=0; i<tagtypes.size();i++){
					if(tagtypes.get(i).equals(tagType)){
						System.out.println("Added tag:");
						System.out.println(fileName+" "+tagType+":"+tagValue);
						return;
					}
				}
				tagtypes.add(tagType);

				System.out.println("Added tag:");
				System.out.println(fileName+" "+tagType+":"+tagValue);
			}


}

/**
 * deletes tag from this photo
 * OR advises tag does not exist for photo
 * @param tagType - string of tagType
 * @param tagValue - string with tagValue
 * 
 */
public void deleteTag (String tagType, String tagValue) {
	
	Tag temp = new Tag(tagType,tagValue);
	boolean exist = false;
	boolean moretagtypes= false;
	if(tags.isEmpty()){
		System.out.println("Tag does not exist for " + fileName+" "+tagType+":"+tagValue);
		return;
	}

	for(Entry<String, Tag> entry: tags.entrySet()){
		if(entry.getValue().equalsTag(temp))
		{
			tags.remove(entry.getKey());
			for(int i=0; i<tagtypes.size(); i++){
				if(tagtypes.get(i).equals(tagType)){
					moretagtypes=true;
					break;
				}
			}
			if(moretagtypes==false){
				tagtypes.remove(tagType);
			}
			exist = true;
			break;
		}
	}
	

	if(!exist)
	{
		System.out.println("Tag does not exist for " + fileName+" "+tagType+":"+tagValue);
	}else{
		System.out.println("Deleted tag:");
		System.out.println(fileName+" "+tagType+":"+tagValue);
	}
}

/**
 * lists Tags of photo
 * @param photo - photo of tags to be listed
 * @return an array list of tags in format tagtype:tagvalue
 */
public ArrayList<String> listTags () {
	ArrayList<String> taglist= new ArrayList<String>();
	
	System.out.println("Tags:");

	for(Entry<String,Tag> entry: tags.entrySet()){
		if(entry.getValue().tagType.equals("location")){
			System.out.println("location:"+entry.getValue().tagValue);
			taglist.add("location:"+entry.getValue().tagValue);
		}
	}
	

	for(Entry<String,Tag> entry1: tags.entrySet()){
		if(entry1.getValue().tagType.equals("people")){
			System.out.println("people:"+entry1.getValue().tagValue);
			taglist.add("people:"+entry1.getValue().tagValue);
		}
	}
	
	for(int i=0; i<tagtypes.size(); i++){
		if(!tagtypes.get(i).equals("location") && !tagtypes.get(i).equals("people")){

			for(Entry<String,Tag> entry2: tags.entrySet()){
				if(entry2.getValue().tagType.equals(tagtypes.get(i))){
					System.out.println(tagtypes.get(i)+":"+entry2.getValue().tagValue);
					taglist.add(tagtypes.get(i)+":"+entry2.getValue().tagValue);
				}
			}
		}
	}
	return taglist;
}

/**
 * lists photo info:
 * filename, album, date, caption, tags
 */
public void listPhotoInfo(){
	SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
	
	System.out.println("Photo file name: "+fileName);
	String tempalb=null;
	
	

	System.out.println("Album: "+tempalb);
	System.out.println("Date: "+format.format(dateTime.getTime()));
	System.out.println("Caption: "+caption);
	System.out.println("Tags:");
	listTags();
}

/**
 * 
 * @param photo - photo to re-caption
 * @param newCaption - new caption to replace old caption
 */
public void recaptionPhoto(String newCaption){
	this.caption = newCaption;
}

	
}
