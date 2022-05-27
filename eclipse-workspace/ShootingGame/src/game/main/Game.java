package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	static final int width=320;
	static final int height=240;
	static final int scale=2;
	static final String title="Space Conquerors";
	static Runnable RT1=new Game();
	static Thread gameThread=new Thread(RT1);
	BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	BufferedImage sprite=null;
	BufferedImage background=null;
	
	Player p;
	Controller C;
	static Textures tex;
	
	boolean isShooting=false;
	static Blue blue;
	static Red red;
	static Green green;
	static Yellow yellow;
	static Cyan cyan;
	static Orange orange;
	static int score;
	
	Menu menu;
	int ammo=30;
	
	static boolean BonusColor=false;
	static boolean BonusEQN=false;
	static String currentBonus="-";
	static String currentEQN="--";
	static Color tempColor=Color.white;
	static int a=0,b=1;
	
	static String path="C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\HighScores.txt";
	static File HighScores=new File(path);
	static String path2="C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\Instructions.txt";
	static File instructions=new File(path2);
	
	static Sound s=new Sound();
	static Sound MainSF=new Sound();
	
	public void init() {
		
		requestFocus();
		BufferedImageLoader loader=new BufferedImageLoader();
		try {
			sprite=loader.loadimage("/sprite_sheet1.png");
			background=loader.loadimage("/pixels-1622239430763-4384.jpg");
		}
		catch(Exception e) {
			System.out.println("Error");
		}
				
		addKeyListener(new Keyboard(this));
		this.addMouseListener(new MouseInput());
		
		tex=new Textures(this);
		p=new Player(200,200,tex);
		C=new Controller(this);
		menu=new Menu();
		
		Random sc=new Random();
		
		double r1=sc.nextInt(Game.width*Game.scale);
		double r2=sc.nextInt(Game.height*Game.scale -64);
		blue=new Blue(r1,r2,tex,C);

		
		r1=sc.nextInt(Game.width*Game.scale);
		r2=sc.nextInt(Game.height*Game.scale -64);
		red=new Red(r1,r2,tex,C);
		
		
		
		r1=sc.nextInt(Game.width*Game.scale);
		r2=sc.nextInt(Game.height*Game.scale -64);
		orange=new Orange(r1,r2,tex,C);
		
		r1=sc.nextInt(Game.width*Game.scale);
		r2=sc.nextInt(Game.height*Game.scale -64);
		yellow=new Yellow(r1,r2,tex,C);
		
		r1=sc.nextInt(Game.width*Game.scale);
		r2=sc.nextInt(Game.height*Game.scale -64);
		cyan=new Cyan(r1,r2,tex,C);
		
		r1=sc.nextInt(Game.width*Game.scale);
		r2=sc.nextInt(Game.height*Game.scale -64);
		green=new Green(r1,r2,tex,C);
		
		ExecutorService executor = Executors.newCachedThreadPool();//could be new fixed thread pool, doesn't make a difference
		executor.execute(blue);
		executor.execute(red);
		executor.execute(orange);
		executor.execute(cyan);
		executor.execute(green);
		executor.execute(yellow);
		
		/*Thread BlueB=new Thread(blue);
		Thread RedB=new Thread(red);
		Thread OrangeB=new Thread(orange);
		Thread CyanB=new Thread(cyan);
		Thread GreenB=new Thread(green);
		Thread YellowB=new Thread(yellow);
		
		BlueB.start();
		RedB.start();
		OrangeB.start();
		CyanB.start();
		GreenB.start();
		YellowB.start();
		
		*/
	}
	
	
	
	@Override
	public void run() {
		
		create();
		init();
		long then= System.nanoTime();
		final double Tick_num=60.0;
		double ns=1000000000/Tick_num;
		double diff=0;
		int ticks=0,frames=0;
		long timer=System.currentTimeMillis();
		
		
		while(Thread.currentThread().isAlive()) {
			long now=System.nanoTime();
			diff+=(now-then)/ns;
			then=now;
			if(diff>=1) {
				tick();
				ticks++;
				diff--;
			}
			
			render();
			frames++;
			if(System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				System.out.println("Ticks: "+ticks+"  FPS: "+ frames);
				ticks=0;
				frames=0;
			}
			
		}
		
	}
	
	static enum STATE{
		MENU,
		GAME,
		GAMEOVER
	};
	
	static STATE state= STATE.MENU;
	
	void tick() {
		if(state==STATE.GAME) {
			p.tick();
			C.tick();
		}
		blue.tick();
		red.tick();
		green.tick();
		orange.tick();
		cyan.tick();
		yellow.tick();
		score=blue.score+red.score+green.score+orange.score+cyan.score+yellow.score;
	}
	
	
	void render() {
		
		BufferStrategy bs=this.getBufferStrategy();
		if (bs==null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background,0,0,null);
		if(state==STATE.GAME) {
			p.render(g);
			C.render(g);
			blue.render(g);
			red.render(g);
			green.render(g);
			orange.render(g);
			cyan.render(g);
			yellow.render(g);
		
		
			Font fntScore=new Font("arial",Font.BOLD,20);
			g.setFont(fntScore);
			g.setColor(Color.white);
			g.drawString("Score: "+score, 5, 20);
			if(ammo>0) {
				g.drawString("Ammo: "+ammo, 5, 40);
				Random sc=new Random();
				int bonus=sc.nextInt(6)+1;
			
				if(!BonusColor) {
					
					switch(bonus) {
					case 1:
						currentBonus="Blue";
						tempColor=Color.blue;
						break;
					case 2:
						currentBonus="Red";
						tempColor=Color.red;
						break;
					case 3:
						currentBonus="Green";
						tempColor=Color.green;
						break;
					case 4:
						currentBonus="Orange";
						tempColor=Color.orange;
						break;
					case 5:
						currentBonus="Yellow";
						tempColor=Color.yellow;
						break;
					case 6:
						currentBonus="Cyan";
						tempColor=Color.cyan;
						break;
					default:
						break;
					}
					BonusColor=true;
				}
				
				Color eqn=Color.white;
				
				if(!BonusEQN) {
					a=sc.nextInt(3)+1;
					b=sc.nextInt(4);
					switch(a+b) {
					case 1:
						currentEQN="Blue";
						break;
					case 2:
						currentEQN="Red";
						break;
					case 3:
						currentEQN="Green";
						break;
					case 4:
						currentEQN="Orange";
						break;
					case 5:
						currentEQN="Yellow";
						break;
					case 6:
						currentEQN="Cyan";
						break;
					default:
						break;
					}
					BonusEQN=true;
				}
				
			
				g.setColor(tempColor);
				g.drawString("Bonus color: "+currentBonus, (width*scale)-200, 20);
				g.setColor(eqn);
				g.drawString("Equation: "+a+"+"+b,(width*scale)-135, 460);
			
				}
				else {
					state=STATE.GAMEOVER;
					MainSF.Stop();
					if(score>=150) {
						s.setFile("C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\Victory_SF.wav");
						s.Play();
					}
					else {
						s.setFile("C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\Lose_SF.wav");
						s.Play();
					}
				}
		
		
		}
		else if(state==STATE.MENU) {
			menu.render(g);
		}
		if(state==STATE.GAMEOVER) {
			
			Font fntfinal=new Font("arial",Font.BOLD,50);
			g.setFont(fntfinal);
			g.setColor(Color.white);
			if(score>=150) {
				g.drawString("YOU WON!", width-150, height-100);
				g.drawString("FINAL SCORE: "+score, width-200, height-50);
			}
			else {
				g.drawString("GAME OVER!", width-150, height-100);
				g.drawString("FINAL SCORE: "+score, width-200, height-50);
			}
			Rectangle quit=new Rectangle(Game.width/2 +100,375,170,50);
			Graphics2D g2d=(Graphics2D) g;
			g2d.draw(quit);
			g.drawString("QUIT", quit.x+25 ,quit.y+ 40);
			Rectangle retry=new Rectangle(Game.width/2 +100,300,170,50);
			g2d.draw(retry);
			g.drawString("RETRY", retry.x ,retry.y+ 40);
		}
		
		g.dispose();
		bs.show();
	}
	
	
	
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(state==STATE.GAME) {
			
		if(key==KeyEvent.VK_RIGHT) {
			p.velX =5;
		}
		else if(key==KeyEvent.VK_LEFT) {
			p.velX =-5;
		}else if(key==KeyEvent.VK_DOWN) {
			p.velY =5;
		}
		else if(key==KeyEvent.VK_UP) {
			p.velY =-5;
		}
		else if(key==KeyEvent.VK_SPACE && !isShooting) {
			isShooting=true;
			C.addBullet(new Bullet(p.x,p.y,tex));
			s.setFile("C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\Bullet_SF.wav");
			FloatControl gainControl = (FloatControl) s.clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f); 	
			s.Play();
			ammo--;
			
		}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) {
			p.velX=0;
		}else if(key==KeyEvent.VK_LEFT) {
			p.velX=0;
		}else if(key==KeyEvent.VK_DOWN) {
			p.velY=0;
		}else if(key==KeyEvent.VK_UP) {
			p.velY=0;
		}else if(key==KeyEvent.VK_SPACE) {
			 isShooting=false;
		}
	}
	
	
	
	void create() {
		
		this.setPreferredSize(new Dimension(width*scale,height*scale));
		this.setMaximumSize(new Dimension(width*scale,height*scale));
		this.setMinimumSize(new Dimension(width*scale,height*scale));
		JFrame gframe=new JFrame(title);
		gframe.add(this);
		gframe.pack();
		gframe.setResizable(false);
		gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gframe.setVisible(true);
		
	}
	BufferedImage getSS() {
		return sprite;
	}
	
	
	
	public static void main(String arg[]) {	
			
			MainSF.setFile("C:\\Users\\zaix6\\eclipse-workspace\\ShootingGame\\resources\\Menu.wav");
			FloatControl gainControl = (FloatControl) MainSF.clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5.0f); 	
			MainSF.Play();
			gameThread.start();
		
		}
	
}
