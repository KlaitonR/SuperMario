package graficos;

import java.awt.Color;
import java.awt.Graphics;

import entities.Player;

public class UI {

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect(10,10,200,30);
		g.setColor(Color.green);
		g.fillRect(10, 10, (int)((Player.life/100) * 200), 30);
		g.setColor(Color.white);
		g.drawRect(10, 10, 200, 30);
		
	}
	
}
