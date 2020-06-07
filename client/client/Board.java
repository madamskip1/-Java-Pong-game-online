package client;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Board extends JPanel {
	private static final long serialVersionUID = -929250326118627613L;
	private final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	private final Color LINE_COLOR = new Color(207, 224, 252);
	private final Color TEXT_COLOR = Color.WHITE;

	private final int OFFSET = 100;
	private final int SPACE_W_OFFSET = 170;
	private final int TXT_OFFSET_Y = 15;

	private final String waitingMsg = "Waiting for another player";
	private final String Win = "You have won!";
	private final String Loss = "You have lost!";
	private final String Draw = "DRAW!";
	private final String waitAccept = "Press space to start";
	private final String acceptedMsg = "Waiting for another player to accept.";

	private Balls balls;
	private Bumper Bumpers[];
	private Powerups Powerups;

	public Board(Keyboard keyB) {
		super();
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
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

		if (!(Game.State == Game.States.RUNNING)) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Font font = new Font("Skia", Font.PLAIN, 20);

			g2d.setFont(font);
			g2d.setColor(TEXT_COLOR);
			if (Game.State == Game.States.INIT)
				g2d.drawString(waitingMsg, Window.WIDTH / 2 - OFFSET, Window.HEIGHT / 2 - TXT_OFFSET_Y);
			else if (Game.State == Game.States.ACCEPTED)
				g2d.drawString(acceptedMsg, Window.WIDTH / 2 - SPACE_W_OFFSET, Window.HEIGHT / 2 - TXT_OFFSET_Y);
			else if (Game.State == Game.States.INITIALIZED)
				g2d.drawString(waitAccept, Window.WIDTH / 2 - OFFSET, Window.HEIGHT / 2 - TXT_OFFSET_Y);
			else if (Game.State == Game.States.WIN)
				g2d.drawString(Win, Window.WIDTH / 2 - OFFSET, Window.HEIGHT / 2);
			else if (Game.State == Game.States.LOSS)
				g2d.drawString(Loss, Window.WIDTH / 2 - OFFSET, Window.HEIGHT / 2);
			else if (Game.State == Game.States.DRAW)
				g2d.drawString(Draw, Window.WIDTH / 2 - OFFSET, Window.HEIGHT / 2);
			else if (Game.State == Game.States.BEFORE_INIT)
				;

		} else {
			g2d.setColor(LINE_COLOR);
			g2d.fillRect(Window.WIDTH / 2 - 1, 0, 2, Window.HEIGHT);
			balls.draw(g2d);
			Bumpers[0].draw(g2d, 0);
			Bumpers[1].draw(g2d, 1);
			Powerups.draw(g2d);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return (new Dimension(Window.WIDTH, Window.HEIGHT));
	}

	public void nextTurn() {

		this.repaint();
	}
}