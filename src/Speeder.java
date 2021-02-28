import java.awt.Color;
import java.awt.Graphics;

public class Speeder extends GameObject {

	Speeder(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
	
	void up() {
		y-=speed;
		}
	void down(){
		y+=speed;
		}
	void left(){
		x-=speed;
		}
	void right(){
		x+=speed;
		}

}
