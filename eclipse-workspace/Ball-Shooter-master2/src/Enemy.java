import java.awt.*;

public class Enemy implements Entity{

	// FIELDS
	private int x;
	private int y;
	static int r;
	
	private int width;
	private int height;
	
	private int dy;
	int dx;
	private int speed;
	
	private boolean dead;
	
	int red = (int) (Math.random() * 256);
	int green = (int) (Math.random() * 256);
	int blue = (int) (Math.random() * 256);
	
	public Enemy() {
		x = (int)((Math.random() * (GamePanel.WIDTH - 50))) ;
		y = (int)((Math.random() * (GamePanel.HEIGHT - 50))) ;
		
		r = (int) (Math.random() * 30 + 20);
		
		width = 2 * r;
		height = 2 * r;
		if (r<=10) 
			speed=5;
			else if (r>10 &&r<=20)
				speed=4;
		    else if (r>20 &&r<=30)
		    	speed=3;
		    	else if (r>30 &&r<=40)
		    		speed=2;
		    		else if (r>40 &&r<=50)
		    			speed=1;
		
		
		dx = speed;
		dy = speed;
		
		dead = false;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public boolean isDead() { return dead; }
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void hit() {
		dead = true;
	}
	
	public void update() {
		if(y>=GamePanel.HEIGHT) {
			y=GamePanel.HEIGHT;
			dy=dy*-1;
		}
		else if(y<=0) {  
			y=0;
			dy=Math.abs(dy);
		}
		if(x>=GamePanel.WIDTH) {
			x=0;
			y=GamePanel.HEIGHT-y;
		}
		else if(x<=0) {
			x=0;
			dx=Math.abs(dx);
		}
		y += dy;
		x+=dx;
	}
	
	public boolean isOutOfScreen() {
		if(y > GamePanel.HEIGHT && x > GamePanel.WIDTH  ) {
			return true;
		}
		
		return false;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(new Color(red, green, blue));
		g.fillOval(x - r, y - r, width, height);
		
		g.setStroke(new BasicStroke(3));
		g.setColor(new Color(red, green, blue));
		g.drawOval(x - r, y - r, width, height);
		
		g.setStroke(new BasicStroke(1));
	}
	
}