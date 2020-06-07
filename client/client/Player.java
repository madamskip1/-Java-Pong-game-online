package client;

/**
 * Klasa gracza po stronie klienta
 */	
public class Player {
	public enum Direction
	{
		UP,
		DOWN,
		NONE;
		
		public int size() { return 3; }
	}
	
	private Bumper bumper;
	
	/**
	 * Inicjuje instancje gracza
	 */
	public Player()  {
		bumper = new Bumper();
	}
	
	/**
	 * Ustawia pozycjê bumpera
	 * 
	 * @param x - pierwsza wspó³rzêdna
	 * @param y - druga wspó³rzêdna
	 */
	public void setPosition(int x, int y) { bumper.setPosition(x, y); }
	
	
	/**
	 * Ustawia rozmiar bumpera
	 * 
	 * @param width - szerokoœæ
	 * @param height - wysokoœæ
	 */
	public void setSize(int width, int height)
	{
		bumper.setWidth(width);
		bumper.setHeight(height);
	}
	
	/**
	 * Daje dostêp do pola bumper gracza
	 * 
	 * @return bumper - bumper gracza
	 */
	public Bumper getBumper() {
		return bumper;
	}
}
