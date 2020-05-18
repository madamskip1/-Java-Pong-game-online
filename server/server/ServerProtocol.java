package server;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import miscellaneous.Player;

public class ServerProtocol {
	private final Pattern mainPattern;
	private Player player;
	private static PongServer server;
	
	public ServerProtocol(PongServer _serv)
	{
		server = _serv;
		mainPattern = Pattern.compile("(.+?);(.+?);(.+?)");
	}
	
	public void read(int PlayerID, String msg)
	{
		Matcher match = mainPattern.matcher(msg);
		String obj = match.group(1);
		String settings = match.group(2);
		String mainMsg = match.group(3);
		
		if (obj == "PLAYER")
			readPlayerProtocol(PlayerID, settings, mainMsg);
	}
	
	private void write(String msg) throws IOException
	{
		server.output(msg);
	}
	
	public void writePlayerProtocol(int PlayerID, String settings, String msg)
	{
			try {
				write("PLAYER;" + settings + "_" + PlayerID + ";" + msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void writeBallProtocol(String settings, String msg)
	{
		try {
			write("BALL;" + settings + ";" + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void writeGameStateProtocol(String settings, String msg)
	{
		try {
			write("STATE;" + settings + ";" + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readPlayerProtocol(int playerID, String settings, String msg)
	{
		if (settings == "MOVE")
		{
			// Wybraæ gracza
		//	player.move(Integer.parseInt(msg));
		}
	}
}
