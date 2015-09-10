package cs213.photoAlbum.simpleview;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import cs213.photoAlbum.control.Control;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.util.CustomComparator;
/**
 * The View, simple text-based view with command line mode used to 
 * administer users
 * and interactive mode used after a user has logged in 
 * @author tianqixiong
 *
 */

public class CmdView {
	public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException{
		final Control ctrl= new Control();
		Scanner sc= new Scanner(System.in);
		String line=sc.nextLine();
		StringTokenizer st= new StringTokenizer(line);
		String userid="";
		String username="";
		String name="";
		String filename="";
		String caption="";
		String albumname="";
		String oldalbumname="";
		String newalbumname="";
		String tagtype="";
		String tagvalue = "";
		String startdate="";
		String enddate="";
		SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
	
		


while(!line.isEmpty()){	
		while(st.hasMoreTokens()) {
			String entry = st.nextToken();
			
	if(entry.equals("listusers")){
			if(ctrl.listUsers().isEmpty()){
				System.out.println("no users exist");
			}else{
				for(int i=0; i<ctrl.listUsers().size();i++){
					System.out.println(ctrl.listUsers().get(i).userID);
				}
			}
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			
		}
		
		else	if(entry.equals("adduser")){
			
			if(st.hasMoreTokens()){
				userid=st.nextToken();
				
				if(st.hasMoreTokens()){

					while(st.hasMoreTokens()){
					username=username+" "+st.nextToken();
					if(username.charAt(username.length()-1)=='\"'){
						break;}
					}
					

					username=username.substring(2, username.length()-1);

			
					
					if(st.hasMoreTokens()){
						System.out.println("error: invalid command 2");
						userid="";
						username="";
						line=sc.nextLine();
						st= new StringTokenizer(line);
						break;
					}else{
						ctrl.addUser(userid,username);
						System.out.println(username);
						line=sc.nextLine();
						st= new StringTokenizer(line);
						userid="";
						username="";
						break;
						
					}


				}else{
					System.out.println("error: invalid command 3");
					userid="";
					username="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
				}
			}else{
				System.out.println("error: invalid command 4");
				userid="";
				username="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
		}
		
		else	if(entry.equals("deleteuser")){
			if(st.hasMoreTokens()){
				userid=st.nextToken();

				if(st.hasMoreTokens()){
					System.out.println("error: invalid command 5");
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
				}else{

					ctrl.deleteUser(userid);
					userid="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
				}
			}
			}
		
		else	if(entry.equals("login")){
			if(st.hasMoreTokens()){
				userid=st.nextToken();
				ctrl.loginUser(userid);
				System.out.println("logged in as "+ctrl.getUser().userName);
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
				
			
			}else{
				System.out.println("error: invalid command 8");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
		}
		
		else if(entry.equals("createAlbum")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
				if(st.hasMoreTokens()){
							
							while(st.hasMoreTokens()){
								name=name+" "+st.nextToken();
								
								if(name.charAt(name.length()-1)=='\"'){
								break;}
									}
					
						name=name.substring(2,name.length()-1);
					
			
					if(!ctrl.getUser().albumlist.containsKey(name))
					{
						
					System.out.println("created album for user: " + ctrl.getUser().userName);
					System.out.println(name);
					}
					ctrl.getUser().createAlbum(name);
					name="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
					
				}else{
					System.out.println("error: invalid command 10");
					name="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
				}
}

		else if(entry.equals("deleteAlbum")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		name=st.nextToken();
		if(st.hasMoreTokens()){
			
			while(st.hasMoreTokens()){
				name=name+" "+st.nextToken();
				
				if(name.charAt(name.length()-1)=='\"'){
				break;}
					}
		}
	
		name=name.substring(1,name.length()-1);
		if(ctrl.getUser().albumlist.containsKey(name))
		{
			System.out.println("deleted album from user: " + ctrl.getUser().userName);
			System.out.println(name);
			ctrl.getUser().deleteAlbum(name);
			name="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;

		}else{
			System.out.println("Album does not exist");
			name="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;
		}
		
		

}
	System.out.println("error: invalid command 12");
	line=sc.nextLine();
	st= new StringTokenizer(line);
	break;
		}
		
		else if(entry.equals("listAlbums")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}

		ctrl.getUser().listAllAlbums();
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;

}

		else if(entry.equals("listPhotos")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			name=name+" "+st.nextToken();
			
			if(name.charAt(name.length()-1)=='\"'){
			break;}
				}
		name=name.substring(1,name.length());
		System.out.println(name);
		System.out.println(ctrl.getUser().userName);
		System.out.println(ctrl.getUser().albumlist.toString());
		ctrl.getUser().albumlist.get(name).listPhotos();
		final String fin=getAlb(name);
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	JFrame frame = new GUI("Photo Album Viewer", ctrl, fin);
        		frame.setSize(1100,700);   // width and height
        		frame.setResizable(false);
        		frame.setLocationRelativeTo(null);
        		frame.setVisible(true);
        		  frame.addWindowListener(new WindowAdapter() {
        	        	public void windowClosing(WindowEvent e) {
        	        		System.exit(1);
        	        	}
        	        });	
            }
        });
		
	
		
