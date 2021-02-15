package entities;

import java.awt.Graphics;
//import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.Camera;
import world.World;

public class Player extends Entity{
	
	public boolean right, left;
	private double gravity = 2;
	public int dir = 1;
	
	public boolean isJumping;
	public boolean jump = false;
	public int jumpHeight = 64;
	public int jumpFrames = 0;
	
	public int maxFrames = 10;
	public int frames;
	public int maxSprites;
	public boolean moved;
	
	public BufferedImage [] rigthSprite;
	public BufferedImage [] leftSprite ;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		depth = 2;
		
		rigthSprite = new BufferedImage[4];
		leftSprite = new BufferedImage[4];
		
		for(int i=0; i<4; i++) {
			rigthSprite[i] = Game.spritesheet.getSprite(32+(i*16), 0, 16, 16);
			leftSprite[i] = Game.spritesheet.getSprite(32+(i*16),16, 16, 16);
		}
		
	}
	
	public void tick(){
		
		if(World.isFree((int)x, (int)(y+gravity)) && !isJumping) {
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
		
		if(jump) {
			if(!World.isFree(getX(), getY()+1)) {
				isJumping = true;
			}else {
				jump = false;
			}
		}
		
		if(isJumping) {
			if(World.isFree(getX(), getY() - 2) && getY() > 0) {
				y-=2;
				jumpFrames += 2;
				if(jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			}else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			maxSprites++;
			if(maxSprites == 4) {
				maxSprites = 0;
			}
		}
		
		Camera.x = Camera.clamp((int)x - Game.WIDTH/2, 0, World.WIDTH*16);
		Camera.y = Camera.clamp((int)y - Game.HEIGHT/2, 0, World.HEIGHT*16);
		updateCamera();
	}
	
	public void render(Graphics g) {
		
		if(dir == 1) {
			if(moved) {
				sprite = rigthSprite[maxSprites];
			}else {
				sprite = rigthSprite[0];
			}
		}else if (dir == 2) {
			if(moved) {
				sprite = leftSprite[maxSprites];
			}else {
				sprite = leftSprite[0];
			}
		}
		
		super.render(g);
		
	}

}
