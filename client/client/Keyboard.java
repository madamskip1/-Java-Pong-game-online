package client;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener {
	private Game game;

	public Keyboard(Game _game) {
		game = _game;
	}

	@Override
	public void keyPressed(KeyEvent ev) {
		game.keyPressed(ev.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent ev) {
		game.keyReleased(ev.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent ev) {
	}

}
