package server;

import client.Game.States;

public class Game {
	public enum States {
		INIT, INITIALIZED, RUNNING, GAMEOVER
	}

	public static final int PLAYERS = 2;
	private Player players[];
	private Bumper playersBumpers[];
	private ServerProtocol Protocol;
	private Balls Balls;
	private Powerups Powerups;
	private Effects Effects;
	private States State;
	private int FPS = 60;
	private int score[] = {0,0};

	public Game(ServerProtocol _prot) {
		State = States.INIT;
		Protocol = _prot;

		Protocol.writeGameStateProtocol("INIT", "0");
		Powerups = new Powerups();
		Effects = new Effects();

		setupPlayers();
		setupFirstBall();
		Powerups.setEffects(Effects);
		Protocol.writeGameStateProtocol("INIT", "1");
		State = States.INITIALIZED;

		start();
	}

	public void setupPlayers() {
		players = new Player[PLAYERS];
		playersBumpers = new Bumper[PLAYERS];

		playersBumpers[0] = new Bumper();
		playersBumpers[1] = new Bumper();

		int yPosition = (server.Board.HEIGHT / 2) - (Bumper.DEFAULT_HEIGHT / 2);

		playersBumpers[0].setPosition(server.Board.X_PADDING, yPosition);
		players[0] = new Player(playersBumpers[0]);
		Protocol.writePlayerProtocol(0, "MOVE", server.Board.X_PADDING + "," + yPosition);
		Protocol.writePlayerProtocol(0, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);

		playersBumpers[1].setPosition(server.Board.WIDTH - server.Board.X_PADDING - Bumper.DEFAULT_WIDTH, yPosition);
		players[1] = new Player(playersBumpers[1]);
		Protocol.writePlayerProtocol(1, "MOVE",
				(server.Board.WIDTH - server.Board.X_PADDING - Bumper.DEFAULT_WIDTH) + "," + yPosition);
		Protocol.writePlayerProtocol(1, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);

		Protocol.setPlayers(players[0], players[1]);
		Powerups.setupPlayers(players[0], players[1]);

	}

	public void setupFirstBall() {
		Balls = new Balls();
		Balls.addBall(Ball.generateBall(server.Board.WIDTH, server.Board.HEIGHT));
		// Balls.addBall(Ball.generateBall(server.Board.WIDTH, server.Board.HEIGHT));
		// Balls.addBall(Ball.generateBall(server.Board.WIDTH, server.Board.HEIGHT));
		// Balls.addBall(Ball.generateBall(server.Board.WIDTH, server.Board.HEIGHT));
		Protocol.writeBallProtocol(("POSITION;" + Balls.size()), Balls.serialize());
	}

	public Player[] getPlayers() {
		return players;
	}

	public void start() {

		State = States.RUNNING;
		Protocol.writeGameStateProtocol("START", "1");
		gameLoop();
	}

	public void gameOver() {
		int winner = 0;
		Protocol.writeGameStateProtocol("OVER", Integer.toString(winner));
	}

	private void update(long deltaTime) {

		Balls.update();
		players[0].update();
		players[1].update();

		Powerups.ballsCollisions(Balls);
		Balls.bumperCollisions(playersBumpers[0], playersBumpers[1]);
		Balls.updateScore(score);

		Powerups.trySpawn(deltaTime);
		// Check collision
		// Check gameEnd
	}

	private void sendEverything() {
		Protocol.writeBallProtocol(("POSITION;" + Balls.size()), Balls.serialize());
		Point pos = players[0].getPosition();
		Protocol.writePlayerProtocol(0, "MOVE", pos.x + "," + pos.y);
		pos = players[1].getPosition();
		Protocol.writePlayerProtocol(1, "MOVE", pos.x + "," + pos.y);
		Protocol.writePowerUpProtocol(("POSITION;" + Powerups.size()), Powerups.serialize());
	}

	private void gameLoop() {
		long timeBetweenUpdates = 1000000000 / FPS;
		long lastTime = System.nanoTime();
		long now;
		long update;
		long delta;

		while (State == States.RUNNING) {

			// Pêtla do poprawienia

			now = System.nanoTime();
			update = now - lastTime;
			if (update >= timeBetweenUpdates) {
				lastTime = now;
				// delta = update / timeBetweenUpdates;

				update(update);
				sendEverything();
			}

		}

	}
}
