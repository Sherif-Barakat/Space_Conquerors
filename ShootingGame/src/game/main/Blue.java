package game.main;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Blue implements Runnable{
	Random R=new Random();
	double x;
	double y;
	double velX;
	double velY;
	boolean plusOrminusX=R.nextBoolean();
	boolean plusOrminusY=plusOrminusX;
	int ss=R.nextInt(2)+1;
	int weight=1;
	Textures tex;
	boolean respawnFlag=false;
	int numberOfhits=0;
	int score=0;
	int multiplier=1;
	Controller C;
	Bullet Temp;
	
		
	Blue(double x,double y,Textures tex,Controller C){
		this.x=x;
		this.y=y;
		this.tex=tex;
		this.C=C;
		
	}
	
	Rectangle getBound(int width,int height) {
		return new Rectangle((int)x,(int)y,32,32);
	}
		
	void tick() {
		
		for(int i=0;i<C.b.size();i++) {
			Temp=C.b.get(i);
			if(this.getBound((int)x,(int) y).intersects(Temp.getBound((int)Temp.x, (int)Temp.y))) {
				if(Game.currentBonus.equals("Blue")) {
					respawnFlag=true;
					numberOfhits++;
					Game.BonusColor=false;
					C.removeBullet(Temp);
					multiplier=2;
					score+=weight*multiplier;
				}else if(Game.currentEQN.equals("Blue")) {
					respawnFlag=true;
					numberOfhits++;
					Game.BonusEQN=false;
					C.removeBullet(Temp);
					multiplier=3;
					score+=weight*multiplier;
				}else {
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
			if(ss<=6)
				ss++;
			
			//new (x,y) to respawn
			x=R.nextInt(Game.width*Game.scale);
			y=R.nextInt(Game.height*Game.scale -64);
			respawnFlag=false;
		}
		
		
		ss=7;
		velX=ss;
		velY=ss;
		
		if(plusOrminusX) {
			x+=velX;	
		}
		else {
			x-=velX;			
		}
		if(plusOrminusY) {
			y+=velY;
		}
		else {
			y-=velY;			
		}
		
		
	}
	void render(Graphics g) {
		g.drawImage(tex.blue,(int)x,(int)y,null);	
	}


	
	@Override
	//controls the movement of the ball
	public void run() {
		while(Game.gameThread.isAlive()) {
			if(x>=Game.width*Game.scale) {
				x=0;
				y=Game.height*Game.scale -y;
				//plusOrminusX=false;
				//left
			}
			if(y>=Game.height*Game.scale) {
				y=Game.height*Game.scale;
				x=Game.width*Game.scale-x;
				//plusOrminusY=false;
				//up
			}
				
			/*if(x<=0) {
				x=0;
				
				//plusOrminusX=true;
				//right
			}
			if(y<=0) {
				y=0;
				//plusOrminusY=true;
				//down
			}*/
			
		}
	}
	
	
}


