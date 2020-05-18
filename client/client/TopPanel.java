package pong;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TopPanel extends JPanel{

	private int sizeY;
	private int sizeX;
	
	public TopPanel(int sizeY, int sizeX) {
		super();
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		setBackground(Color.BLACK);
		setPreferredSize(getPreferredSize());



	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(sizeX, sizeY));
	}
}