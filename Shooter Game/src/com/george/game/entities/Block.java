package com.george.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.george.game.GameObject;
import com.george.game.id.ID;

public class Block extends GameObject{

	public Block(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle (x, y, 32, 32);
	}

}
