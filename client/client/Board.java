package client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board extends JPanel{
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final Color LINE_COLOR = new Color(207, 224, 252);
	public static final int X_PADDING = 10;
	
	private int sizeY;
	private int sizeX;

	
	public Board(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(sizeX, sizeY));

	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(LINE_COLOR);
		g2d.fillRect(sizeX/2-1, 0, 2, sizeY);
		
		Powerup d = new Powerup();
		d.draw(g2d);
//		Powerup.draw(g2d);
		
		
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(sizeX, sizeY));
	}
	
	
	public void nextTurn() {

		this.repaint();
	}
}