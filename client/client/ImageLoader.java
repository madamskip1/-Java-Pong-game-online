package client;

import java.awt.Image;
import javax.swing.ImageIcon;

public class ImageLoader {
	private final String fileNames [] = {"images/expand.jpg"};
	protected Image pics [] = new Image[fileNames.length];
	
	ImageLoader(){
		for (int i = 0; i < fileNames.length; i++) {
			try {
				pics[i]= new ImageIcon(fileNames[i]).getImage();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	

}
