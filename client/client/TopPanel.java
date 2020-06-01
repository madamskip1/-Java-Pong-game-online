package client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TopPanel extends JPanel {
	public static final Color PANEL_COLOR = Color.black;
	public static final int HEIGHT = 60;
	public static final int WIDTH = 1000;

	private int p1ScoreX;
	private int p1ScoreY;
	private int p2ScoreX;
	private int p2ScoreY;

	private Score s1;
	private Score s2;

	public TopPanel(Score score1, Score score2, Keyboard keyB) {
		super();
		this.p1ScoreX = WIDTH / 4;
		this.p1ScoreY = HEIGHT / 2 +2;
		this.p2ScoreX = WIDTH * 3 / 4;
		this.p2ScoreY = HEIGHT / 2 +2;
		setBackground(PANEL_COLOR);
		setPreferredSize(getPreferredSize());

		s1 = score1;
		s2 = score2;
		
		s1.setPosition(p1ScoreX, p1ScoreY);
		s2.setPosition(p2ScoreX, p2ScoreY);
		
		addKeyListener(keyB);
		setFocusable(true);
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
		return (new Dimension(WIDTH, HEIGHT));
	}
}