package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
	double x;
	double y;
	Textures tex;
	
	Bullet(double x,double y,Textures tex){
		this.x=x;
		this.y=y;
		this.tex=tex;
	}
	void tick() {
		y-=6;
	}
	
	void render(Graphics g) {
		g.drawImage(tex.bullet,(int) x,(int)y,null);
		
	}
	
	Rectangle getBound(int width,int height) {
		return new Rectangle((int)x,(int)y,32,32);
	}
}
