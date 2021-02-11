package entities;

import java.awt.Graphics;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;

import world.World;

public class Player extends Entity{
	
	public boolean right, left;
	private double gravity = 2;
	public int dir = 1;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		depth = 2;
	}
	
	public void tick(){
		
		if(World.isFree((int)x, (int)(y+gravity))) {
			y += gravity;
		}
		
		if(right && World.isFree((int)(x+speed), (int)y)) {
			x+=gravity;
			dir = 1;
		}
		
		if(left && World.isFree((int)(x-speed), (int)y)) {
			x-=speed;
			dir = 2;
		}
		
	}
	
	public void render(Graphics g) {
		
		if(dir == 1) {
			sprite = Entity.PLAYER_RIGHT_SPRITE;
		}else if (dir == 2) {
			sprite = Entity.PLAYER_LEFT_SPRITE;
		}
		
		super.render(g);
		
	}

}
