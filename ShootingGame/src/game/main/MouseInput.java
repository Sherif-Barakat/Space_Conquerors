package game.main;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import game.main.Game.STATE;

public class MouseInput implements MouseListener {
	static int flag=0;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
		int mx=e.getX(); int my=e.getY();
		
		if(mx>=Game.width*Game.scale -50&& mx<=Game.width*Game.scale -10)
			if(my>=0&&my<=20&&Game.state==STATE.MENU) {
				Game.MainSF.Switch();
			}
				
				
				
		if(mx>=Game.width/2 +100&& mx<=Game.width/2 +270) {
			if(my>=150 && my<=225&&Game.state==STATE.MENU) {
				Game.state=Game.STATE.GAME;
			}
		}
		
	
		
		if(mx>=Game.width/2 +100&& mx<=Game.width/2 +270) {
			if(my>=225 && my<=300&&Game.state==STATE.MENU) {
		        Desktop desktop = Desktop.getDesktop();
		        if(Game.HighScores.exists())
					try {
						desktop.open(Game.HighScores);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}
	
		
		if(mx>=Game.width/2 +100&& mx<=Game.width/2 +270) {
			if(my>=300 && my<=375&&Game.state==STATE.MENU) {
		        Desktop desktop = Desktop.getDesktop();
		        if(Game.instructions.exists())
					try {
						desktop.open(Game.instructions);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}

		
		if(mx>=Game.width/2 +100&& mx<=Game.width/2 +270) {
			if(my>=375 && my<=450&&(Game.state==STATE.MENU||Game.state==STATE.GAMEOVER)) {
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
					LocalDateTime now = LocalDateTime.now();     
					FileWriter fr = new FileWriter(Game.HighScores, true);
					if(Game.score>=120) 
						fr.write(dtf.format(now)+"     "+Integer.toString(Game.score)+"\n");
					fr.close();
					
				}
				catch(Exception e1) {}
				System.exit(1);
			}
			
			
		}
		if(mx>=Game.width/2 +100&& mx<=Game.width/2 +270) {
			if(my>=300 && my<=375&&Game.state==STATE.GAMEOVER) {
				
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(new File("C:\\Users\\zaix6\\OneDrive\\Desktop\\SpaceConqueror.exe"));
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				System.exit(1);
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}