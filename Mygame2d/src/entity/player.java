package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepanel;
import main.controller;

public class player extends entity {
	Gamepanel gp;
	controller KeyH;
	
	
	public player(Gamepanel gp, controller KeyH) {
		this.gp = gp;
		this.KeyH = KeyH;
		setDefaultValues();
		getPlayerImage();		
		
	}
	public void setDefaultValues() {
		x=100;
		y=100;
		speed=4;
		direction ="right";
		
	}
	public void getPlayerImage() {
		try {
			System.out.println("Image loading started");
			up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
			up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
			down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
			down2 = ImageIO.read(new File("res/player/boy_down_2.png"));;
			left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
			left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
			right1 = ImageIO.read(new File("res/player/boy_right_1 (1).png"));
			right2 = ImageIO.read(new File("res/player/boy_right_2.png.png"));
			System.out.println("Image loading ended");
		}
		catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void update() {
		if(KeyH.upPressed ==true || KeyH.downPressed == true || KeyH.rightPressed == true || KeyH.leftPressed ==true) {
			if(KeyH.upPressed == true) {
				direction = "up";
				y -= speed;
			}
			else if(KeyH.downPressed == true) {
				direction = "down";
				y += speed;
			}
			else if(KeyH.leftPressed ==true) {
				direction="left";
				x -= speed;
			}
			else if(KeyH.rightPressed == true) {
				direction ="right";
				x += speed;
			}
			spriteCounter++;
			if(spriteCounter>10) {
				if(spriteNum ==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		
	}
	
		
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum ==1) {
			image = up1;
			}
			if(spriteNum ==2) {
			image =up2;
			}
			break;
		case "down":
			if(spriteNum ==1) {
			image = down1;
			}
			if(spriteNum ==2) {
			image = down2;
			}
			break;
			
		case "left":
			if(spriteNum ==1) {
			image = left1;
			}
			if(spriteNum ==2) {
			image = left2;
			}
			break;
			
		case "right":
			if(spriteNum ==1) {
			image = right1;
			}
			if(spriteNum ==2) {
			image = right2;
			}
			break;
			
		}
		g2.drawImage(image, x, y,gp.tilesize,gp.tilesize,null);
		
		
		
		
}
	}
