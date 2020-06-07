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
	 * Ustawia pozycj� bumpera
	 * 
	 * @param x - pierwsza wsp�rz�dna
	 * @param y - druga wsp�rz�dna
	 */
	public void setPosition(int x, int y) { bumper.setPosition(x, y); }
	
	
	/**
	 * Ustawia rozmiar bumpera
	 * 
	 * @param width - szeroko��
	 * @param height - wysoko��
	 */
	public void setSize(int width, int height)
	{
		bumper.setWidth(width);
		bumper.setHeight(height);
	}
	
	/**
	 * Daje dost�p do pola bumper gracza
	 * 
	 * @return bumper - bumper gracza
	 */
	public Bumper getBumper() {
		return bumper;
	}
}
