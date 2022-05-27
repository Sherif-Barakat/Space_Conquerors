package game.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	BufferedImage image;
	
	BufferedImage loadimage(String path) throws IOException {
		image=ImageIO.read(getClass().getResource(path));
		return image;
	}
}
