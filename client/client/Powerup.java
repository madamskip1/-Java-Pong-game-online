package client;


import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;


public class Powerup {
	protected Point pos;
	private Powerups.PowerupTypes type;
	private ImageLoader il;
	
	public Powerup(int x, int y, Powerups.PowerupTypes typ, ImageLoader im)
	{
		pos = new Point(x, y);
		type = typ;
		il = im;
	}
		

	public void draw(Graphics2D g) {
		BufferedImage img = il.getImage(0);
		RoundRectangle2D rr = new RoundRectangle2D.Double(this.pos.x, this.pos.y, 50, 50, 15, 15);
		g.setClip(rr);
		g.drawImage(img, this.pos.x, this.pos.y, null);

	}
}
