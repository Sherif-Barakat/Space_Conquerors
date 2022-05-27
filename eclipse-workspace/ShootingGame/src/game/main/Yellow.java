package game.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Yellow implements Runnable{
	
	Random R=new Random();
	double x;
	double y;
	double velX;
	double velY;
	boolean plusOrminusX=R.nextBoolean();
	boolean plusOrminusY=plusOrminusX;
	int ss=R.nextInt(6)+1;
	int weight=5;
	Textures tex;
	boolean respawnFlag=false;
	int numberOfhits=0;
	int score=0;
	Controller C;
	Bullet Temp;
	int multiplier=1;
	Yellow(double x,double y,Textures tex,Controller C){
		this.x=x;
		this.y=y;
		this.tex=tex;
		this.C=C;
		
	}
		
void tick() {
		
		for(int i=0;i<C.b.size();i++) {
			Temp=C.b.get(i);
			if(this.getBound((int)x,(int) y).intersects(Temp.getBound((int)Temp.x, (int)Temp.y))) {
				if(Game.currentBonus.equals("Yellow")) {
					respawnFlag=true;
					numberOfhits+=1;
					Game.BonusColor=false;
					C.removeBullet(Temp);
					multiplier=2;
					score+=weight*multiplier;
				}else if(Game.currentEQN.equals("Yellow")) {
					respawnFlag=true;
					numberOfhits+=1;
					Game.BonusEQN=false;
					C.removeBullet(Temp);
					multiplier=3;
					score+=weight*multiplier;
				}
					else {
						respawnFlag=true;
						numberOfhits+=1;
						C.removeBullet(Temp);
						multiplier=1;
						score+=weight*multiplier;
				}
				break;
			}
		}
		if(respawnFlag) {
			if(ss<=14)
				ss++;
			x=R.nextInt(Game.width*Game.scale);
			y=R.nextInt(Game.height*Game.scale -64);
			respawnFlag=false;
		}
		

		velX=ss;
		velY=ss;
		if(plusOrminusX) {
			x+=velX;
			//y+=velY;
		}
		else {
			x-=velX;
			//y-=velY;
		}
		if(plusOrminusY) {
			y+=velY;
			//y+=velY;
		}
		else {
			y-=velY;
			//y-=velY;
		}
	}
	void render(Graphics g) {
		g.drawImage(tex.yellow,(int)x,(int)y,null);	
	}
	Rectangle getBound(int width,int height) {
		return new Rectangle((int)x,(int)y,32,32);
	}
	

	@Override
	public void run() {
		while(Game.gameThread.isAlive()) {
			if(x>=Game.width*Game.scale-32) {
				x=Game.width*Game.scale-32;
				plusOrminusX=false;
			}
			if(y>=Game.height*Game.scale-74) {
				y=Game.height*Game.scale-74;
				plusOrminusY=false;
			}
				
			if(x<=0) {
				x=0;
				plusOrminusX=true;
			}
			if(y<=0) {
				y=0;
				plusOrminusY=true;
			}
		}
	}
}