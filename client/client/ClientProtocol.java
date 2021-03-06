package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa definiuj�ca protok� klienta
 */
public class ClientProtocol {
	private final Pattern mainPattern;
	private final Pattern twoPattern;
	private Game game;
	private DataOutputStream Output;
	
	/**
	 * Tworzy instancj� klasy obs�uguj�cej nadawanie i odbieranie pakiet�w
	 * 
	 * @param _game gra dla kt�rej b�dzie nast�powa� wymiana pakiet�w
	 * @param _output wychodz�cy strumie� danych
	 */
	public ClientProtocol(Game _game, DataOutputStream _output) {
		game = _game;
		Output = _output;
		mainPattern = Pattern.compile("(.+?);(.+?);(.+?)");
		twoPattern = Pattern.compile("(.+?);(.+?)");
	}

	/**
	 * Kieruje odebran� wiadomo�� do odpowiedniego przetworzenia
	 * 
	 * @param msg odebrana wiadomo��
	 */
	public void read(String msg) {
		Matcher match = mainPattern.matcher(msg);
		if (match.matches()) {
			String obj = match.group(1);
			String settings = match.group(2);
			String mainMsg = match.group(3);

			if (obj.equals("PLAYER"))
				readPlayerProtocol(settings, mainMsg);

			else if (obj.equals("BALL"))
				readBallProtocol(settings, mainMsg);

			else if (obj.equals("STATE"))
				readStateProtocol(settings, mainMsg);

			else if (obj.equals("POWERUP"))
				readPowerupProtocol(settings, mainMsg);

			else if (obj.equals("SCORE"))
				readScoreProtocol(settings, mainMsg);

		}
	}

	/**
	 * Wysy�a wiadomo�� do serwera
	 * 
	 * @param msg wysy�ana wiadomo��
	 */
	private void write(String msg) throws IOException {
		Output.flush();
		Output.writeUTF(msg);
	}

	/**
	 * Parsuje wiadomo�� dotycz�c� graczy i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	private void readPlayerProtocol(String settings, String mainMsg) {
		if (settings.equals("YOU")) {
			game.setYou(Integer.parseInt(mainMsg));
			game.init();
		} else if (settings.equals("OPPONENT"))
			game.initialized();
		else if (settings.contains("MOVE")) {
			String[] pos = mainMsg.split(",");
			int player = getPlayerId(settings);
			int x = Integer.parseInt(pos[0]);
			int y = Integer.parseInt(pos[1]);
			game.PlayerPos(player, x, y);
		} else if (settings.contains("SIZE")) {
			String[] size = mainMsg.split(",");
			int player = getPlayerId(settings);
			int width = Integer.parseInt(size[0]);
			int height = Integer.parseInt(size[1]);
			game.PlayerSize(player, width, height);
		}
	}

	/**
	 * Parsuje wiadomo�� dotycz�c� pi�ek i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	private void readBallProtocol(String settings, String mainMsg) {
		if (settings.equals("POSITION")) {
			Matcher match = twoPattern.matcher(mainMsg);
			if (match.matches())
				game.Balls(Integer.parseInt(match.group(1)), match.group(2));
			else
				game.zeroBalls(); // inaczej nie znikna
		}
	}

	/**
	 * Parsuje wiadomo�� dotycz�c� powerup�w i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	private void readPowerupProtocol(String settings, String mainMsg) {
		if (settings.equals("POSITION")) {
			Matcher match = twoPattern.matcher(mainMsg);
			if (match.matches())
				game.Powerups(Integer.parseInt(match.group(1)), match.group(2));
			else
				game.zeroPowerups(); // inaczej nie znikna
		}
	}

	/**
	 * Parsuje wiadomo�� dotycz�c� powerup�w i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	private void readStateProtocol(String settings, String mainMsg) {
		if (settings.equals("INIT")) {
		} else if (settings.equals("START"))
			game.start();
		else if (settings.equals("OVER"))
			game.over(Integer.parseInt(mainMsg));
	}

	/**
	 * Parsuje wiadomo�� dotycz�c� wyniku i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	private void readScoreProtocol(String settings, String mainMsg) {
		if (settings.equals("SET")) {
			String[] split = mainMsg.split(",");
			int sc[] = { Integer.parseInt(split[0]), Integer.parseInt(split[1]) };
			Game.Scores[0].setScore(sc[0]);
			Game.Scores[1].setScore(sc[1]);
		}
	}

	/**
	 * Tworzy i wysy�a wiadomo�� dotycz�c� gracza
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomo��
	 */
	public void writePlayerProtocol(String settings, String mainMsg) {
		try {
			write("PLAYER;" + settings + ";" + mainMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tworzy i wysy�a wiadomo�� o przyj�ciu wyzwania
	 * 
	 */
	public void writeAcceptProtocol() {
		try {
			write("ACCEPT;1;1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Odczytuje z wiadomo�ci id gracza
	 * 
	 * @return id gracza
	 */
	private int getPlayerId(String msg) {
		return Integer.parseInt((msg.split("_"))[1]);
	}
}
