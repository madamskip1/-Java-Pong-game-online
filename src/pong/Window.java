package pong;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setVisible(true);

		TopPanel tPanel = new TopPanel(60, 1000);
		Board board = new Board(440, 1000);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(tPanel, BorderLayout.NORTH);
		frame.add(board, BorderLayout.CENTER);

		frame.pack();

		
		Timer timer = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				board.nextTurn();
			}

		});
		
	timer.start();
	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
