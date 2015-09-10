package cs213.photoAlbum.model;
/**
 * 
 * @author Tian Qi Xiong
 *
 */

public interface AlbumInterface {
	
	/**
	 * checks if two albums are same/identical
	 * @param album1- compares with album2
	 * @param album2- compares with album1
	 * @return true if albums are identical, false if not
	 */
	public boolean identicalAlbums(Album anotherAlbum);
	
	
	/** 
	 * removes photo from specific album OR
	 * advises photo not in album
	 * @param photo - photo being removed
	 * @param album - album photo is being removed from

	 */
	public void removePhotoFromAlbum (String photoname);
	
	/**
	 * lists all photos in album
	 * @param album - gets album from user
	 * 
	 */
	public void listPhotos ();

	/**
	 * adds photo with fileName and caption to album, or indicates no photo exists or that
	 * photo already exists in album
	 * @param fileName - name of photo
	 * @param caption - caption of photo
	 * @param album - album photo is being added to
	 * 
	 */
	public void addPhoto (String filename, String caption);


	



}
