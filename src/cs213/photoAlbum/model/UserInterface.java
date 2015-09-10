package cs213.photoAlbum.model;

/**
 * 
 * @author Tian Qi Xiong
 *
 */

public interface UserInterface {
	/**
	 * creates album with a name for user, if album does not exist
	 * @param name - name of album 
	 * 
	 */
	public void createAlbum (String name);

	/** deletes album with name name, if album exists
	 * @param name - name of album
	 * 
	 */
	public void deleteAlbum (String name);

	/**
	 *  lists all albums for user or indicates no albums exist
	 */
	public void listAllAlbums ();
	
	/**
	 * renames album
	 * @param album - album to be renamed
	 * @param newName - new name to replace old name of album
	 */
	public void renameAlbum(String albumname, String newName);

	
}
