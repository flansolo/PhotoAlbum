package cs213.photoAlbum.util;

import java.util.Comparator;

import cs213.photoAlbum.model.Photo;

public class CustomComparator implements Comparator<Photo>{
	public int compare(Photo photo1, Photo photo2){
		return photo1.dateTime.compareTo(photo2.dateTime);
	}

}
