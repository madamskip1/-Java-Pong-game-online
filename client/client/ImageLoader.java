package client;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import client.Powerups.PowerupTypes;

public class ImageLoader {	
	private final String fileNames [];
	private BufferedImage pics [];

	
	ImageLoader(){
		fileNames = makePathList();
		pics = new BufferedImage[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			try {
				pics[i]= ImageIO.read(new File(fileNames[i]));
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
		}
	}
	
	public BufferedImage getImage(PowerupTypes pt) {
		 BufferedImage bi = pics[pt.ordinal()];
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
		}
	
	private String[] makePathList() {
		String[] list = new String[PowerupTypes.values().length];
		list[PowerupTypes.ME_FAST.ordinal()] = "images/fastME.png";
		list[PowerupTypes.ME_SLOW.ordinal()] = "images/slowME.png";
		list[PowerupTypes.ME_BEER.ordinal()] = "images/beerME.png";
		list[PowerupTypes.ME_LONG.ordinal()] = "images/longME.png";
		list[PowerupTypes.ME_SMALL.ordinal()] = "images/smallME.png";
		list[PowerupTypes.OPPONENT_FAST.ordinal()] = "images/fastNME.png";
		list[PowerupTypes.OPPONENT_SLOW.ordinal()] = "images/slowNME.png";
		list[PowerupTypes.OPPONENT_BEER.ordinal()] = "images/beerNME.png";
		list[PowerupTypes.OPPONENT_LONG.ordinal()] = "images/longNME.png";
		list[PowerupTypes.OPPONENT_SMALL.ordinal()] = "images/smallNME.png";
		list[PowerupTypes.BALL_MULTIPLE_SINGLE.ordinal()] = "images/splitBall.png";
		list[PowerupTypes.BALL_MULTIPLE_ALL.ordinal()] = "images/multipleAll.png";
		list[PowerupTypes.BALL_FAST.ordinal()] = "images/speedBall.png";
		list[PowerupTypes.BALL_SLOW.ordinal()] = "images/slowBall.png";		
		return list;
	}
	
}
