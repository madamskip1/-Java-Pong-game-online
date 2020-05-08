package pong;

import java.awt.Graphics2D;

public class Player {
	
	public Bumper bumper;
	public Mouse mouse;
	
	public Player(int x, int y) {
		bumper = new Bumper(x, y);
		mouse = new Mouse();
	}
	
	
	
	public void moveBumper(int fieldY) {
		int mY = mouse.loc.getY();
		int cY = bumper.getCentre().getY();
		
		if(bumper.getPosition().getY() > 0 || (bumper.getPosition().getY() + bumper.getHeight()) < fieldY) {
			bumper.translate(0, (mY-cY)/15);			
		}
	}

	public void draw(Graphics2D g2d) {
		bumper.draw(g2d);
	}
	

}
