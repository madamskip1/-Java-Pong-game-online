package client;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TopPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final Color PANEL_COLOR = Color.black;
	private final int SCORE_OFFSET= 40;
	

	private int p1ScoreX;
	private int p1ScoreY;
	private int p2ScoreX;
	private int p2ScoreY;

	private Score s1;
	private Score s2;

	public TopPanel(Score score1, Score score2, Keyboard keyB) {
		super();
		this.p1ScoreX = Window.WIDTH / 4 - SCORE_OFFSET ;
		this.p1ScoreY = Window.PANEL_HEIGHT / 2 + 2;
		this.p2ScoreX = Window.WIDTH * 3 / 4 - SCORE_OFFSET; 
		this.p2ScoreY = Window.PANEL_HEIGHT / 2 + 2;
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
		
		if(Game.State == Game.States.RUNNING || Game.State == Game.States.WIN ||
				Game.State == Game.States.LOSS || Game.State == Game.States.DRAW){
		s1.drawScore(g2d);
		s2.drawScore(g2d);
		}
	}

	public void setScores(int p1, int p2) {
		s1.setScore(p1);
		s2.setScore(p2);
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(Window.WIDTH, Window.PANEL_HEIGHT));
	}
}