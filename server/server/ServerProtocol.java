package server;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerProtocol {
	private final Pattern mainPattern;
	private Player players[];
	private static PongServer server;
	private Game Game;

	public ServerProtocol(PongServer _serv) {
		server = _serv;
		mainPattern = Pattern.compile("(.+?);(.+?);(.+?)");
		players = new Player[2];
	}

	public void setPlayers(Player player0, Player player1) {
		players[0] = player0;
		players[1] = player1;
	}
	
	public void setGame(Game game)
	{
		Game = game;
	}

	public void read(int PlayerID, String msg) {
		Matcher match = mainPattern.matcher(msg);
		if(match.matches())
		{
			String obj = match.group(1);
			String settings = match.group(2);
			String mainMsg = match.group(3);
	
			if (obj.equals("PLAYER"))
				readPlayerProtocol(PlayerID, settings, mainMsg);
			else if(obj.equals("ACCEPT"))
				readAcceptProtocol(PlayerID);

		}
	}

	private void write(String msg) throws IOException {
		server.output(msg);
	}

	public void writePlayerProtocol(int PlayerID, String settings, String msg) {
		try {
			write("PLAYER;" + settings + "_" + PlayerID + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeBallProtocol(String settings, String msg) {
		try {
			write("BALL;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writePowerUpProtocol(String settings, String msg) {
		try {
			write("POWERUP;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeGameStateProtocol(String settings, String msg) {
		try {
			write("STATE;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readPlayerProtocol(int playerID, String settings, String msg) {
		
		//////////////////////////////////////////////
		// 		P�tla do test�w i prezentacji  		//
		//		Dzi�ki temu ruszaja sie oba			//
		//////////////////////////////////////////////
		
		
		for(int i = 0; i < 2; i++)
		{
			if (settings.equals("DIR")) {
				if (msg.equals("UP"))
					players[playerID].goUp();
				else if (msg.equals("DOWN"))
					players[playerID].goDown();
				else if (msg.equals("NONE"))
					players[playerID].stop();

			}
			
			playerID = (playerID + 1) % 2;
		}
	}
	
	private void readAcceptProtocol(int playerID)
	{
		Game.Accepted[playerID] = true;
	}

	
}
