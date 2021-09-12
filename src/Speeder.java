import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Speeder extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	public static BufferedImage image1;
	public static boolean needImage1 = true;
	public static boolean gotImage1 = false;	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	Speeder(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		
		if (needImage) {
		    loadImage ("speeder1.png");
		}
	}
	
	void draw(Graphics g){
		if (gotImage) {
			//g.setColor(Color.PINK);
			//g.drawRect(x, y, width, height);
			g.drawImage(image, x, y, width, height, null);
			
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
	
	void update() {
		
	}
	
	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
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
	public Projectile getProjectile() {
		return new Projectile(x+width/2, y+20, 10, 10);
	}
}
