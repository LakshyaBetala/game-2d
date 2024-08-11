package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;

import entity.player;
import tile.TileManager;

public class Gamepanel extends JPanel implements Runnable{
	
	final int originaltilesize = 16;
	final int scale =3;
	
	public final int tilesize = originaltilesize * scale;
	public final int maxscreencol = 20;
	public final int maxscreenrow = 16;
	
	public final int screenwidth = tilesize * maxscreencol; //960
	public final int screenheight = tilesize* maxscreenrow; //768
	
	int Fps=60;
	
	TileManager tileM = new TileManager(this);
	controller KeyH = new controller();
	Thread gamethread;
	player Player = new player(this,KeyH);

	//default position
	
	int playerX =100;
	int playerY =100;
	int playerspeed = 4;
	
	
	public Gamepanel() {
		
		this.setPreferredSize(new Dimension(screenwidth ,screenheight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
		
	}
	
	public void startgamethread() {
		gamethread = new Thread(this);
		gamethread.start();
	}
	



	@Override
	public void run() {
		
		double drawInterval= 1000000000/Fps; //0.0166
		double nextDrawTime =System.nanoTime() + drawInterval;
		
		while(gamethread != null) {
			
			
			update();
			
			repaint();
			
			try {
			
			double remainingtime = nextDrawTime - System.nanoTime();
			remainingtime = remainingtime/1000000;
			
			if(remainingtime<0) {
				remainingtime =0;
			}
			Thread.sleep((long) remainingtime);
			
			nextDrawTime += drawInterval;
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	}
	public void update() {   //controller
		
		Player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		Player.draw(g2);
		
		
		g2.dispose();
	}
}
