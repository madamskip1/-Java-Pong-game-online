package client;

import javax.swing.JPanel;

import client.Game.States;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Board extends JPanel {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 440;
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final Color LINE_COLOR = new Color(207, 224, 252);
	public static final int X_PADDING = 10;

	private final Color textColor = Color.white;
	private final String waitingMsg = "Waiting for another player";
	private final String leftWon = "Left player has won!";
	private final String rightWon = "Right player has won!";

	private Balls balls;
	private Bumper Bumpers[];
	private Powerups Powerups;

	public Board(Keyboard keyB) {
		super();
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		balls = new Balls();
		Bumpers = new Bumper[2];

		addKeyListener(keyB);
		setFocusable(true);
	}

	public void setBalls(Balls _balls) {
		balls = _balls;
	}

	public void setBumper(Bumper _bumper, int n) {
		Bumpers[n] = _bumper;
	}

	public void setPowerups(Powerups _powers) {
		Powerups = _powers;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (Game.State == States.INIT || Game.State == States.GAMEOVER) {
			Font font = new Font("Skia", Font.PLAIN, 40);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(BACKGROUND_COLOR);
			g2d.fillRect(0, 0, WIDTH, HEIGHT);
			g2d.setFont(font);
			g2d.setColor(textColor);

			if (Game.State == States.INIT)
				g2d.drawString(waitingMsg, HEIGHT / 2, WIDTH / 2);

			else if (Game.State == States.GAMEOVER) {
				if (Game.Scores[0].getScore() > Game.Scores[1].getScore()) {
					g2d.drawString(leftWon, HEIGHT / 2, WIDTH / 2);
				} else {
					g2d.drawString(rightWon, HEIGHT / 2, WIDTH / 2);
				}
			}
		}
		g2d.setColor(LINE_COLOR);
		g2d.fillRect(WIDTH / 2 - 1, 0, 2, HEIGHT);

		balls.draw(g2d);
		Bumpers[0].draw(g2d, 0);
		Bumpers[1].draw(g2d, 1);
		Powerups.draw(g2d);

	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(WIDTH, HEIGHT));
	}

	public void nextTurn() {

		this.repaint();
	}
}