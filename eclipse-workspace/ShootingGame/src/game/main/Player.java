package game.main;

import java.awt.Graphics;

public class Player {
	double x;
	double y;
	
	double velX=0;
	double velY=0;
	
	Textures tex;
	
	Player (double x,double y,Textures tex){
		this.x=x;
		this.y=y;
		this.tex=tex;
		//this is the texture for the spirte sheet di el bet2asem el sowar this are constuctor
	
	}
	
	void tick() {
		x+=velX;
		y+=velY;
		
		
		if(x<=0)
			x=0;
		
		if(x>=(Game.width*2)-32)
			x=640-32;
		
		if(y<=(Game.height*2)-64) 
			y=(Game.height*2)-64;
		
		if(y>=(Game.height*2)-34) 
			y=480-34;

	}
	
	void render(Graphics g) {
		g.drawImage(tex.player,(int)x,(int)y,null);
	}
}	
