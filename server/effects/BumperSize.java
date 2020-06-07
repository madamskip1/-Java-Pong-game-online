package effects;


/**
 * Klasa efektów modyfikacji wielkoœci bumpera
 */
public class BumperSize extends PlayerEffect
{
	private static final int MULTIPLE_SIZE = 2;
	
	/**
	 * Ustawia efekt dla powerupu
	 */
	public void startEffect()
	{
		switch (type)
		{
		case LONG:
			makeLong();
			break;
		case SMALL:
			makeSmall();
			break;
		default:
			throw new RuntimeException("invalid effect type");
		}
		
		writeSize();
	}
	
	/**
	 * Przywraca rozmiar z momentu zebrania powerupu
	 */
	protected void expire()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH, server.Bumper.DEFAULT_HEIGHT);
		writeSize();
	}
	
	/**
	 * Powieksza bumper
	 */
	private void makeLong()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH,
				server.Bumper.DEFAULT_HEIGHT * MULTIPLE_SIZE);
	}
	
	/**
	 * Zmniejsza bumper
	 */
	private void makeSmall()
	{
		setBumperSize(server.Bumper.DEFAULT_WIDTH,
				server.Bumper.DEFAULT_HEIGHT / MULTIPLE_SIZE);
	}
	
	/**
	 * Ustawia rozmiar bumpera
	 * @param width - ustawiana szerokoœæ
	 * @param height - ustawiana wysokoœæ
	 */
	private void setBumperSize(int width, int height)
	{
		server.Bumper bumper = Player.getBumper();
		bumper.setHeight(height);
		bumper.setWidth(width);
	}
	
	/**
	 * Wysy³a klientom nowy rozmiar bumpera
	 */
	private void writeSize()
	{
		server.Bumper bump = Player.getBumper();
		server.PongServer.protocol.writePlayerProtocol(Player.getPlayerID(), "SIZE", bump.getWidth() + "," + bump.getHeight());
	}
}
