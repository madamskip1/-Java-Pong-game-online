package client;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Klasa przechowuj�ca informacje o po�o�eniu pi�ki.
 * Zawiera funkcj� rysuj�c� pi�k�
 */
public class Ball {
	public static final Color BALL_COLOR = Color.WHITE;
	public static final Color EDGE_COLOR = Color.black;
	
	private Point position;
	private int radius;
	
	/**
	 * Tworzy instancj� pi�ki o koordynatach (0,0) i promieniu 0
	 */
	public Ball() {
		position = new Point(0,0);
		radius = 0;
	}
	
	/**
	 * Tworzy instancj� pi�ki o koordynatach (x,y) i promieniu r
	 * 
	 * @param x - pozycja x
	 * @param y - pozycja y
	 * @param r - promien
	 */
	public Ball(int x, int y, int r) {
		position = new Point(x,y);
		radius = r;
	}

	/**
	 * Rysuje pi�k� na polu
	 * 
	 * @param g - renderer
	 */
	public void drawBall(Graphics2D g) {
		g.setColor(BALL_COLOR);
		g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
		g.setColor(EDGE_COLOR);
		g.drawOval(position.x - radius, position.y - radius, radius * 2, radius * 2);	
	}
	
}
