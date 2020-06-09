package server;

/**
 * Klasa pi�ki po stronie serwera
 */
public class Ball {
	public static final int DEFAULT_RADIUS = 15;
	private final double FRICTION = 0.035;

	private Point position;
	private Vector2d velocity;
	private Vector2d previousVelocity;
	private int radius;
	private int touchByPlayer;// 0 == L ; 1 == P

	/**
	 * Tworzy now� pi�k� o pozycji (0,0) i pr�dko�ci (0,0)
	 */
	public Ball() {
		position = new Point(0, 0);
		velocity = new Vector2d(0, 0);
		previousVelocity = new Vector2d(0, 0);
		radius = DEFAULT_RADIUS;
		touchByPlayer = -1;
	}
	/**
	 * Klonowanie pi�ki
	 * 
	 * @return sklonowana pi�ka
	 */
	public Ball clone() {
		Ball _ball = new Ball();
		_ball.setPosition(this.position);
		_ball.setVelocity(this.velocity);
		_ball.touchByPlayer = this.touchByPlayer;
		return _ball;
	}
	
	/**
	 * Generuje now� pi�k�
	 * @param boardWidth - szeroko�� planszy
	 * @param boardHeight - wysoko�� planszy
	 * @return nowa pi�ka
	 */
	public static Ball generateBall(int boardWidth, int boardHeight) {
		Ball _ball = new Ball();
		int y = boardHeight / 2;
		int x = boardWidth / 2;
		_ball.setPosition(x, y);

		x = Utility.randomInt(2, 8);
		y = Utility.randomInt(2, 8);

		if (Utility.randomInt(0, 1) == 1)
			x *= -1;
		if (Utility.randomInt(0, 1) == 1)
			y *= -1;

		_ball.setVelocity(x, y);
		_ball.correctSpeed();

		return _ball;
	}
	/**
	 * Ustawia pozycj� pi�ki
	 * 
	 * @param pos - nowa pozycja pi�ki
	 */
	public void setPosition(Point pos) {
		this.setPosition(pos.x, pos.y);
	}

	/**
	 * Ustawia pozycj� pi�ki
	 * 
	 * @param x - pierwsza wsp�rz�dna
	 * @param y - druga wsp�rz�dna
	 */
	public void setPosition(int x, int y) {
		this.position.x = x;
		this.position.y = y;
	}

	/**
	 * Zwraca pole pozycji pi�ki
	 * 
	 * @return pozycja pi�ki
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Zwraca promie� pi�ki
	 * 
	 * @return promie�
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * Ustawia pr�dko�� pi�ki
	 * 
	 * @param x - sk�adowa x pr�dko�ci
	 * @param y - sk�adowa y pr�dko�ci
	 */
	public void setVelocity(int x, int y) {
		this.velocity.x = x;
		this.velocity.y = y;
	}

	/**
	 * Modyfikuje pr�dko�� pi�ki je�eli sk�adowa x == 0
	 */
	public void correctSpeed() {
		if (velocity.x == 0) {
			velocity.x = 3;
			if (Utility.randomInt(0, 1) == 0)
				velocity.x *= -1;
		}
	}

	/**
	 * Przywraca zapisan� uprzednio pr�dko��
	 * 
	 */
	public void returnToPreviousVelocity() {
		if ((velocity.x * previousVelocity.x) < 0)
			previousVelocity.x *= -1;
		else if (velocity.x == 0)
			previousVelocity.x = 0;
		if ((velocity.y * previousVelocity.y) < 0)
			previousVelocity.y *= -1;
		else if (velocity.y == 0)
			previousVelocity.y = 0;

		velocity = previousVelocity.clone();
	}

	/**
	 * Zapisuje pr�dko��
	 * 
	 */
	public void saveVelocity() {
		previousVelocity = velocity.clone();
	}

	/**
	 * Zwraca gracza przez kt�rego pi�ka jest dotkni�ta
	 * @return 0 - gracz lewy, 1 - prawy, -1 - �aden
	 */
	public int touchBy() {
		return touchByPlayer;
	}

	/**
	 * Ustawia pr�dko�� pi�ki
	 * 
	 * @param vec - pr�dko��
	 */
	public void setVelocity(Vector2d vec) {
		this.setVelocity(vec.x, vec.y);
	}

