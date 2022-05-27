package game.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	boolean playing=true;
	void setFile(String fileName) {
		try {
			File file=new File(fileName);
			AudioInputStream input=AudioSystem.getAudioInputStream(file);
			clip=AudioSystem.getClip();
			clip.open(input);
		}
		catch(Exception e) {
			
		}
		
		
	}
	
	void Play() {
		clip.setFramePosition(0);
		clip.start();
		playing=true;
	}
	void Stop() {
		clip.close();
		playing=false;
	}
	
	void Switch() {
		if(playing) 
			Stop();
		else
			Play();
	}
}