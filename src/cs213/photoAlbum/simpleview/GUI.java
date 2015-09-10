package cs213.photoAlbum.simpleview;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cs213.photoAlbum.control.Control;
import cs213.photoAlbum.model.Album;
import cs213.photoAlbum.model.Photo;
import cs213.photoAlbum.model.Tag;


public class GUI extends JFrame{
	
	JLabel photos= new JLabel("Photos");
	JLabel photographLabel= new JLabel();
	JButton addphoto= new JButton("Add Photo");
	JButton remove= new JButton("Remove");
	JButton recaption= new JButton("Recaption");
	JButton addtag= new JButton("Add Tag");
	JButton deletetag= new JButton("Delete Tag");
	JButton movephoto= new JButton("Move Photo");
	JButton slideshow= new JButton("Slideshow");
	JButton userpage= new JButton("User Page");
	JButton logout= new JButton("Logout");
	Control ctrl;
	String albumname;
	JToolBar buttonBar = new JToolBar(null,JToolBar.VERTICAL);
	DefaultListModel newListModel = new DefaultListModel();
	JList photolist= new JList(newListModel);
	Photo selectedphoto;
	Tag selectedtag;
	JLabel cap=new JLabel();
	JLabel date=new JLabel();
	JPanel captions=new JPanel();
	JPanel photodisplay= new JPanel();
	JPanel buttons= new JPanel();
	JPanel photoinfo=new JPanel();
	final DefaultListModel newtagListModel= new DefaultListModel();
	JList tagli=new JList(newtagListModel);
	JButton backarrow= new JButton("<--");
	JButton back= new JButton("Back");
	JButton nextarrow= new JButton("-->");
	JPanel slide= new JPanel();
	int count;
	Icon firstphoto;
	JPanel tagl=new JPanel();
	JLabel taglabel= new JLabel("Tags:");
	JScrollPane scroll= new JScrollPane();
	JScrollPane scroller=new JScrollPane();
    JViewport vv= new JViewport();
			
