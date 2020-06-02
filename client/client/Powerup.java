package client;

import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;



public class Powerup {
	BufferedImage sprite;
	private Point pos;
	private Powerups.PowerupTypes type;
	
	public Powerup(int x, int y, Powerups.PowerupTypes typ)
	{
		pos = new Point(x, y);
		type = typ;
	}

	public void draw(Graphics2D g) {
		ImageLoader il = new ImageLoader();
		
		RoundRectangle2D rr = new RoundRectangle2D.Double(50, 50, 50, 50, 10, 10);
		g.clip(rr);
		g.drawImage(il.pics[0], 50, 50, null);
	}

}
