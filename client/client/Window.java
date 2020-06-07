package client;

import javax.swing.*;
import java.awt.BorderLayout;


public class Window extends JFrame {
	private static final long serialVersionUID = -3834353369457929081L;
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 440;
	public static final int PANEL_HEIGHT = 60;
	public static final int REFRESH_T = 8;
	private Board board;
	private TopPanel topPanel;
	
	
	public Window(Keyboard keyB)
	{
		addKeyListener(keyB);
		setFocusable(true);
	}
	
	public void setBoard(Board _board)
	{
		board = _board;
	}
	
	public void setTopPanel(TopPanel _panel)
	{
		topPanel = _panel;
	}
	
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setVisible(true);


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(board, BorderLayout.CENTER);
		frame.add(topPanel, BorderLayout.NORTH);

		frame.pack();
	}
}
