import java.awt.*;
public class GoldenBall extends Enemy implements Entity {
    

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
	
	int yellow = (int) (Math.random() * 256);
	
	public GoldenBall() {
		x = (int)((Math.random() * (GamePanel.WIDTH - 50) - 200) + 200);
		y = (int)((Math.random() * (GamePanel.HEIGHT - 50) - 200) + 200);
		
		r = 5;
		
		width = 2 * r;
		height = 2 * r;
		speed = 10;
		
		
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
			x=GamePanel.WIDTH;
			dx=dx*-1;
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
		g.setColor(new Color(0,0,0));
		g.fillOval(x - r, y - r, width, height);
		
		g.setStroke(new BasicStroke(3));
		g.setColor(new Color(255,255,0));
		g.drawOval(x - r, y - r, width, height);
		
		g.setStroke(new BasicStroke(1));
	}
	
}

