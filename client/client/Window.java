package client;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
	public static final int boardXSize = 1000;
	public static final int boardYSize = 440;
	public static final int topPanelYSize = 60; //xsize == boardXsize
	public static final int repaintTime = 8;
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setVisible(true);

		TopPanel tPanel = new TopPanel(topPanelYSize, boardXSize);
		Board board = new Board(boardYSize, boardXSize);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(tPanel, BorderLayout.NORTH);
		frame.add(board, BorderLayout.CENTER);

		frame.pack();

		
		Timer timer = new Timer(repaintTime, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				board.nextTurn();
			}

		});
		
	timer.start();
	}


	
}
