package game.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	BufferedImage image;
	
	SpriteSheet(BufferedImage image){
		this.image=image;
		
	}
	
	BufferedImage grabImage(int col,int row,int width,int height) {
			BufferedImage img=image.getSubimage( col*32 -32 , row*32 -32, width, height);
			return img;
	}
}
