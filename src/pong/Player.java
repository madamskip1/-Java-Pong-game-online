package pong;

import java.awt.Graphics2D;

public class Player {
	
	public Bumper bumper;
	public Mouse mouse;
	
	public Player(int bY1, int bX1, int bY2, int bX2) {
		bumper = new Bumper(bY1, bX1, bY2, bX2);
		mouse = new Mouse();
	}
	
	
	
	public void moveBumper(int fieldY) {
		int mY = mouse.loc.getY();
		int cY = bumper.getCentre().getY();
		
		if(bumper.getTop()>0 || bumper.getBot()<fieldY) {
			bumper.translate(0, (mY-cY)/15);			
		}
	}

	public void draw(Graphics2D g2d) {
		bumper.draw(g2d);
	}
	

}
