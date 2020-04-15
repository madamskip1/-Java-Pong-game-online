package pong;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse  implements MouseMotionListener {

	public Point loc;
	
	public Mouse() {
		loc = new Point(0,0);
	}

	public void mouseDragged(MouseEvent e) {
		loc = new Point(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		loc = new Point(e.getX(), e.getY());
	}
}