	public GUI(String title, final Control ctrl, String album){
		super(title);
		this.ctrl=ctrl;
		this.albumname=album;
		
		
		
		GridBagLayout gridbag= new GridBagLayout();
		GridBagConstraints c= new GridBagConstraints();
	

		setLayout(gridbag);
		
		
		
		c.weighty=1.0;
		c.anchor=GridBagConstraints.NORTHWEST;
		c.weightx=.15;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		captions.setLayout(new BoxLayout(captions,BoxLayout.Y_AXIS));
		captions.add(photos, BorderLayout.NORTH);

	
		
		
		
		/*photolist.setModel(newListModel);
		photolist.setVisible(true);
		
		  photolist.addListSelectionListener(new ListSelectionListener()
		  {
		    public void valueChanged(ListSelectionEvent e)
		    {
		      if (e.getValueIsAdjusting() == false)
		      {

		      }
		      else
		      {
		        //showDetail();
		      }
		    }
		  });
		  */

		
		
		 loadimages.execute();
		
		
		//scroller.add(buttonBar);
		 JViewport v= new JViewport();
		 v.add(buttonBar);
		 scroll.setViewport(v);
		scroll.setVisible(true);
		buttonBar.setVisible(true);
		buttonBar.setFloatable(false);
		
		captions.add(scroll);
		//captions.add(scroller, BorderLayout.SOUTH);
		gridbag.setConstraints(captions, c);
		add(captions);
		
		c.weighty=1.0;
		c.anchor=GridBagConstraints.CENTER;
		c.weightx=.7;
		c.fill = GridBagConstraints.BOTH;
		c.gridx=1;
		c.gridy=0;
		
		
		
		GridBagLayout grid= new GridBagLayout();
		GridBagConstraints g= new GridBagConstraints();
		photodisplay.setLayout(grid);
		g.weightx=1;
		g.anchor=GridBagConstraints.NORTH;
		g.weighty=1;
		g.gridx=0;
		g.gridy=0;
		g.fill=GridBagConstraints.BOTH;
		 photographLabel.setVerticalTextPosition(JLabel.BOTTOM);
	     photographLabel.setHorizontalTextPosition(JLabel.CENTER);
	     photographLabel.setHorizontalAlignment(JLabel.CENTER);
	     photographLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	     //photographLabel.setMaximumSize(new Dimension(40,40));

		 vv.add(photographLabel);
		 vv.setViewSize(new Dimension(60,60));
		 scroller.setViewport(vv);
		scroller.setVisible(true);
	     grid.setConstraints(scroller, g);
	
		photodisplay.add(scroller);
		

		photoinfo.setVisible(false);
		photoinfo.setLayout(new GridLayout(2,2));
		photoinfo.add(cap);
		photoinfo.add(date);
		photoinfo.add(taglabel);
		photoinfo.add(tagli);
		
		  tagli.addListSelectionListener(new ListSelectionListener()
		  {
		    public void valueChanged(ListSelectionEvent e)
		    {
		      if (e.getValueIsAdjusting() == false)
		      {
		    	  
		      }
		      else
		      {
		    	  selectedt();
		    	  
		      }
		    }
		  });
		

		  g.weightx=1;
			g.anchor=GridBagConstraints.NORTH;
			g.weighty=.4;
			g.gridx=0;
			g.gridy=1;
		     grid.setConstraints(photoinfo, g);  
		photodisplay.add(photoinfo);
		
		slide.setLayout(new GridLayout(1,3));
		slide.add(backarrow);
		slide.add(back);
		slide.add(nextarrow);
		
		
		 g.weightx=1;
			g.anchor=GridBagConstraints.SOUTH;
			g.weighty=.1;
			g.gridx=0;
			g.gridy=2;
		     grid.setConstraints(slide, g);
		photodisplay.add(slide);
		slide.setVisible(false);
		
		
		
		
		gridbag.setConstraints(photodisplay, c);

		add(photodisplay);
		
		c.weighty=1.0;
		c.anchor=GridBagConstraints.NORTH;
		c.weightx=.15;
		c.fill = GridBagConstraints.BOTH;
		c.gridx=2;
		c.gridy=0;

		buttons.setLayout(new GridLayout(9,1));
		buttons.add(addphoto);
		addphoto.setVisible(true);
		buttons.add(remove);
		remove.setVisible(false);
		buttons.add(recaption);
		recaption.setVisible(false);
		buttons.add(addtag);
		addtag.setVisible(false);
		buttons.add(deletetag);
		deletetag.setVisible(false);
		buttons.add(movephoto);
		movephoto.setVisible(false);
		buttons.add(slideshow);
		slideshow.setVisible(true);
		buttons.add(userpage);
		userpage.setVisible(false);
		buttons.add(logout);
		logout.setVisible(true);
		gridbag.setConstraints(buttons, c);
		add(buttons);
		
		addphoto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			     addphoto.setVisible(false);
					remove.setVisible(false);
					recaption.setVisible(false);
					addtag.setVisible(false);
					deletetag.setVisible(false);
					movephoto.setVisible(false);
					slideshow.setVisible(false);
					userpage.setVisible(false);
					logout.setVisible(false);
				
				
				
				String filename= JOptionPane.showInputDialog("file name of photo");
				
				if(filename.equals("")){
					JOptionPane.showMessageDialog(null, "cannot have empty file name");
			        addphoto.setVisible(true);
					remove.setVisible(true);
					recaption.setVisible(true);
					addtag.setVisible(true);
					deletetag.setVisible(true);
					movephoto.setVisible(true);
					slideshow.setVisible(true);
					userpage.setVisible(true);
					logout.setVisible(true);
				}else{
					String caption= JOptionPane.showInputDialog("caption for photo:");
					if(caption.equals("")){
						JOptionPane.showMessageDialog(null, "cannot have empty caption");
				        addphoto.setVisible(true);
						remove.setVisible(true);
						recaption.setVisible(true);
						addtag.setVisible(true);
						deletetag.setVisible(true);
						movephoto.setVisible(true);
						slideshow.setVisible(true);
						userpage.setVisible(true);
						logout.setVisible(true);
					}else{
					
							if(ctrl.getUser().albumlist.get(albumname).photolist.containsKey(filename)){
								JOptionPane.showMessageDialog(null, "Photo "+filename+" already exists in album "+albumname);
							}else{
								File f=new File(filename);
								if(!f.exists()){
									JOptionPane.showMessageDialog(null, "File "+filename+" does not exist");
								}else{
									ctrl.getUser().albumlist.get(albumname).addPhoto(filename, caption);
									
									JOptionPane.showMessageDialog(null, "photo added");
							        addphoto.setVisible(true);
									remove.setVisible(true);
									recaption.setVisible(true);
									addtag.setVisible(true);
									deletetag.setVisible(true);
									movephoto.setVisible(true);
									slideshow.setVisible(true);
									userpage.setVisible(true);
									logout.setVisible(true);
								}
							}
							
						}
					}
				}	
		});

		remove.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ctrl.getUser().albumlist.get(albumname).removePhotoFromAlbum(selectedphoto.fileName);
				JOptionPane.showMessageDialog(null, "Removed photo:\n"+selectedphoto.fileName+"-"+"From album "+albumname);
		        addphoto.setVisible(true);
				remove.setVisible(true);
				recaption.setVisible(true);
				addtag.setVisible(true);
				deletetag.setVisible(true);
				movephoto.setVisible(true);
				slideshow.setVisible(true);
				userpage.setVisible(true);
				logout.setVisible(true);
					
			}
		});
		
		
		recaption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String newcaption= JOptionPane.showInputDialog("new caption for photo:");
				if(newcaption.equals("")){
					JOptionPane.showMessageDialog(null, "cannot have empty caption");
			        addphoto.setVisible(true);
					remove.setVisible(true);
					recaption.setVisible(true);
					addtag.setVisible(true);
					deletetag.setVisible(true);
					movephoto.setVisible(true);
					slideshow.setVisible(true);
					userpage.setVisible(true);
					logout.setVisible(true);
				}else{
					selectedphoto.caption=newcaption;
					JOptionPane.showMessageDialog(null, "caption changed to: "+newcaption);
			        addphoto.setVisible(true);
					remove.setVisible(true);
					recaption.setVisible(true);
					addtag.setVisible(true);
					deletetag.setVisible(true);
					movephoto.setVisible(true);
					slideshow.setVisible(true);
					userpage.setVisible(true);
					logout.setVisible(true);
				}		
			}
		});
		
		addtag.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String tagtype= JOptionPane.showInputDialog("tag type:");
				if(tagtype.equals("")){
					JOptionPane.showMessageDialog(null, "cannot have empty tag type");
			        addphoto.setVisible(true);
					remove.setVisible(true);
					recaption.setVisible(true);
					addtag.setVisible(true);
					deletetag.setVisible(true);
					movephoto.setVisible(true);
					slideshow.setVisible(true);
					userpage.setVisible(true);
					logout.setVisible(true);
				}else{
					String tagvalue= JOptionPane.showInputDialog("tag value:");
					if(tagvalue.equals("")){
						JOptionPane.showMessageDialog(null, "cannot have empty tag value");
				        addphoto.setVisible(true);
						remove.setVisible(true);
						recaption.setVisible(true);
						addtag.setVisible(true);
						deletetag.setVisible(true);
						movephoto.setVisible(true);
						slideshow.setVisible(true);
						userpage.setVisible(true);
						logout.setVisible(true);
					}else{
						selectedphoto.addTag(tagtype, tagvalue);
						JOptionPane.showMessageDialog(null, "tag added");
				        addphoto.setVisible(true);
						remove.setVisible(true);
						recaption.setVisible(true);
						addtag.setVisible(true);
						deletetag.setVisible(true);
						movephoto.setVisible(true);
						slideshow.setVisible(true);
						userpage.setVisible(true);
						logout.setVisible(true);
					}
					
				}
			}
		});
		
		deletetag.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Photo temp=selectedphoto;
				temp.deleteTag(selectedtag.tagType, selectedtag.tagValue);
				JOptionPane.showMessageDialog(null, "tag removed");
		        addphoto.setVisible(true);
				remove.setVisible(true);
				recaption.setVisible(true);
				addtag.setVisible(true);
				deletetag.setVisible(true);
				movephoto.setVisible(true);
				slideshow.setVisible(true);
				userpage.setVisible(true);
				logout.setVisible(true);
			}
		});
		
		movephoto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String newalbum= JOptionPane.showInputDialog("Move to album:");
				if(newalbum.equals("")){
					JOptionPane.showMessageDialog(null, "cannot have empty tag type");
			        addphoto.setVisible(true);
					remove.setVisible(true);
					recaption.setVisible(true);
					addtag.setVisible(true);
					deletetag.setVisible(true);
					movephoto.setVisible(true);
					slideshow.setVisible(true);
					userpage.setVisible(true);
					logout.setVisible(true);
				}else{ 
					if(!ctrl.getUser().albumlist.containsKey(newalbum)){
						JOptionPane.showMessageDialog(null, "album does not exist");
				        addphoto.setVisible(true);
						remove.setVisible(true);
						recaption.setVisible(true);
						addtag.setVisible(true);
						deletetag.setVisible(true);
						movephoto.setVisible(true);
						slideshow.setVisible(true);
						userpage.setVisible(true);
						logout.setVisible(true);
				}else{
					String fname=selectedphoto.fileName;
					if(ctrl.getUser().albumlist.get(newalbum).photolist.containsKey(fname)){
						JOptionPane.showMessageDialog(null, "Photo "+fname+" already exists in album "+newalbum);
					}else{
						ctrl.movePhoto(selectedphoto.fileName, albumname, newalbum);
						JOptionPane.showMessageDialog(null, "Moved Photo:\n"+selectedphoto.fileName+" - From album "+albumname+ " to album "+newalbum);
				        addphoto.setVisible(true);
						remove.setVisible(true);
						recaption.setVisible(true);
						addtag.setVisible(true);
						deletetag.setVisible(true);
						movephoto.setVisible(true);
						slideshow.setVisible(true);
						userpage.setVisible(true);
						logout.setVisible(true);
					}
				}
					
				}
				
			}
		});
		
		slideshow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 captions.setVisible(false);
				  buttons.setVisible(false);
				  photoinfo.setVisible(false);
				final ArrayList<Photo> photos= new ArrayList<Photo>();
				
				for(Entry<String, Photo> entry: ctrl.getUser().albumlist.get(albumname).photolist.entrySet()){
						photos.add(entry.getValue());
				}
				count=0;
				firstphoto= new ImageIcon(photos.get(0).fileName, photos.get(0).caption);
				photographLabel.setIcon(firstphoto);
				
				slide.setVisible(true);
				
				backarrow.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(count==0){
							count=photos.size()-1;
							firstphoto= new ImageIcon(photos.get(count).fileName,photos.get(count).caption);
							photographLabel.setIcon(firstphoto);
						}else{
							count=count-1;
							firstphoto= new ImageIcon(photos.get(count).fileName, photos.get(count).caption);
							photographLabel.setIcon(firstphoto);
						}
		
					}
					
				});
				
				nextarrow.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(count==photos.size()-1){
							count=0;
							firstphoto= new ImageIcon(photos.get(count).fileName,photos.get(count).caption);
							photographLabel.setIcon(firstphoto);
						}else{
							count=count+1;
							firstphoto= new ImageIcon(photos.get(count).fileName, photos.get(count).caption);
							photographLabel.setIcon(firstphoto);
						}
						
					}
					
					
				});
				
				back.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						 captions.setVisible(true);
						  buttons.setVisible(true);
						  photoinfo.setVisible(true);
						  
						  newtagListModel.removeAllElements();
							photographLabel.setVisible(false);
							tagl.setVisible(false);
						  slide.setVisible(false);
						
					}
					
					
				});
				
			}
		});
		
		userpage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//
			}
		});
		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//
				try {
					ctrl.logoutUser();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(logout, "error occurred");
				
				}
			}
		});
		
		 
	}
	
	


	public void selectedt(){
		deletetag.setVisible(true);
		String temp=(String)tagli.getSelectedValue();
		String[] temps= new String[2];
		temps= temp.split(":");
		selectedtag=selectedphoto.tags.get(temps[1]);
	}
	
	
	private SwingWorker<Void, ThumbnailAction> loadimages = new SwingWorker<Void, ThumbnailAction>() {

	    /**
	     * Creates full size and thumbnail versions of the target image files.
	     */
	    @Override
	    protected Void doInBackground() throws Exception {
	    	for(Entry<String, Photo> entry: ctrl.getUser().albumlist.get(albumname).photolist.entrySet()) {
	            ImageIcon icon;
	            icon = new ImageIcon(entry.getValue().fileName, entry.getValue().caption);

	            ThumbnailAction thumbAction;
	

	                ImageIcon thumbnailIcon = new
	                     ImageIcon(getScaledImage(icon.getImage(), 32, 32));


	                thumbAction = new ThumbnailAction(icon, thumbnailIcon, entry.getValue().caption, entry.getValue());
	                
	                publish(thumbAction);
	           
	            
	        }

	        return null;
	    }

	    /**
	     * Process all loaded images.
	     */
	    @Override
	    protected void process(List<ThumbnailAction> chunks) {
	    	int count=0;
	        for (ThumbnailAction thumbAction : chunks) {
	            JButton thumbButton = new JButton(thumbAction);
	            
	            buttonBar.add(new JLabel(thumbAction.caption),count++);
	            buttonBar.add(thumbButton,count);
	            
	            count++;
	           
	            // add the new button BEFORE the last glue
	            // this centers the buttons in the toolbar
	           //buttonBar.add(thumbButton, buttonBar.getComponentCount() - 1);
	        }
	    }
	};
	
	
	private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
	
	

	
	private class ThumbnailAction extends AbstractAction{
	    /**
	     *The icon if the full image we want to display.
	     */
	    Icon displayPhoto;
	    String caption;
	    Photo photo;
	     
	    /**
	     * @param Icon - The full size photo to show in the button.
	     * @param Icon - The thumbnail to show in the button.
	     * @param String - The descriptioon of the icon.
	     */
	    public ThumbnailAction(Icon photo, Icon thumb, String desc, Photo photoobj){
	        displayPhoto = photo;

	       
	        this.caption=desc;
	        this.photo=photoobj;
	         
	        // The short description becomes the tooltip of a button.
	        putValue(SHORT_DESCRIPTION, desc);
	         
	        // The LARGE_ICON_KEY is the key for setting the
	        // icon when an Action is applied to a button.
	        putValue(LARGE_ICON_KEY, thumb);
	        
	    }
	     
	    /**
	     * Shows the full image in the main area and sets the application title.
	     */
	    public void actionPerformed(ActionEvent e) {
	    	SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");

	        photographLabel.setIcon(displayPhoto);
	        
	        selectedphoto=this.photo;
	        
	        photographLabel.setVisible(true);
			tagl.setVisible(true);
	        
	        addphoto.setVisible(true);
			remove.setVisible(true);
			recaption.setVisible(true);
			addtag.setVisible(true);
			deletetag.setVisible(false);
			movephoto.setVisible(true);
			slideshow.setVisible(true);
			userpage.setVisible(true);
			logout.setVisible(true);
			
			cap.setText(selectedphoto.caption);
			date.setText(format.format(selectedphoto.dateTime.getTime()));
			
			ArrayList<String> temp= selectedphoto.listTags();
			newtagListModel.removeAllElements();
			for(int i=0;i<temp.size();i++){
				newtagListModel.addElement(temp.get(i));
			}
			photoinfo.setVisible(true);
			
	    }
	}
	
	

			
	/*public static void main(String[] args) throws IOException, ClassNotFoundException{
		Control ctrl= new Control();
		
		
		/*JFrame frame = new GUI("Photo Album Viewer",ctrl, album);
		frame.setSize(1100,700);   // width and height
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		  frame.addWindowListener(new WindowAdapter() {
	        	public void windowClosing(WindowEvent e) {
	        		System.exit(1);
	        	}
	        });	
	}*/
				

}
