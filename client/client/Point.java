package client;

/**
 * Klasa opisuj¹ca dwuwymiarowy punkt
 */
public class Point {
	protected int x;
	protected int y;

	/**
	 * Inicjuje punkt (0,0)
	 */
	public Point() {
		this.zero();
	}

	/**
	 * Inicjuje punkt (x,y)
	 * 
	 * @param x - pierwsza wspó³rzêdna
	 * @param y - druga wspó³rzêdna
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Ustawia po³o¿enie punktu na (0,0)
	 */
	public void zero() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Zwraca wartoœæ po³o¿enia na osi x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Zwraca wartoœæ po³o¿enia na osi y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Przemieszcza punkt 
	 * 
	 * @param dx - przesuniêcie x
	 * @param dy - przesuniêcie y
	 */
	public void add(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	/**
	 * Zwraca odleg³oœæ punktu od po³o¿enia (x,y)
	 * 
	 * @param x - pierwsza wspó³rzedna
	 * @param y - druga wspó³rzêdna
	 * @return odleg³oœæ
	 */
	public double distance(int x, int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Zwraca odleg³oœæ punktu od przekazanego punktu
	 * 
	 * @param p - punkt wzglêdem którego obliczana jest odleg³oœæ
	 * @return odleg³oœæ
	 */
	public double distance(Point p) {
		return distance(p.x, p.y);
	}

	/**
	 * Ustawia po³o¿enie punktu na (x,y)
	 * 
	 * @param _x - pierwsza wspó³rzedna
	 * @param _y - druga wspó³rzêdna
	 */
	public void set(int _x, int _y) {
		x = _x;
		y = _y;
	}
}
