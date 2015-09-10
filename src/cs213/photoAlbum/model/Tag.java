package cs213.photoAlbum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * 
 * @author Bill Baker
 * information of a single tag of a photo,
 * 
 *
 */

public class Tag implements Serializable {
	/**
	 * tagType- type of tag ex. "location" or "person"
	 * tagValue- value of tag ex. "America" or "Joe"
	 */
	public String tagType;
	
	public String tagValue;
	
	/**
	 * Constructor for Tag
	 * @param tagType- type of tag
	 * @param tagValue- value of tag
	 */
	public Tag(String tagType, String tagValue){
		this.tagType=tagType;
		this.tagValue=tagValue;
	}
	
	/**
	 * to string function for tag,
	 * returns tagType:tagValue as a string
	 */
	public String toString()
	{
		return this.tagType + ":" + this.tagValue;
	}
	
	/**
	 * checks if two tags are equal
	 * @param object- tag object to be compared with this tag
	 */
	public boolean equalsTag (Tag tag){
		if(tag.tagType.equals(this.tagType) && tag.tagValue.equals(tagValue)){
			return true;
		}
		return false;
	}
	
}
