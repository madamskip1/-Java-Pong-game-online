package server;

import java.util.Iterator;
import java.util.Vector;

public class Powerups {
	public static final long TIME_BEFORE_FIRST_PU = 2;
	public static final long DEFAULT_TIME_BETWEEN_PU = 2;
	public static final int DEFAULT_CHANCE_TO_SPAWN = 5; // <1,1000>/1000 each frame

	static private Player players[];
	private Effects Effects;

	public enum PowerupFor {
		BALL, ME, OPPONENT,
	}

	public enum PowerupTypes {
		BALL_MULTIPLE_SINGLE, BALL_MULTIPLE_ALL, ME_LONG, ME_SMALL, ME_SLOW, ME_FAST,
//		ME_BEER,
		OPPONENT_LONG, OPPONENT_SMALL, OPPONENT_SLOW, OPPONENT_FAST,
//		OPPONENT_BEER
	};

	public enum PlayerPowerupTypes {
		LONG, SMALL, SLOW, FAST,
//		BEER,
	}

	public enum BallPowerupTypes {
		MULTIPLE_SINGLE, MULTIPLE_ALL
	}

	private Vector<Powerup> powerups;
	private long minTimeBetweenPU;
	private long timeToNext;
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
		System.out.println(players.length);
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

	public void ballsCollisions(Balls balls) {// tu moze byc cos
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

	public void powerupHit(Powerup powerup, Balls balls, Ball ball) {
		Effect effect = powerup.hitBy(balls, ball);
		if (effect != null)
			Effects.add(effect);
	}

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

	public static int typeToInt(Powerups.PowerupTypes type) {
		return type.ordinal();
	}

	public static PowerupTypes intToType(int index) {
		return PowerupTypes.values()[index];
	}

	public String serialize() {
		String toReturn = "";
		Point pos;
		for (Powerup pu : powerups) {
			pos = pu.getPosition();
			toReturn += pos.x + "," + pos.y + "," + Powerups.typeToInt(pu.getType()) + ";";
		}

		return toReturn;
	}

	private boolean randIfSpawn() {
		if (Utility.randomInt(0, 1000) <= chanceToSpawn)
			return true;

		return false;
	}

	private boolean powerUpTimer(long delta) {
		timeToNext -= delta;
		if (timeToNext <= 0) {
			timeToNext = 0;
			return true;
		}

		return false;
	}

	private Powerup CreateBallPowerUp() {

		int ranInt = Utility.randomInt(0, BallPowerupTypes.values().length - 1);
		int x = ranX();
		int y = ranY();
		Powerup powerup = new powerup.BallPowerup(x, y, BallPowerupTypes.values()[ranInt]);
		return powerup;
	}

	private Powerup CreatePlayerPowerUp(PowerupFor who) {
		int ranInt = Utility.randomInt(0, PlayerPowerupTypes.values().length - 1);

		int x = ranX();
		int y = ranY();
		Powerup powerup = new powerup.PlayerPowerup(x, y, PlayerPowerupTypes.values()[ranInt], who);

		return powerup;
	}

	private int ranX() {

		// DO ZROBIENIA LEPSZE LOSOWANIE MIEJSCA

		int min, max;
		min = Board.WIDTH * 2 / 5;
		max = Board.WIDTH * 3 / 5;
		return Utility.randomInt(min, max);
	}

	private int ranY() {

		// DO ZROBIENIA LEPSZE LOSOWANIE MIEJSCA

		int min, max;
		min = Board.HEIGHT * 1 / 5;
		max = Board.HEIGHT * 4 / 5;
		return Utility.randomInt(min, max);
	}

}
