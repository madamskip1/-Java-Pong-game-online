package powerups;

public class PlayerPowerup extends server.Powerup
{

	public PlayerPowerup(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	
	public void hitBy(server.Balls balls, int index)
	{
		
	}
	
	private server.Player forPlayer(int index)
	{

		if (For == server.Powerups.PowerupFor.ME)
			return Powerups.getPlayer(index);

		return Powerups.getPlayer((index + 1) % 2);
			
	}
	
	private void Long(server.Player player)
	{
		
	}
}
