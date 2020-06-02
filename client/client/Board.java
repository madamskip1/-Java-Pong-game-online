package client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Board extends JPanel {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 440;
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final Color LINE_COLOR = new Color(207, 224, 252);
	public static final int X_PADDING = 10;
	
	private Balls balls;
	private Bumper Bumpers[];
	private Powerups Powerups;
	//private 
		
	public Board(Keyboard keyB) {
		super();
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		balls = new Balls();
		Bumpers = new Bumper[2];
		
		addKeyListener(keyB);
		setFocusable(true);
	}
	
	public void setBalls(Balls _balls)
	{
		balls = _balls;
	}
	
	public void setBumper(Bumper _bumper, int n)
	{
		Bumpers[n] = _bumper;
	}
	
	public void setPowerups(Powerups _powers)
	{
		Powerups = _powers;
	}
	
	
	//public void setPowerups()
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(LINE_COLOR);
		g2d.fillRect(WIDTH/2-1, 0, 2, HEIGHT);
		
		balls.draw(g2d);
		Bumpers[0].draw(g2d, 0);
		Bumpers[1].draw(g2d, 1);
		Powerups.draw(g2d);
		//d.draw(g2d);
//		Powerup.draw(g2d);
		
		
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(WIDTH, HEIGHT));
	}
	
	
	public void nextTurn() {

		this.repaint();
	}
}