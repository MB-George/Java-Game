package com.george.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.george.game.entities.Block;
import com.george.game.entities.Crate;
import com.george.game.entities.Enemy;
import com.george.game.entities.Player;
import com.george.game.gfx.BufferedImageLoader;
import com.george.game.gfx.Camera;
import com.george.game.id.ID;
import com.george.game.input.KeyInput;
import com.george.game.input.MouseInput;
import com.george.game.window.Window;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean isRunning = false;
	private Handler handler;
	private Camera camera;
	
	private BufferedImage level  = null;

	public int ammo = 0;
	
	public Game() {
		new Window("Game", 563, 1000, this);
		start();
		
		handler = new Handler();
		camera = new Camera(0, 0);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, this));
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/textures/Arena.png");
		
		loadLevel(level);
		
	}

	private synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		isRunning = false;
		try {
		thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//GameLoop
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;
			}
		}
		
		stop();
	}
	
	public void tick() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				camera.tick(handler.object.get(i));
			}
		}
		
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		//Draw Under!!!
		
		g2d.translate(-camera.getX(), -camera.getY());
		
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1000, 563);
		
		
		
		
		
		//End Drawing!!
		handler.render(g);
		g2d.translate(-camera.getX(), - camera.getY());

		
		g.dispose();
		bs.show();
		
		
	
	}
	
	//load level
	
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255)
					handler.addObject(new Block(xx*32, yy*32, ID.Block));
				
				if(blue == 255 && green == 0)
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler, this));
				
				if(green == 255 && blue == 0)
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemey, handler));
				
				if(blue == 255 && green == 255) {
					handler.addObject(new Crate(xx*32, yy*32, ID.Crate));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
