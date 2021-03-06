package server;

/**
 * Klasa opisuj�ca dwuwymiarowy punkt
 */
public class Point {
	protected int x;
	protected int y;
	
	/**
	 * Inicjuje punkt (0,0)
	 */
	public Point()
	{
		this.zero();
	}
	
	/**
	 * Inicjuje punkt (x,y)
	 * 
	 * @param x - pierwsza wsp�rz�dna
	 * @param y - druga wsp�rz�dna
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Ustawia po�o�enie punktu na (0,0)
	 */
	public void zero()
	{
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Zwraca warto�� po�o�enia na osi x
	 * @return pozycja x
	 */
	public int getX() {
		return x;
	}
	
	
	/**
	 * Zwraca warto�� po�o�enia na osi y
	 * @return pozycja y
	 */
	public int getY() {
		return y;
	}
	
	public void add(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
		
	/**
	 * Zwraca odleg�o�� punktu od po�o�enia (x,y)
	 * 
	 * @param x - pierwsza wsp�rzedna
	 * @param y - druga wsp�rz�dna
	 * @return odleg�o��
	 */
	public double distance(int x, int y)
	{
		int dx = this.x - x;
		int dy = this.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	/**
	 * Zwraca odleg�o�� punktu od przekazanego punktu
	 * 
	 * @param p - punkt wzgl�dem kt�rego obliczana jest odleg�o��
	 * @return odleg�o��
	 */
	public double distance(Point p)
	{
		return distance(p.x, p.y);
	}
}
	