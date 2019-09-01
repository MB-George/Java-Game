package com.george.game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.george.game.Game;
import com.george.game.GameObject;
import com.george.game.Handler;
import com.george.game.id.ID;

public class Player extends GameObject{

	Handler handler;
	Game game;
	
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		collision();
		
		//movement
		if(handler.isUp() ) velY = -8;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown() ) velY = 8;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isRight() ) velX = 8;
		else if(!handler.isLeft()) velX = 0;
		
		if(handler.isLeft() ) velX = -8;
		else if(!handler.isRight()) velX = 0;
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
		
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Block) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
				
			}
			
			if(tempObject.getId() == ID.Crate) {
				
				if(getBounds().intersects(tempObject.getBounds())) {
					game.ammo += 25;
					handler.removeObject(tempObject);
				}
				
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
