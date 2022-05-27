package game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter{
	Game game;
	Keyboard(Game g){
		this.game=g;
	}
	
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
