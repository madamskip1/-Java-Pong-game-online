package effects;

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
