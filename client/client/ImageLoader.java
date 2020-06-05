package client;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {
	private final String fileNames [] = {"images/slowME.png"};
	private BufferedImage pics [] = new BufferedImage[fileNames.length];

	ImageLoader(){
		for (int i = 0; i < fileNames.length; i++) {
			try {
				pics[i]= ImageIO.read(new File(fileNames[i]));
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	public BufferedImage getImage(int i) {
		 BufferedImage bi = pics[i];
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	

}
