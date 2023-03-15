package main;

import javax.swing.JFrame;

public class Launcher {
	public static void main(String args[]) {

		System.setProperty("sun.java2d.opengl", "true");

		Game game = new Game();
		JFrame frame = new JFrame("Worm Dodge");
																
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
