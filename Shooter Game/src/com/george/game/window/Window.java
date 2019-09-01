package com.george.game.window;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.george.game.Game;

public class Window {
	
	public int width, height;
	String title;
	
	public Window(String title, int height, int width, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(game);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
