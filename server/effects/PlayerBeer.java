package effects;

/**
 * Klasa efektów odwrócenia sterowania 
 */
public class PlayerBeer extends PlayerEffect
{
	public void startEffect()
	{
		Player.setBeer(true);
	}
	
	protected void expire()
	{
		Player.setBeer(false);
	}
}
