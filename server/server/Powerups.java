package server;

import java.util.Iterator;
import java.util.Vector;

/**
 * Klasa przechowuj�ca powerupy znajduj�ce si� na planszy
 */
public class Powerups {
	public static final int TIME_BEFORE_FIRST_PU = 4; // sec
	public static final int DEFAULT_TIME_BETWEEN_PU = 5; // sec
	public static final int DEFAULT_CHANCE_TO_SPAWN = 5; // <1,1000>/1000 each frame

	public enum PowerupFor {
		BALL, ME, OPPONENT,
	}

	public enum PowerupTypes {
		BALL_MULTIPLE_SINGLE,
		BALL_MULTIPLE_ALL,
		BALL_FAST,
		BALL_SLOW,
		ME_LONG,
		ME_SMALL,
		ME_SLOW,
		ME_FAST,
		ME_BEER,
		OPPONENT_LONG,
		OPPONENT_SMALL,
		OPPONENT_SLOW,
		OPPONENT_FAST,
		OPPONENT_BEER
	};

	public enum PlayerPowerupTypes {
		LONG,
		SMALL,
		SLOW,
		FAST,
		BEER,
	}

	public enum BallPowerupTypes {
		MULTIPLE_SINGLE,
		MULTIPLE_ALL,
		FAST,
		SLOW
	}

	static private Player players[];
	private Vector<Powerup> powerups;
	private Effects Effects;
	private double timeToNext;
	private int minTimeBetweenPU;
	private int chanceToSpawn;

	
	public Powerups() {
		powerups = new Vector<Powerup>();
		players = new Player[Game.PLAYERS];
		minTimeBetweenPU = DEFAULT_TIME_BETWEEN_PU;
		timeToNext = TIME_BEFORE_FIRST_PU;
		chanceToSpawn = DEFAULT_CHANCE_TO_SPAWN;
	}

	public void setupPlayers(Player _player1, Player _player2) {
		players[0] = _player1;
		players[1] = _player2;
	}

	public static Player getPlayer(int index) {
		return players[index];
	}

	public void setEffects(Effects effects) {
		Effects = effects;
	}

	public int size() {
		return powerups.size();
	}

	/**
	 * Sprawdza kolizje wszystkich pi�ek z powerupami
	 * @param balls - wszystkie pi�ki
	 * */
	public void ballsCollisions(Balls balls) {
		for (Iterator<Powerup> it = powerups.iterator(); it.hasNext();) {
			Powerup p = it.next();
			for (int i = 0; i < balls.size(); ++i) {
				Ball b = balls.getBall(i);
				if (b.powerupCollisionCheck(p)) {
					powerupHit(p, balls, b);
					it.remove();
					break;
				}
			}
		}
	}

	/**
	 * Aktywuje efekt powerupu
	 * @param powerup - aktywowany powerup
	 * @param balls - wszsytkie pi�ki
	 * @param ball - pi�ka kt�ra aktywowa�a powerup
	 * */
	public void powerupHit(Powerup powerup, Balls balls, Ball ball) {
		Effect effect = powerup.hitBy(balls, ball);

		if (effect != null)
			Effects.add(effect);
	}

	/**
	 * Pr�buje wygenerowa� powerup. Je�eli min�� wystarczaj�cy czas od 
	 * ostatniego wygenerowanego, oraz wylosowana zostanie odpowiednia warto��
	 * powerup zostaje wygenerowany.
	 * @param deltaTime - czas modyfikuj�cy timer
	 * */
	public void trySpawn(long deltaTime) {
		if (powerUpTimer(deltaTime) && randIfSpawn()) {
			int ranInt;
			ranInt = Utility.randomInt(0, PowerupFor.values().length - 1);
			switch (PowerupFor.values()[ranInt]) {
			case BALL:
				powerups.add(CreateBallPowerUp());
				break;
			case ME:
				powerups.add(CreatePlayerPowerUp(PowerupFor.ME));
				break;
			case OPPONENT:
				powerups.add(CreatePlayerPowerUp(PowerupFor.OPPONENT));
				break;
			default:
				return;
			}

			timeToNext = minTimeBetweenPU;
		}
	}

	/**
	 * Zamienia typ powerupu na warto��
	 * @param type - typ 
	 * @return warto��
	 * */
	public static int typeToInt(Powerups.PowerupTypes type) {
		return type.ordinal();
	}

	/**
	 * Zamienia warto�� na typ powerupu
	 * @param index - warto�� do przeliczenia
	 * @return typ powerupu
	 * */
	public static PowerupTypes intToType(int index) {
		return PowerupTypes.values()[index];
	}

	/**
	 * Zapisuje stan instancji klasy tak aby mo�na by�o je wys�a� do klient�w
	 * 
	 * @return wiadomo�� do wys�ania
	 * */
	public String serialize() {
		String toReturn = "";
		Point pos;
		for (Powerup pu : powerups) {
			pos = pu.getPosition();
			toReturn += pos.x + "," + pos.y + "," + Powerups.typeToInt(pu.getType()) + ";";
		}
		return toReturn;
	}

	/**
	 * Funkcja dodaj�ca losowo�� do generacji
	 * 
	 * @return true/ false losowo
	 */
	private boolean randIfSpawn() {
		if (Utility.randomInt(0, 1000) <= chanceToSpawn)
			return true;

		return false;
	}

	/**
	 * Funkcja sprawdzaj�ca czy mo�na wygenerowa� ju� nowy powerup

	 * @return true je�li mo�na, false je�li nie
	 */
	private boolean powerUpTimer(long delta) {
		timeToNext -= (double) delta / 1000000000;
		if (timeToNext <= 0) {
			timeToNext = 0;
			return true;
		}

		return false;
	}

	/**
	 * Tworzy powerup dla pi�ki
	 * @return zwraca stworzony powerup
	 */
	private Powerup CreateBallPowerUp() {

		int ranInt = Utility.randomInt(0, BallPowerupTypes.values().length - 1);
		int x = ranX();
		int y = ranY();

		Powerup powerup = new powerup.BallPowerup(x, y, BallPowerupTypes.values()[ranInt]);
		return powerup;
	}

	/**
	 * Tworzy powerup dla gracza
	 * 
	 * @param who - na siebie czy na przeciwnika
	 * @return zwraca stworzony powerup
	 */
	private Powerup CreatePlayerPowerUp(PowerupFor who) {
		int ranInt = Utility.randomInt(0, PlayerPowerupTypes.values().length - 1);

		int x = ranX();
		int y = ranY();

		Powerup powerup = new powerup.PlayerPowerup(x, y, PlayerPowerupTypes.values()[ranInt], who);

		return powerup;
	}
	
	/**
	 * Losuje po�o�enie x powerupu
	 */
	private int ranX() 
	{
		int min, max;
		min = Board.WIDTH * 2 / 5;
		max = Board.WIDTH * 3 / 5;
		return Utility.randomInt(min, max);
	}

	/**
	 * Losuje po�o�enie y powerupu
	 */
	private int ranY()
	{
		int min, max;
		min = Board.HEIGHT * 1 / 5;
		max = Board.HEIGHT * 4 / 5;
		return Utility.randomInt(min, max);
	}

}
