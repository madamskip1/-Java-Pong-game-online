package client;

import java.util.Vector;

/**
 * Klasa implementuj�ca zachowanie rozgrywki
 */
public class Game {
	private final int REFRESH_TIME = 10;

	ClientProtocol Protocol;
	private Player Players[];
	private int you;
	private int opponent;
	private Window Window;
	private TopPanel TopPanel;
	private Board Board;
	private Keyboard Keyboard;
	private Balls Balls;
	private Powerups Powerups;
	protected static Score Scores[];

	/**
	 * Stany w jakich gra mo�e si� znajdowa�
	 */
	public enum States {
		BEFORE_INIT, INIT, ACCEPTED, INITIALIZED, RUNNING, GAMEOVER, LOSS, WIN, DRAW
	}

	protected static States State;

	/**
	 * Tworzy instancj� gry
	 */
	public Game() {
		State = States.BEFORE_INIT;
		Players = new Player[2];
		Players[0] = new Player();
		Players[1] = new Player();
		Balls = new Balls();
		Powerups = new Powerups();
	}

	/**
	 * Ustawia protok� komunikacji klienta
	 */
	public void setProtocol(ClientProtocol _prot) {
		Protocol = _prot;
	}

	/**
	 * Ustawia kt�rym graczem (lewym czy prawym) jest
	 */
	public void setYou(int num) {
		you = num;
		opponent = (num + 1) % 2;
	}

	/**
	 * Funkcja przetwarzaj�ca odebran� wiadomo�� zawieraj�ca informacje dotycz�ce
	 * pi�ek i ustawiaj�ca je w kliencie
	 * 
	 * @param numOfBalls  liczba pi�ek w grze
	 * @param ballsString odebrana wiadomo�� opisuj�ca pi�ki
	 */
	public void Balls(int numOfBalls, String ballsString) {
		Vector<Ball> newBalls = client.Balls.deserialize(ballsString);
		Balls.setBalls(newBalls);
	}

	/**
	 * Funkcja przetwarzaj�ca odebran� wiadomo�� zawieraj�ca informacje dotycz�ce
	 * pi�ek i ustawiaj�ca je w kliencie
	 * 
	 * @param numOfBalls  liczba pi�ek w grze
	 * @param ballsString odebrana wiadomo�� opisuj�ca pi�ki
	 */
	public void Powerups(int numOfPowerups, String powerupsString) {
		Vector<Powerup> newPowers = client.Powerups.deserialize(powerupsString);
		Powerups.setPowerups(newPowers);
	}

	/**
	 * Funkcja ustawiaj�ca pozycj� bumpera konkretnego gracza
	 * 
	 * @param player - kt�ry gracz, 0 - lewy, 1 - prawy
	 * @param x      - pierwsza wsp�rz�dna
	 * @param y      - druga wsp�rz�dna
	 */
	public void PlayerPos(int player, int x, int y) {
		Players[player].setPosition(x, y);
	}

	/**
	 * Funkcja ustawiaj�ca rozmiar bumpera konkretnego gracza
	 * 
	 * @param player - kt�ry gracz, 0 - lewy, 1 - prawy
	 * @param width  - szeroko��
	 * @param height - wysoko��
	 */
	public void PlayerSize(int player, int width, int height) {
		Players[player].setSize(width, height);
	}

	/**
	 * Funkcja inicjalizuj�ca rozgrywk�
	 */
	public void init() {
		State = States.INIT;

		Keyboard = new Keyboard(this);
		Window = new Window(Keyboard);
		Scores = new Score[2];

		if (you == 0) {
			Scores[0] = new Score("You");
			Scores[1] = new Score("Opponent");
		} else {
			Scores[1] = new Score("You");
			Scores[0] = new Score("Opponent");
		}

		TopPanel = new TopPanel(Scores[0], Scores[1]);
		Board = new Board(Keyboard);
		Board.setBalls(Balls);
		Board.setBumper(Players[0].getBumper(), 0);
		Board.setBumper(Players[1].getBumper(), 1);
		Board.setPowerups(Powerups);
		Window.setBoard(Board);
		Window.setTopPanel(TopPanel);
		Window.createAndShowGUI();
		Board.repaint();
	}

	/**
	 * Po zainicjowaniu rozgrywki ustawiany jest stan oraz przemalowana plansza
	 */
	public void initialized() {
		State = States.INITIALIZED;
		Board.repaint();
	}

	/**
	 * G��wna funkcja zawieraj�ca p�tl� od�wie�aj�c� pole do zako�czenia
	 */
	public void start() {
		State = States.RUNNING;

		Thread loop = new Thread() {
			public void run() {
				gameLoop();
			}
		};

		loop.start();
	}

	/**
	 *Funkcja wywo�ywana przy zako�czeniu rozgrywki
	 *
	 *@param winner - kt�ry gracz wygra�, 0 - lewy, 1 - prawy
	 */
	public void over(int winner) {
		if (winner == you)
			State = Game.States.WIN;
		else if (winner == opponent)
			State = Game.States.LOSS;
		else
			State = Game.States.DRAW;
		Board.repaint();
	}

	/**
	 * G��wna p�tla gry, dop�ki trwa rozgrywka, plansza jest od�wie�ana
	 */
	private void gameLoop() {
		while (State == States.RUNNING) {
			try {
				Thread.sleep(REFRESH_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Board.repaint();
			TopPanel.repaint();
		}
		Board.repaint();
		TopPanel.repaint();
	}

	/**
	 * Funkcja wysy�aj�ca informacj� o naci�ni�ciu klawisza do serwera
	 * 
	 * @param code - kod wci�ni�tego klawisza
	 */
	public void keyPressed(int code) {
		switch (code) {
		case 38:
		case 87:
			Protocol.writePlayerProtocol("DIR", "UP");
			break;
		case 40:
		case 83:
			Protocol.writePlayerProtocol("DIR", "DOWN");
			break;
		}
	}

	/**
	 * Funkcja wysy�aj�ca informacj� o puszczeniu wci�ni�tego klawisza do serwera
	 * 
	 * @param code - kod puszczonego klawisza
	 */
	public void keyReleased(int code) {
		switch (code) {
		case 38:
		case 87:
		case 40:
		case 83:
			Protocol.writePlayerProtocol("DIR", "NONE");
			break;
		case 32:
			accept();
			break;
		}
	}

	public void accept() {
		if (State != Game.States.RUNNING || State != Game.States.BEFORE_INIT || State != Game.States.INIT) {
			State = Game.States.ACCEPTED;
			Board.repaint();
			Protocol.writeAcceptProtocol();
		}
	}

	/**
	 * Funkcja zeruj�ca ilo�� pi�ek na planszy
	 */
	public void zeroBalls() {
		Balls.clearBalls();
	}

	/**
	 * Funkcja zeruj�ca ilo�� powerup�w na planszy
	 */
	public void zeroPowerups() {
		Powerups.clearPowerups();
	}
}
