package effects;

/**
 * Klasa efektów modyfikacji gracza
 */
public class PlayerEffect extends server.Effect
{
	protected server.Player Player;
	
	public void setPlayer(server.Player player)
	{
		Player = player;
	}
	
	
}
