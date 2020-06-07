package client;

import java.awt.Graphics2D;
import java.util.Vector;

/**
 * Klasa przechowuj�ca zbi�r powerup�w
 */
public class Powerups {
	static private ImageLoader iLoader;
	private Vector<Powerup> powerups;

	public enum PowerupTypes
	{
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
	}
	
	/**
	 * Inicjacja pustego wektora powerup�w
	 */
	public Powerups()
	{
		powerups = new Vector<Powerup>();
		iLoader = new ImageLoader();
	}
	
	/**
	 * Zwraca ilo�� powerup�w
	 */
	public int size() {
		return powerups.size();
	}
	
	/**
	 * Przetwarza odebran� wiadomo�� na wektor powerup�w
	 * 
	 * @param stringWithBalls odczytana wiadomo�� o powerupach
	 * @return nowy wektor powerup�w
	 */
	public static Vector<Powerup> deserialize(String stringWithPowerups)
	{
		Vector<Powerup> toReturn = new Vector<Powerup>();
		
		String[] splitted = stringWithPowerups.split(";");
		String[] singlePowerup;
		
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
	
	/**
	 * Przetwarza warto�� na typ powerupu
	 * 
	 * @param type
	 * @return nowy wektor pi�ek
	 */
	public static PowerupTypes intToType(int type)
	{
		return PowerupTypes.values()[type];
	}
	
	/**
	 * Ustawia nowy wektor powerup�w
	 * 
	 * @param balls wektor powerup�w
	 */
	public void setPowerups(Vector<Powerup> powers)
	{
		powerups = powers;
	}
	
	/**
	 * Zeruje ilo�� powerup�w
	 */
	public void clearPowerups()
	{
		powerups.clear();
	}
	
	/**
	 * Rysuje wszystkie powerupy
	 **/
	public void draw(Graphics2D g)
	{
		for (int i=0; i<powerups.size();++i) 
			powerups.get(i).draw(g);
	}
}
