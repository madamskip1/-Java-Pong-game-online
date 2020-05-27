package client;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 440;
	public static final int PANEL_HEIGHT = 60;
	public static final int REFRESH_T = 8;
	
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setVisible(true);

		TopPanel tPanel = new TopPanel(WIDTH, PANEL_HEIGHT);
		Board board = new Board(WIDTH, HEIGHT);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(tPanel, BorderLayout.NORTH);
		frame.add(board, BorderLayout.CENTER);

		frame.pack();

		Timer timer = new Timer(REFRESH_T, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				board.nextTurn();
			}

		});
		
	timer.start();
	}
}
