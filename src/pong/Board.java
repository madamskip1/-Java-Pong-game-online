package pong;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board extends JPanel{
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final int X_PADDING = 10;
	private int sizeY;
	private int sizeX;
	
	//temp
	
	public Player p;
	
	
	
	
	//
	
	public Board(int sizeY, int sizeX) {
		super();
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(sizeY, sizeX));
		
		//temp
		p = new Player(X_PADDING, sizeY/2);
		addMouseMotionListener(p.mouse);
		
		//temp
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
		//temp
		p.draw(g2d);
		//temp
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(sizeX, sizeY));
	}
	
	
	public void nextTurn() {
		//temp
		p.moveBumper(this.getY());
		//
		this.repaint();
	}
}