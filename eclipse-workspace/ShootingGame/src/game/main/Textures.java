package game.main;

import java.awt.image.BufferedImage;

public class Textures {
	SpriteSheet ss=null;
	BufferedImage player,bullet,blue,red,yellow,green,orange,cyan;
	Textures(Game game){
		 ss=new SpriteSheet(game.getSS());
		 getTextures();
	}
	
	void getTextures() {
		player=ss.grabImage(1, 1, 32, 32);
		bullet=ss.grabImage(2, 1, 32,32);
		blue=ss.grabImage(3, 1, 32, 32);
		red=ss.grabImage(4, 1, 32, 32);
		yellow=ss.grabImage(5, 1, 32, 32);
		green=ss.grabImage(6, 1, 32, 32);
		orange=ss.grabImage(7, 1, 32, 32);
		cyan=ss.grabImage(8, 1, 32, 32);
	}
}