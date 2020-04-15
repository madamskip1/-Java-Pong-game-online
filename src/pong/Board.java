package pong;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board extends JPanel{
	private int sizeY;
	private int sizeX;
	
	private Bumper bump1 = new Bumper(5, 5, 20, 10);
	
	public Board(int sizeY, int sizeX) {
		super();
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(sizeY, sizeX));
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.white);
		int len = sizeY/11;
		for(int i=1; i<=11; i=i+2) {
			g2d.fillRect(sizeX/2-len/16, i*len , len/8, len);
		}
		bump1.draw(g2d);
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(sizeX, sizeY));
	}
	
	
	public void nextTurn() {
		bump1.translate(0, 10);
		this.repaint();
	}
}