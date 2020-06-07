package client;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Klasa opakowuj¹ca funkcje listenera dla klawiatury
 */
public class Keyboard implements KeyListener {
	private Game game;

	/**
	 * Tworzy instancjê listenera
	 * 
	 * @param _game - gra dla której instancja klasy jest potrzebna
	 */
	public Keyboard(Game _game) {
		game = _game;
	}

	/**
	 * Funkcja wciœniêcia klawisza
	 */
	@Override
	public void keyPressed(KeyEvent ev) {
		game.keyPressed(ev.getKeyCode());
	}

	/**
	 * Funkcja puszczenia klawisza
	 */
	@Override
	public void keyReleased(KeyEvent ev) {
		game.keyReleased(ev.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent ev) {
	}

}
