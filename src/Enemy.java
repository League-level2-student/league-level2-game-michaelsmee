import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{

	public Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		this.speed=1;
	}
	
	void update() {
		x-=1;
		super.update();
	}
	
	void draw (Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
	}

}
