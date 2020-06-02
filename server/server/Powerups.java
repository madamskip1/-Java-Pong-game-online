package server;

import java.util.Map;
import java.util.Vector;

public class Powerups {
	public static final long TIME_BEFORE_FIRST_PU = 2;
	public static final long DEFAULT_TIME_BETWEEN_PU = 2;
	public static final int DEFAULT_CHANCE_TO_SPAWN = 5; // <1,1000>/1000 each frame
	
	private Player players[];
	
	public enum PowerupFor
	{
		ME,
		OPPONENT,
		BALL
	}
	
	public enum PowerupTypes
	{
		BALL_MULTIPLE_SINGLE,
		BALL_MULTIPLE_ALL,
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
	

	//private Vector
	
	private Vector<Powerup> powerupsOnBoard;
	private long minTimeBetweenPU;
	private long timeToNext;
	private int chanceToSpawn;
	
	
	public Powerups()
	{
		powerups = new Vector<Powerup>();
		players = new Player[Game.PLAYERS];
		//PowerupTypes = new Map<PowerupFor, Vector<PowerupType>>();
		minTimeBetweenPU = DEFAULT_TIME_BETWEEN_PU;
		timeToNext = TIME_BEFORE_FIRST_PU;
		chanceToSpawn = DEFAULT_CHANCE_TO_SPAWN;
		CreateTypes();
	}
	
	public void setupPlayers(Player[] _players)
	{
		players = _players;
	}
	
	public Player getPlayer(int index)
	{
		return players[index];
	}
	
	public void trySpawn(long deltaTime)
	{
		if (powerUpTimer(deltaTime) && randIfSpawn())
		{
			// Spawnujemy 
		}
	}
	
	public static int typeToInt(Powerups.PowerupTypes type)
	{
		return type.ordinal();
	}
	
	public String serialize()
	{
		String toReturn = "";
		Point pos;
		for(Powerup pu : powerups)
		{
			pos = pu.getPosition();
			toReturn += pos.x + "," + pos.y + "," + Powerups.typeToInt(pu.getType()) + ";";
		}
		
		return toReturn;
	}
	
	private boolean randIfSpawn()
	{
		if (Utility.randomInt(0, 1000) <= chanceToSpawn)
			return true;
		
		return false;
	}
	
	private boolean powerUpTimer(long delta)
	{
		timeToNext -= delta;
		if (timeToNext <= 0)
		{
			timeToNext = 0;
			return true;
		}
		
		return false;
	}
	
	private void CreateTypes()
	{
		Powerups.PowerupTypes.ME;
	}
	
	public int size() { return powerups.size(); }
}
