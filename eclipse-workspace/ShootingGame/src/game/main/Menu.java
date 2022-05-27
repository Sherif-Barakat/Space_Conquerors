package game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	Rectangle play=new Rectangle(Game.width/2 +100,150,170,50);
	Rectangle highscores=new Rectangle(Game.width/2 +100,225,170,50);
	Rectangle help=new Rectangle(Game.width/2 +100,300,170,50);
	Rectangle quit=new Rectangle(Game.width/2 +100,375,170,50);
	Rectangle Mute=new Rectangle(Game.width*Game.scale -50,20,40,40);
	
	
	void render(Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		
		Font fntMute=new Font("arial",Font.PLAIN,10);
		g.setFont(fntMute);
		g.setColor(Color.white);
		g.drawString("MUTE", Mute.x, Mute.y);
		
		Font fntTitle=new Font("arial",Font.BOLD,50);
		g.setFont(fntTitle);
		g.setColor(Color.white);
		g.drawString("SPACE CONQUEROR", Game.width/2-90, 80);
		
		Font fntMenu=new Font("arial",Font.BOLD,20);
		g.setFont(fntMenu);
		g.setColor(Color.white);
		g2d.draw(play);
		g.drawString("PLAY", play.x +55,play.y +30);
		g2d.draw(highscores);
		g.drawString("HIGHSCORES", highscores.x +20,highscores.y +30);
		g2d.draw(help);
		g.drawString("HELP", help.x +55,help.y +30);
		g2d.draw(quit);
		g.drawString("QUIT", quit.x +55,quit.y +30);
	}
}
