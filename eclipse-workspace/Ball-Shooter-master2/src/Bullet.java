import java.awt.*;

public class Bullet implements Entity{
	
	private int x;
	private int y;
	private int r;
	
	private int width;
	private int height;
	
	private int dx;
	private int dy;
	private int speed;
	
	private Color color1;
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		
		r = 4;
		
		width = height = r * 2;
		
		speed = 15;
		
		dy = -speed;
		
		color1 = Color.BLACK;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public boolean canRemove(){
		if(y < 26+r || y > GamePanel.HEIGHT + r){
			return true;
		}
				
		return false;
	}
	
	public void update() {
		
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g){
		g.setColor(color1);
		g.fillOval(x - r, y - r, width, height);
	}
	
	
	
}
