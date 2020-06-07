package effects;

/**
 * Klasa efektów modyfikacji prêdkoœci gracza
 */
public class PlayerSpeed  extends PlayerEffect
{
	private static final int MULTIPLE_SPEED = 2;
	
	/**
	 * Ustawia efekt dla powerupu
	 */
	public void startEffect()
	{
		switch(type)
		{
		case FAST:
			makeFast();
			break;
		case SLOW:
			makeSlow();
			break;
		default:
			throw new RuntimeException("invalid effect type");
		}
	}
	
	/**
	 * Przywraca prêdkoœæ z momentu zebrania powerupu
	 */
	protected void expire()
	{
		Player.resetSpeed();
	}
	
	/**
	 * Przyspiesza gracza
	 */
	private void makeFast()
	{
		Player.setSpeed(server.Player.DEFAULT_SPEED * MULTIPLE_SPEED);
	}
	
	/**
	 * Spowalnia gracza
	 */
	private void makeSlow()
	{
		Player.setSpeed(server.Player.DEFAULT_SPEED / MULTIPLE_SPEED);
	}

}
