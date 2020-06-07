package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Klasa definiuj¹ca protokó³ klienta
 */
public class ClientProtocol {
	private final Pattern mainPattern;
	private final Pattern twoPattern;
	private Game game;
	private DataOutputStream Output;
	
	/**
	 * Tworzy instancjê klasy obs³uguj¹cej nadawanie i odbieranie pakietów
	 * 
	 * @param _game gra dla której bêdzie nastêpowaæ wymiana pakietów
	 * @param _output wychodz¹cy strumieñ danych
	 */
	public ClientProtocol(Game _game, DataOutputStream _output) {
		game = _game;
		Output = _output;
		mainPattern = Pattern.compile("(.+?);(.+?);(.+?)");
		twoPattern = Pattern.compile("(.+?);(.+?)");
	}

	/**
	 * Kieruje odebran¹ wiadomoœæ do odpowiedniego przetworzenia
	 * 
	 * @param msg odebrana wiadomoœæ
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
	 * Wysy³a wiadomoœæ do serwera
	 * 
	 * @param msg wysy³ana wiadomoœæ
	 */
	private void write(String msg) throws IOException {
		Output.flush();
		Output.writeUTF(msg);
	}

	/**
	 * Parsuje wiadomoœæ dotycz¹c¹ graczy i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
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
	 * Parsuje wiadomoœæ dotycz¹c¹ pi³ek i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
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
	 * Parsuje wiadomoœæ dotycz¹c¹ powerupów i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
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
	 * Parsuje wiadomoœæ dotycz¹c¹ powerupów i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
	 */
	private void readStateProtocol(String settings, String mainMsg) {
		if (settings.equals("INIT")) {
		} else if (settings.equals("START"))
			game.start();
		else if (settings.equals("OVER"))
			game.over(Integer.parseInt(mainMsg));
	}

	/**
	 * Parsuje wiadomoœæ dotycz¹c¹ wyniku i
	 * ustawia odpowiednie pola
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
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
	 * Tworzy i wysy³a wiadomoœæ dotycz¹c¹ gracza
	 * 
	 * @param settings dodatkowe ustawienia
	 * @param mainMsg wiadomoœæ
	 */
	public void writePlayerProtocol(String settings, String mainMsg) {
		try {
			write("PLAYER;" + settings + ";" + mainMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tworzy i wysy³a wiadomoœæ o przyjêciu wyzwania
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
	 * Odczytuje z wiadomoœci id gracza
	 * 
	 * @return id gracza
	 */
	private int getPlayerId(String msg) {
		return Integer.parseInt((msg.split("_"))[1]);
	}
}
