package client;


import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Klasa opisuj¹ca wygl¹d powerupu oraz rysuj¹ca go na planszy  
 * */
public class Powerup {
	private final int SIZE = 50;
	private final int ARCSIZE = 15;
	
	protected Point pos;
	private Powerups.PowerupTypes type;
	private ImageLoader il;
	
	/**
	 * Inicjuje powerup
	 * @param x - pierwsza wspó³rzêdna po³o¿enia na planszy
	 * @param y - druga wspó³rzêdna po³o¿enia na planszy
	 * @param typ - typ powerupu okreœlaj¹cy jego wygl¹d
	 * @param im - instancja klasy wczytuj¹cej obrazy powerupów
	 */
	public Powerup(int x, int y, Powerups.PowerupTypes typ, ImageLoader im)
	{
		pos = new Point(x, y);
		type = typ;
		il = im;
	}
		
	/**
	 * Rysuje powerup na planszy
	 * @param g - renderer
	 * */
	public void draw(Graphics2D g) {
		BufferedImage img = il.getImage(type);
		RoundRectangle2D rr = new RoundRectangle2D.Double(this.pos.x, this.pos.y, SIZE, SIZE, ARCSIZE, ARCSIZE);
		g.setClip(rr);
		g.drawImage(img, this.pos.x, this.pos.y, null);
	}
}
