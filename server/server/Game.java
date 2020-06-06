package server;

import java.util.concurrent.TimeUnit;

import client.Game.States;

public class Game {
	public enum States {
		INIT, INITIALIZED, RUNNING, GAMEOVER,
		ACCEPTED
	}

	public static final int PLAYERS = 2;
	private Player players[];
	private Bumper playersBumpers[];
	private ServerProtocol Protocol;
	private Balls Balls;
	private Powerups Powerups;
	private Effects Effects;
	private States State;
	public boolean Accepted[];
	private int FPS = 60;
	private int score[] = {0,0};
	private static final int MAX_SCORE = 10;
	private int winner;

	public Game(ServerProtocol _prot) {
		State = States.INIT;
		Protocol = _prot;

		Protocol.writeGameStateProtocol("INIT", "0");
		Powerups = new Powerups();
		Effects = new Effects();

		setupPlayers();
		setupFirstBall();
		Powerups.setEffects(Effects);
		Accepted = new boolean[2];
		Accepted[0] = Accepted[1] = false;
		
		Protocol.writeGameStateProtocol("INIT", "1");
		State = States.INITIALIZED;
	}

	public void setupPlayers() {
		players = new Player[PLAYERS];
		playersBumpers = new Bumper[PLAYERS];

		playersBumpers[0] = new Bumper();
		playersBumpers[1] = new Bumper();

		int yPosition = (server.Board.HEIGHT / 2) - (Bumper.DEFAULT_HEIGHT / 2);

		playersBumpers[0].setPosition(server.Board.X_PADDING, yPosition);
		players[0] = new Player(0, playersBumpers[0]);
		Protocol.writePlayerProtocol(0, "MOVE", server.Board.X_PADDING + "," + yPosition);
		Protocol.writePlayerProtocol(0, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);

		playersBumpers[1].setPosition(server.Board.WIDTH - server.Board.X_PADDING - Bumper.DEFAULT_WIDTH, yPosition);
		players[1] = new Player(1, playersBumpers[1]);
		Protocol.writePlayerProtocol(1, "MOVE",
				(server.Board.WIDTH - server.Board.X_PADDING - Bumper.DEFAULT_WIDTH) + "," + yPosition);
		Protocol.writePlayerProtocol(1, "SIZE", Bumper.DEFAULT_WIDTH + "," + Bumper.DEFAULT_HEIGHT);

		Protocol.setPlayers(players[0], players[1]);
		Powerups.setupPlayers(players[0], players[1]);

	}

	public void setupFirstBall() {
		Balls = new Balls();
		Balls.setGame(this);
		Balls.addBall(Ball.generateBall(server.Board.WIDTH, server.Board.HEIGHT));
		Protocol.writeBallProtocol(("POSITION;" + Balls.size()), Balls.serialize());
	}
	

	public void checkAccepted()
	{
		if (Accepted[0] == true && Accepted[1] == true)
			State = States.ACCEPTED;
	}
	
	public void initalized()
	{
		while (State == States.INITIALIZED)
		{
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			checkAccepted();
		}
		start();
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
		Protocol.writeGameStateProtocol("OVER", Integer.toString(winner));
		State = Game.States.INITIALIZED;
		Accepted[0] = Accepted[1] = false;
		initalized();
	}
	
	public checkEnd()
	{
		if (score[0] >= MAX_SCORE)
		{
			winner = 0;
			if (score[1] >= MAX_SCORE)
				winner = 2;
			
			State = Game.States.GAMEOVER;
		}
		else if (score[1] >= MAX_SCORE)
		{
			winner = 1;
			if (score[0] >= MAX_SCORE)
				winner = 2;
			
			State = Game.States.GAMEOVER;
		}
		
	}

	private void update(long deltaTime) {

		Balls.update();
		players[0].update();
		players[1].update();

		Powerups.ballsCollisions(Balls);
		Balls.bumperCollisions(playersBumpers[0], playersBumpers[1]);
		Effects.update(deltaTime);
		Powerups.trySpawn(deltaTime);
		Balls.addIfZero();
		Balls.updateScore(score);
		checkEnd();
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
		Protocol.writeScoreProtocol("SET", score[0] + "," + score[1]);
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
		
		gameOver();

	}
}
