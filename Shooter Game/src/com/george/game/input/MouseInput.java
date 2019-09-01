package com.george.game.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.george.game.Game;
import com.george.game.GameObject;
import com.george.game.Handler;
import com.george.game.entities.Bullet;
import com.george.game.gfx.Camera;
import com.george.game.id.ID;

public class MouseInput extends MouseAdapter{
	
	private Handler handler;
	private Camera camera;
	private Game game;
	
	public MouseInput(Handler handler, Camera camera, Game game) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = (int) (e.getX() + camera.getX());
		int my = (int) (e.getY() + camera.getY());
	
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player && game.ammo >= 1){
				handler.addObject(new Bullet(tempObject.getX()+16, tempObject.getY()+24, ID.Bullet, handler, mx, my));
				game.ammo--;
			}
		}
	}
	
}
