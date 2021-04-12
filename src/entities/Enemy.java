package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.World;

public class Enemy extends Entity{
	
	public boolean right = true, left = false;
	public int life = 2;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void destroySelf() {
		if(life <= 0) {
			Game.entities.remove(this);
		}
	}
	
	public void tick() {
		
		destroySelf();
		
		if(World.isFree((int)x, (int)(y+1))) {
			y += 1;
		}
		
		if(right) {
			if(World.isFree((int)(x+speed), (int)y)) {
				x+=speed;
				if(World.isFree((int)(x+16), (int)y+1)) {
					right = false;
					left = true;
				}
			}else {
				right = false;
				left = true;
			}
		}
		
		if(left){
			if(World.isFree((int)(x-speed), (int)y)) {
				x-=speed;
				if(World.isFree((int)(x-16), (int)y+1)) {
					right = true;
					left = false;
				}
			}else {
				right = true;
				left = false;
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		if(right) {
			sprite = Entity.ENEMY_RIGHT;
		}
		
		if (left){
			sprite = Entity.ENEMY_LEFT;
		}
		
		super.render(g);
		
	}

}
