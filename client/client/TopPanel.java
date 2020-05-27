package client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TopPanel extends JPanel {
	public static final Color PANEL_COLOR = Color.black;

	private int sizeY;
	private int sizeX;

	private int p1ScoreX;
	private int p1ScoreY;
	private int p2ScoreX;
	private int p2ScoreY;

	private Score s1;
	private Score s2;

	public TopPanel(int sizeX, int sizeY) {
		super();
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.p1ScoreX = sizeX / 4;
		this.p1ScoreY = sizeY / 2 +2;
		this.p2ScoreX = sizeX * 3 / 4;
		this.p2ScoreY = sizeY / 2 +2;
		setBackground(PANEL_COLOR);
		setPreferredSize(getPreferredSize());

		s1 = new Score(p1ScoreX, p1ScoreY, "name");
		s2 = new Score(p2ScoreX, p2ScoreY, "name1");

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		s1.drawScore(g2d);
		s2.drawScore(g2d);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(sizeX, sizeY));
	}
}