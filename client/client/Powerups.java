package client;

import java.awt.Graphics2D;
import java.util.Vector;

/**
 * Klasa przechowuj¹ca zbiór powerupów
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
	 * Inicjacja pustego wektora powerupów
	 */
	public Powerups()
	{
		powerups = new Vector<Powerup>();
		iLoader = new ImageLoader();
	}
	
	/**
	 * Zwraca iloœæ powerupów
	 * @return iloœæ powerupów
	 */
	public int size() {
		return powerups.size();
	}
	
	/**
	 * Przetwarza odebran¹ wiadomoœæ na wektor powerupów
	 * 
	 * @param stringWithPowerups - odczytana wiadomoœæ o powerupach
	 * @return nowy wektor powerupów
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
	 * Przetwarza wartoœæ na typ powerupu
	 * 
	 * @param type - który z kolei efekt
	 * @return nowy wektor pi³ek
	 */
	public static PowerupTypes intToType(int type)
	{
		return PowerupTypes.values()[type];
	}
	
	/**
	 * Ustawia nowy wektor powerupów
	 * 
	 * @param powers - wektor powerupów
	 */
	public void setPowerups(Vector<Powerup> powers)
	{
		powerups = powers;
	}
	
	/**
	 * Zeruje iloœæ powerupów
	 */
	public void clearPowerups()
	{
		powerups.clear();
	}
	
	/**
	 * Rysuje wszystkie powerupy
	 * @param g - renderer
	 **/
	public void draw(Graphics2D g)
	{
		for (int i=0; i<powerups.size();++i) 
			powerups.get(i).draw(g);
	}
}
