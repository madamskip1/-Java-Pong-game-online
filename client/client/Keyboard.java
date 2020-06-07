package client;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Klasa opakowuj�ca funkcje listenera dla klawiatury
 */
public class Keyboard implements KeyListener {
	private Game game;

	/**
	 * Tworzy instancj� listenera
	 * 
	 * @param _game - gra dla kt�rej instancja klasy jest potrzebna
	 */
	public Keyboard(Game _game) {
		game = _game;
	}

	/**
	 * Funkcja wci�ni�cia klawisza
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
