package client;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
	public static final Color BALL_COLOR = Color.WHITE;
	public static final Color EDGE_COLOR = Color.black;
	
	private Point position;
	private int radius;
	
	
	public Ball() {
		position = new Point(0,0);
		radius = 0;
	}
	
	public Ball(int x, int y, int r) {
		position = new Point(x,y);
		radius = r;
	}

	
	public void drawBall(Graphics2D g) {
		g.setColor(BALL_COLOR);
		g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
		g.setColor(EDGE_COLOR);
		g.drawOval(position.x - radius, position.y - radius, radius * 2, radius * 2);	
	}
	
}
