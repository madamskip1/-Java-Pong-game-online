package client;

import java.awt.Graphics2D;
import java.util.Vector;

public class Powerups {
	static private ImageLoader iLoader;
	private Vector<Powerup> powerups;

	public enum PowerupTypes
	{
		BALL_MULTIPLE_SINGLE,
		BALL_MULTIPLE_ALL,
		BALL_SLOW,
		BALL_FAST,
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
	}
	
	public Powerups()
	{
		powerups = new Vector<Powerup>();
		iLoader = new ImageLoader();
	}
	
	public int size() {
		return powerups.size();
	}
	
	public static Vector<Powerup> deserialize(String stringWithPowerups)
	{
		Vector<Powerup> toReturn = new Vector<Powerup>();
		
		String[] splitted = stringWithPowerups.split(";");
		String[] singlePowerup;
		
		int numberOfPU = splitted.length;
		PowerupTypes type;
		for (String Pu : splitted)
		{
			singlePowerup = Pu.split(",");
			type = Powerups.intToType(Integer.parseInt(singlePowerup[2]));
			toReturn.add(new Powerup(Integer.parseInt(singlePowerup[0]),
					Integer.parseInt(singlePowerup[1]),
					type, iLoader));
		}
		return toReturn;
	}
	
	public static PowerupTypes intToType(int type)
	{
		return PowerupTypes.values()[type];
	}
	
	public void setPowerups(Vector<Powerup> powers)
	{
		powerups = powers;
	}
	
	public void clearPowerups()
	{
		powerups.clear();
	}
	
	public void draw(Graphics2D g)
	{
		for (Powerup Pu : powerups) 
			Pu.draw(g);
	}
}