		line=sc.nextLine();
		st= new StringTokenizer(line);
		name="";
		break;

	} 
}


		else if(entry.equals("addPhoto")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2,filename.length()-1);
		if(st.hasMoreTokens()){
			while(st.hasMoreTokens()){
				caption=caption+" "+st.nextToken();
				
				if(caption.charAt(caption.length()-1)=='\"'){
				break;}
					}
			caption=caption.substring(2,caption.length()-1);
			if(st.hasMoreTokens()){
				while(st.hasMoreTokens()){
					albumname=albumname+" "+st.nextToken();
					
					if(albumname.charAt(albumname.length()-1)=='\"'){
					break;}
						}
				albumname=albumname.substring(2,albumname.length()-1);
				if(!ctrl.getUser().albumlist.containsKey(albumname)){
					System.out.println("album does not exist");
					filename="";
					caption="";
					albumname="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
				}
				ctrl.getUser().albumlist.get(albumname).addPhoto(filename, caption);
				filename="";
				caption="";
				albumname="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}

		}

	}
}

		else if(entry.equals("movePhoto")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2,filename.length()-1);
		if(st.hasMoreTokens()){
			while(st.hasMoreTokens()){
				oldalbumname=oldalbumname+" "+st.nextToken();
				
				if(oldalbumname.charAt(oldalbumname.length()-1)=='\"'){
				break;}
					}
			oldalbumname=oldalbumname.substring(2,oldalbumname.length()-1);
			if(st.hasMoreTokens()){
				while(st.hasMoreTokens()){
					newalbumname=newalbumname+" "+st.nextToken();
					
					if(newalbumname.charAt(newalbumname.length()-1)=='\"'){
					break;}
						}
				newalbumname=newalbumname.substring(2,newalbumname.length()-1);
				ctrl.movePhoto(filename, oldalbumname, newalbumname);
				filename="";
				oldalbumname="";
				newalbumname="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
				}
			
			}

		}

	}

		
		
		else if(entry.equals("removePhoto")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2,filename.length()-1);
		if(st.hasMoreTokens()){
			while(st.hasMoreTokens()){
				albumname=albumname+" "+st.nextToken();
				
				if(albumname.charAt(albumname.length()-1)=='\"'){
				break;}
					}
			albumname=albumname.substring(2,albumname.length()-1);
			ctrl.getUser().albumlist.get(albumname).removePhotoFromAlbum(filename);
			filename="";
			albumname="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;

		}
	}else{
		System.out.println("error: invalid command 26");
		filename="";
		albumname="";
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;
	}
}

		else if(entry.equals("addTag")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2,filename.length()-1);
		if(st.hasMoreTokens()){
			String[] temp=st.nextToken().split(":");
			String tok="";
			while(st.hasMoreTokens()){
				tok=tok+" "+st.nextToken();
				if(tok.charAt(tok.length()-1)=='\"'){
					break;
				}
			}

			temp[1]=temp[1]+tok;
			tagtype=temp[0];
			tagvalue=temp[1];

			tagvalue=tagvalue.substring(1,tagvalue.length()-1);

				Photo tempp= ctrl.getPhoto(filename);
					tempp.addTag(tagtype, tagvalue);
					filename="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
			}

		}

		}



		else if(entry.equals("deleteTag")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2,filename.length()-1);
		if(st.hasMoreTokens()){
			String[] temp=st.nextToken().split(":");
			String tok="";
			while(st.hasMoreTokens()){
				tok=tok+" "+st.nextToken();
				if(tok.charAt(tok.length()-1)=='\"'){
					break;
				}
			}

			temp[1]=temp[1]+tok;
			tagtype=temp[0];
			tagvalue=temp[1];
			tagvalue=tagvalue.substring(1,tagvalue.length()-1);
			if(st.hasMoreTokens()){
				System.out.println("error: invalid command 30");
				filename="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}else{
				Photo tempp= ctrl.getPhoto(filename);
					tempp.deleteTag(tagtype, tagvalue);
					filename="";
					line=sc.nextLine();
					st= new StringTokenizer(line);
					break;
	
			}
		}else{
			System.out.println("error: invalid command 31");
			filename="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;
		}
	}else{
		System.out.println("error: invalid command 32");
		filename="";
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;
	}
}
		
		else if(entry.equals("listPhotoInfo")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			filename=filename+" "+st.nextToken();
			
			if(filename.charAt(filename.length()-1)=='\"'){
			break;}
				}
		filename=filename.substring(2, filename.length()-1);
		if(st.hasMoreTokens()){
			System.out.println("error: invalid command 33");
			filename="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;
		}else{
			format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
			
			String tempalb="";
			for(Entry<String,Album> entry1: ctrl.user.albumlist.entrySet()){
				for(Entry<String,Photo> entry2: entry1.getValue().photolist.entrySet()){
					if(entry2.getValue().fileName.equals(filename)){
						tempalb=tempalb+","+entry1.getValue().albumName;
						break;
					}
				}
			}
			if(tempalb.equals("")){
				System.out.println("Photo "+filename+" does not exist");
				filename="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
			tempalb=tempalb.substring(1);

			System.out.println("Photo file name: "+filename);
			System.out.println("Album: "+tempalb);
			System.out.println("Date: "+format.format(ctrl.getPhoto(filename).dateTime.getTime()));
			System.out.println("Caption: "+ctrl.getPhoto(filename).caption);
			ctrl.getPhoto(filename).listTags();

			filename="";
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;
			
		}
	}else{
		System.out.println("error: invalid command 34");
		filename="";
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;
	}
}

		else if(entry.equals("getPhotosByDate")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		DateFormat df=new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		startdate=st.nextToken();
		Date stdate=df.parse(startdate);
		if(st.hasMoreTokens()){
			enddate=st.nextToken();
			Date endate=df.parse(enddate);
			if(st.hasMoreTokens()){
				System.out.println("error: invalid command 35");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}else{
				ctrl.getPhotosByDate(stdate, endate);
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
		}else{
			System.out.println("error: invalid command 36");
			line=sc.nextLine();
			st= new StringTokenizer(line);
			break;
		}
	}else{
		System.out.println("error: invalid command 37");
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;
	}
}

		else if(entry.equals("getPhotosByTag")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
	if(st.hasMoreTokens()){
		String searchstring=st.nextToken();
		if(st.hasMoreTokens()){
		while(st.hasMoreTokens()){
			searchstring=searchstring+" "+st.nextToken();
				}
	}
		String[] r=searchstring.split("\\,");
		ArrayList<Photo> master= new ArrayList<Photo>();
		for(int i=0; i<r.length; i++){
			if(r[i].charAt(0)==' '){
				r[i]=r[i].substring(1);
			}
			String[] temp= r[i].split("\\:");
			if(temp.length==1){
				tagvalue=temp[0];
				tagvalue=tagvalue.substring(1,tagvalue.length()-1);
				tagtype=" ";
			}else if(temp.length==2){

				tagtype=temp[0];
				tagvalue=temp[1];
				tagvalue=tagvalue.substring(1,tagvalue.length()-1);
			}else{
				System.out.println("error: invalid command 39");
				searchstring="";
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
			ArrayList<Photo> tempp=ctrl.getPhotosByTag(tagtype,tagvalue,searchstring);
			for(int h=0;h<tempp.size();h++){
				if(!master.contains(tempp.get(h))){
					master.add(tempp.get(h));
				}
			}

		}
		
		if(master!=null){
		if(master.isEmpty())
		{
			System.out.println("Error: No Tags found");
		}else{
			Collections.sort(master,new CustomComparator());
			System.out.println("Photos for user " + ctrl.user.userID + " with tags " +searchstring+":");
			for(int z=0;z<master.size();z++){
			Photo tempphoto= master.get(z);	
			String tempalbs=" ";
			for(Entry<String, Album> entry1: ctrl.user.albumlist.entrySet()){
				for(Entry<String, Photo> entry2: entry1.getValue().photolist.entrySet()){
					if(entry2.getValue().fileName.equals(tempphoto.fileName)){
						tempalbs=tempalbs+", "+entry1.getValue().albumName;
					}
				}
			}
			
			tempalbs=tempalbs.substring(2);
			System.out.println(tempphoto.caption+" - Album: "+tempalbs+ " - Date: "+
					format.format(tempphoto.dateTime.getTime()));
			
			}
			
		}}
		
		searchstring="";
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;

	}else{
		System.out.println("error: invalid command 40");
		line=sc.nextLine();
		st= new StringTokenizer(line);
		break;
	}
	
}

		else if(entry.equals("logout")){
			if(ctrl.interactive==false){
				System.out.println("error: must be logged in to perform this action");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}
			if(st.hasMoreTokens()){
				System.out.println("error: invalid command 41");
				line=sc.nextLine();
				st= new StringTokenizer(line);
				break;
			}else{
	ctrl.logoutUser();
	line=sc.nextLine();
	st= new StringTokenizer(line);
	break;
			}
}
		
		
		}

}
	}
	
final public static String getAlb(String name){
	return name;
}
	}



