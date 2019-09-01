package com.george.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.george.game.GameObject;
import com.george.game.id.ID;

public class Crate extends GameObject{

	public Crate(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 32, 32);
		g.setColor(Color.red);
		g.drawRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
