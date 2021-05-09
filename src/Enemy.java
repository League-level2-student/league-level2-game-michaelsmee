import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;

public class Enemy extends GameObject{

	public Enemy(int x, int y, int width, int height) {
		super(image, x, y, width, height);
		// TODO Auto-generated constructor stub
		this.speed=1;
	}
	
	void update() {
		x-=1;
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}

		}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
}
