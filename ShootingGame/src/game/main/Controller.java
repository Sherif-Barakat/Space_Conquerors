package game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	LinkedList<Bullet> b=new LinkedList<Bullet>();
	int count=0;
	Bullet Temp;
	Game game;
	int ammo=30;
	Controller(Game g){
		this.game=g;
		
	}
	
	void tick(){
		for(int i=0;i<b.size();i++) {
			Temp=b.get(i);
			count++;
			if(Temp.y<0) {
				removeBullet(Temp);
				ammo--;
			}
			Temp.tick();
			
		}
		
	}
	
	void render(Graphics g) {
		for(int i=0;i<b.size();i++) {
			Temp=b.get(i);
			Temp.render(g);
		}
	}
	
	void addBullet(Bullet x) {
		b.add(x);
	}
	
	void removeBullet(Bullet x) {
		b.remove(x);
	}
}
