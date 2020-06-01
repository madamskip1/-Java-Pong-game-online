package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class Score {
	private final int size = 20;
	private final Color textColor = Color.WHITE;
	
	
	private int score;
	private Point p;
	private String name;
	
	
	public Score(String name) {
		score = 0;
		this.name = name;
	}
	
	public void setScore(int x) {
		score = x;
	}
	
	public void setPosition(int x, int y)
	{
		p = new Point(x,y);
	}
	
	public void drawScore(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Skia",Font.PLAIN, size);
		g2d.setFont(font);
		g2d.setColor(textColor);
		g2d.drawString(name +": " +String.valueOf(score),p.x,p.y);
	}
}
