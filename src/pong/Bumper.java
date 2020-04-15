package pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bumper {
	private Point topL;
	private Point botR;

	public Bumper(int yTop, int xTop, int yBot, int xBot) {
		topL = new Point(xTop, yTop);
		botR = new Point(xBot, yBot);
	}

	public void translate(int dx, int dy) {
		topL.translate(dx, dy);
		botR.translate(dx, dy);
	}

	public int getHeight() {
		return (botR.getY() - topL.getY());
	}

	public int getWidth() {
		return (botR.getX() - topL.getX());
	}

	public Point getCentre() {
		return (new Point((topL.getX() + botR.getX()) / 2, (topL.getY() + botR.getY()) / 2));
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.yellow);
		g2d.fillRect(topL.getX(), topL.getY(), getWidth(), getHeight());
	}
	
	public int getTop() {
		return topL.getY();
	}
	
	public int getBot() {
		return botR.getY();
	}
}