	/**
	 * Zwraca pole pr�dko�ci pi�ki
	 * @return pr�dko��
	 * */
	public Vector2d getVelocity() {
		return velocity;
	}

	/**
	 * Aktualizuje po�o�enie pi�ki
	 * 
	 * @return true je�eli po�o�enie pi�ki zosta�o aktualizowane, false w przeciwnym przypadku
	 */
	public boolean update() {
		position.add(velocity.x, velocity.y);

		if (position.x < 0 || position.x > Board.WIDTH) // Wysz�a z boku
			return false;

		if ((position.y - radius) < 0) { // Odbicie od g�ry
			position.y = radius - (position.y - radius);
			velocity.negateY();
		} else if ((position.y + radius) > Board.HEIGHT) { // Odbicie od do�u
			position.y = Board.HEIGHT - radius - (position.y + radius - Board.HEIGHT);
			velocity.negateY();
		}
		return true;
	}

	/**
	 * Sprawdza kolizj� pi�ki z bumperami
	 * 
	 * @param b1 - bumper lewego gracza
	 * @param b2 - bumper prawego gracza
	 * @return true je�eli nast�pi�a kolizja, false w przeciwnym przypadku
	 */
	public boolean bumperCollision(Bumper b1, Bumper b2) {
		if (velocity.x > 0) {
			if (Utility.CircleRectangleCollision(position, radius, b2.getPosition(), b2.getWidth(), b2.getHeight())) {
				velocity.negateX();
				spin(b1);
				touchByPlayer = 1;
				if (position.y > b2.getPosition().y + b2.getHeight() && position.y < b2.getPosition().y)
					position.x = b2.getPosition().x - radius;
				return true;
			}

		} else {
			if (Utility.CircleRectangleCollision(position, radius, b1.getPosition(), b1.getWidth(), b1.getHeight())) {
				velocity.negateX();
				spin(b2);
				touchByPlayer = 0;
				if (position.y > b1.getPosition().y + b1.getHeight() && position.y < b1.getPosition().y)
					position.x = b1.getPosition().x + b1.getWidth() + radius;
				return true;
			}
		}
		return false;
	}

	/**
	 * Sprawdza czy pi�ka zderzy�a si� z powerupem
	 * 
	 * @param pp - powerup z kt�rym sprawdzana jest kolizja
	 * @return true je�eli pi�ka zderzy�a si� z powerupem, false w przeciwnym przypadku
	 */
	public boolean powerupCollisionCheck(Powerup pp) {
		if (touchByPlayer == -1)//pi�ka nie jest przypisana do gracza
			return false;
		return Utility.CircleRectangleCollision(position, radius, pp.getPosition(), pp.getSize(), pp.getSize());

	}

	/**
	 * Sprawdza czy pi�ka znalaz�a si� poza obszarem gry i aktualizuje wynik
	 * 
	 * @param score - tablica wynik�w
	 * @return - true je�eli wynik zosta� aktualizowany, false winnych przypadkach
	 */
	public boolean addToScore(int score[]) {
		if (position.x < 0) {
			++score[0];
			return true;
		} else if (position.x > Board.WIDTH) {
			++score[1];
			return true;
		}
		return false;
	}

	
	/**
	 * Zmienia trajektori� pi�ki w zale�no�ci od pr�dko�ci bumpera
	 * 
	 * @param b - bumper kt�ry wp�ywa na pi�k�
	 */
	private void spin(Bumper b) {
		if (b.getLastSpeed() < 0) {// board goes up
			if (velocity.y < 0)
				velocity.accelerate(0, (int) (-1 * FRICTION * b.getLastSpeed()));
			else
				velocity.accelerate(0, (int) (FRICTION * b.getLastSpeed()));
		} else {// board goes down
			if (velocity.y < 0)
				velocity.accelerate(0, (int) (-1 * FRICTION * b.getLastSpeed()));
			else
				velocity.accelerate(0, (int) (FRICTION * b.getLastSpeed()));
		}
		if (velocity.x < 0)
			velocity.accelerate(-1, 0);
		else
			velocity.accelerate(1, 0);
	}

}
