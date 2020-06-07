package client;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import client.Powerups.PowerupTypes;

/**
 * Klasa wykonuj�ca wczytywanie obraz�w opisuj�cych powerupy
 */
public class ImageLoader {
	private final String fileNames[];
	private BufferedImage pics[];

	/**
	 * Tworzy instancj� i wczytuje obrazy
	 */
	ImageLoader() {
		fileNames = makePathList();
		pics = new BufferedImage[fileNames.length];
		for (int i = 0; i < fileNames.length; i++) {
			try {
				pics[i] = ImageIO.read(new File(fileNames[i]));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Funkcja zwracaj�ca obraz dla konkretnego typu powerupu
	 * 
	 * @param pt - typ powerupu
	 * @return BufferedImage obrazu
	 */
	public BufferedImage getImage(PowerupTypes pt) {
		BufferedImage bi = pics[pt.ordinal()];
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	/**
	 * Funkcja inicjuj�ca list� �cie�ek do obraz�w
	 * 
	 * @return lista �cie�ek
	 */
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
		list[PowerupTypes.BALL_SLOW.ordinal()] = "images/slowBall.png";
		list[PowerupTypes.BALL_FAST.ordinal()] = "images/speedBall.png";
		return list;
	}

}
