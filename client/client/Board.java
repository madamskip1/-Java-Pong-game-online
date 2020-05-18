package client;

import miscellaneous.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import miscellaneous.*;

public class Board extends JPanel{
	
	private int sizeY;
	private int sizeX;
		
	public Board(int sizeY, int sizeX) {
		super();
		this.sizeY = sizeY;
		this.sizeX = sizeX;
		setBackground(BoardConst.BACKGROUND_COLOR);
		setPreferredSize(new Dimension(sizeY, sizeX));
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(BoardConst.MIDLINE_COLOR);
		g2d.fillRect(sizeX/2-1, 0, 2, sizeY);
	}
		
	@Override
	public Dimension getPreferredSize() {
		return(new Dimension(sizeX, sizeY));
	}
	
	
	public void nextTurn() {
		this.repaint();
	}
	
	private void paintPlayer(Graphics2D gp, Game ga, int which) {
		gp.setColor(BoardConst.playerColors[which]);
		Point pt = ga.Players[which].bumper.getPosition();
		int x = pt.getX();
		int y = pt.getY();
		int w = ga.Players[which].bumper.getWidth();
		int h = ga.Players[which].bumper.getHeight();
		gp.fillRect(x, y, w, h);
	}
	
	private void paintBall(Graphics2D gp, miscellaneous.Ball b) {// do poprawy
		gp.setColor(BoardConst.ballColor);
		Point pt = b.getPosition();
		int r = b.getRadius();
		int x = pt.getX();
		int y = pt.getY();
		gp.fillOval(x, y, r, r);
	}
}