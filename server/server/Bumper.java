package server;
/**
 * Klasa przechowująca informacje o bumperze - odbijaczu.
 */
public class Bumper {
	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 40;
	
	private Point Position;
	private int width;
	private int height;
	private int lastSpeed;
	
	/**
	 * Tworzy instancję klasy Bumper z domyślnymi parametrami
	 */
	public Bumper() {
		Position = new Point();
		width = DEFAULT_WIDTH;
		height = DEFAULT_HEIGHT;
		lastSpeed = 0;
	}
	
	/**
	 * Przemieszcza bumper
	 * @param dy - przesunięcie OY
	 * 	 */
	public void move(int dy) {
		Position.add(0, dy);
		lastSpeed = dy;
	}

	/**
	 * Ustawia wysokość
	 * @param _height - ustawiana wysokość
	 * 	 */
	public void setHeight(int _height) {
		if (height == _height) 
			return;
		
		if (_height > height) 
			Position.y -= height / 2;
		else
			Position.y += _height / 2;
		
		
		if (Position.y < 0)
			Position.y = 0;
		else if ((Position.y + height) > server.Board.HEIGHT)
			Position.y = server.Board.HEIGHT - height;
		
		height = _height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int _width)
	{
		width = _width;
	}

	public int getWidth() {
		return width;
	}

	public Point getCentre() {
		return new Point(Position.getX() + (width / 2), Position.getY() + (height / 2));
	}
	
	public Point getPosition()
	{
		return Position;
	}
	
	public void setPosition(int x, int y)
	{
		Position.x = x;
		Position.y = y;
	}
	
	public void setLastSpeed(int _lastSpeed) {
		lastSpeed = _lastSpeed;
	}
	
	public int getLastSpeed() {
		return lastSpeed;
	}
}
