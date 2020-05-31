package client;

import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;



public class Powerup {
	BufferedImage sprite;
	

	public void draw(Graphics2D g) {
		ImageLoader il = new ImageLoader();
		
		RoundRectangle2D rr = new RoundRectangle2D.Double(50, 50, 50, 50, 10, 10);
		g.clip(rr);
		g.drawImage(il.pics[0], 50, 50, null);
	}

}
