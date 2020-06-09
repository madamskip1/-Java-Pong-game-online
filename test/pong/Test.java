package pong;

import static org.junit.jupiter.api.Assertions.*;
import server.*;
import server.Ball;
import server.Powerup;
import server.Powerups;
import client.*;

class Test {

	@org.junit.jupiter.api.Test
	void testServerBall() {
		server.Ball ball = new server.Ball();
		ball.correctSpeed();
		assertTrue(ball.getVelocity().getX() != 0); // pi³ka bêdzie siê poruszaæ w stronê bumpera

		ball.setVelocity(10, 0);
		ball.setPosition(50, 50);
		assertTrue(ball.getVelocity().getX() == 10);
		assertTrue(ball.getPosition().getX() == 50);
		assertTrue(ball.getPosition().getY() == 50);

		server.Bumper bumperR = new server.Bumper();
		bumperR.setPosition(100, 50);
		assertTrue(bumperR.getPosition().getX() == 100);
		assertTrue(bumperR.getPosition().getY() == 50);

		server.Bumper bumperL = new server.Bumper();
		bumperL.setPosition(0, 50);

		ball.update();
		assertFalse(ball.bumperCollision(bumperL, bumperR));

		ball.update();
		assertFalse(ball.bumperCollision(bumperL, bumperR));

		ball.update();
		assertFalse(ball.bumperCollision(bumperL, bumperR));

		ball.update(); // odbicie od prawego
		assertTrue(ball.bumperCollision(bumperL, bumperR));

		assertTrue(ball.getPosition().getX() == 90);
		assertTrue(ball.getPosition().getX() + ball.getRadius() >= 100); // czy aby na pewno od prawego
		assertTrue(ball.getVelocity().getX() == -11);// nast¹pi³o odbicie

		ball.update();
		ball.update();
		ball.update();
		ball.update();
		ball.update();
		ball.update();
		assertTrue(ball.bumperCollision(bumperL, bumperR));// kolizja z lewym bumperem
		assertTrue(ball.getVelocity().getX() == 12);// nast¹pi³o odbicie

		server.Powerup powerup = new server.Powerup(0, 50);
		ball.update();
		ball.update();
		ball.update();
		assertTrue(ball.powerupCollisionCheck(powerup));// kolizja z powerupem

		ball.setVelocity(10, 10);
		server.Vector2d savedVel = ball.getVelocity().clone();
		server.Vector2d setVel = new server.Vector2d(5431, 87654);

		ball.saveVelocity();
		ball.setVelocity(setVel);

		assertTrue(ball.getVelocity().getX() == setVel.getX());
		assertTrue(ball.getVelocity().getY() == setVel.getY());

		assertFalse(ball.getVelocity().getX() == savedVel.getX());
		assertFalse(ball.getVelocity().getY() == savedVel.getY());

		ball.returnToPreviousVelocity();
		assertFalse(ball.getVelocity().getX() == setVel.getX());
		assertFalse(ball.getVelocity().getY() == setVel.getY());

		assertTrue(ball.getVelocity().getX() == savedVel.getX());
		assertTrue(ball.getVelocity().getY() == savedVel.getY());

		ball = server.Ball.generateBall(1000, 1000);
		assertTrue(ball.getVelocity().getX() != 0);

		server.Ball nb = ball.clone();
		assertTrue(nb.getPosition().getX() == ball.getPosition().getX());
		assertTrue(nb.getPosition().getY() == ball.getPosition().getY());
		assertTrue(nb.getVelocity().getX() == ball.getVelocity().getX());
		assertTrue(nb.getVelocity().getY() == ball.getVelocity().getY());

		int score[] = { 0, 0 };
		ball.addToScore(score);
		assertEquals(0, score[0]);
		assertEquals(0, score[1]);

		ball.setPosition(1500, 0);
		ball.addToScore(score);
		assertEquals(0, score[0]);
		assertEquals(1, score[1]);
	}

	@org.junit.jupiter.api.Test
	void testProtocol() {
		server.Ball sball = new server.Ball();
		server.Balls sballs = new server.Balls();
		sballs.addBall(sball);
		
		String ballString = sballs.serialize();
		client.Balls balls = new client.Balls();
		assertTrue(balls.size() == 0);
		balls.setBalls(client.Balls.deserialize(ballString));
		assertTrue(balls.size() == 1);//dodano pi³kê po stronie klienta

		server.Ball sball1 = new server.Ball();
		server.Ball sball2 = new server.Ball();
		sballs.addBall(sball1);
		sballs.addBall(sball2);
		ballString = sballs.serialize();
		balls.setBalls(client.Balls.deserialize(ballString));
		assertTrue(balls.size() == 3);//dodano 2 pi³ki po stronie klienta

		
		
		
		server.Powerups spowerups = new server.Powerups();
		client.Powerups cpowerups = new client.Powerups();
		
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		spowerups.trySpawn(1000000000);
		String powerupsString = spowerups.serialize();
		assertTrue(cpowerups.size() == 0 );
		cpowerups.setPowerups(client.Powerups.deserialize(powerupsString));
		assertTrue(cpowerups.size() != 0 );//losowe wiec moze nie przejsc; po zmianie linii 181 w server.Powerups przechodzi
	}
}
