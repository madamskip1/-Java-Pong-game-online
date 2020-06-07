package server;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Klasa definiuj¹ca protokó³ klienta
 */
public class ServerProtocol {
	private final Pattern mainPattern;
	private Player players[];
	private static PongServer server;
	private Game Game;

	/**
	 * Tworzy instancjê klasy dla serwera 
	 * 
	 * @param _serv - serwer dla którego protokó³ ma dzia³aæ
	 */
	public ServerProtocol(PongServer _serv) {
		server = _serv;
		mainPattern = Pattern.compile("(.+?);(.+?);(.+?)");
		players = new Player[2];
	}

	/**
	 * Ustawia graczy
	 * 
	 * @param player0 - lewy gracz
	 * @param player1 - prawy gracz
	 */
	public void setPlayers(Player player0, Player player1) {
		players[0] = player0;
		players[1] = player1;
	}
	
	/**
	 * Ustawia pole rozgrywki
	 * 
	 * @param game - ustawiane pole
	 */
	public void setGame(Game game)
	{
		Game = game;
	}

	/**
	 * Funkcja odczytuj¹ca wiadomoœci wys³ane od gracza (klienta)
	 * 
	 * @param PlayerID - id gracza
	 * @param msg - odebrana wiadomoœæ
	 */
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

	/**
	 * Wysy³a wiadomoœæ
	 * @param msg - wiadomoœæ
	 */
	private void write(String msg) throws IOException {
		server.output(msg);
	}

	/**
	 * Wysy³a informacje o graczu klientom
	 * @param PlayerID - gracz
	 * @param settings - dodatkowe ustawienia wiadomoœci
	 * @param msg - wiadomoœæ
	 */
	public void writePlayerProtocol(int PlayerID, String settings, String msg) {
		try {
			write("PLAYER;" + settings + "_" + PlayerID + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wysy³a informacje o pi³kach klientom
	 * @param settings - dodatkowe ustawienia wiadomoœci
	 * @param msg - wiadomoœæ
	 */
	public void writeBallProtocol(String settings, String msg) {
		try {
			write("BALL;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wysy³a informacje o powerupach klientom
	 * @param settings - dodatkowe ustawienia wiadomoœci
	 * @param msg - wiadomoœæ
	 */
	public void writePowerUpProtocol(String settings, String msg) {
		try {
			write("POWERUP;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wysy³a informacje o stanie gry klientom
	 * @param settings - dodatkowe ustawienia wiadomoœci
	 * @param msg - wiadomoœæ
	 */
	public void writeGameStateProtocol(String settings, String msg) {
		try {
			write("STATE;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wysy³a informacje o wyniku klientom
	 * @param settings - dodatkowe ustawienia wiadomoœci
	 * @param msg - wiadomoœæ
	 */
	public void writeScoreProtocol(String settings, String msg) {
		try {
			write("SCORE;" + settings + ";" + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void readPlayerProtocol(int playerID, String settings, String msg) {
		
		//////////////////////////////////////////////
		// 		Pêtla do testów i prezentacji  		//
		//		Dziêki temu ruszaja sie oba			//
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
	
	/**
	 * Odczytuje informacje o akceptacji gry przez gracza
	 * 
	 * @param playerID - id gracza
	 */
	private void readAcceptProtocol(int playerID)
	{
		Game.Accepted[playerID] = true;
	}

	
}
