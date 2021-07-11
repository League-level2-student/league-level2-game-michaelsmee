import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	
	int currentState = MENU;
	Font titleFont;
	Font gameFont;
	Font gameInstructions;
	Timer frameDraw;
	int barrierX = 800;
	int barrierY = 0;
	Speeder speeder = new Speeder(250, 250, 50, 50);
	Enemy enemy = new Enemy(barrierX,250,15,20);
	int upperHeight = 100;
	int Gap = 200;
	int lowerY = upperHeight + Gap;
	int score;
	
	

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 30);
		gameFont = new Font("Arial", Font.PLAIN, 30);
		gameInstructions = new Font("Arial", Font.PLAIN, 30);
		if (needImage) {
		    loadImage ("background4.jpg");
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		intersectss();
		enemyIntersect();
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("Welcome", 350, 200);
		g.drawString("Press Enter to start", 350, 300);
		g.drawString("Press space for info", 350, 400);
		barrierX = 800;
		enemy.x = 800;
		score = 0;
	}

	void drawGameState(Graphics g) {
		if(gotImage) {
			g.drawImage(image, WIDTH, HEIGHT, null);
			//System.out.println("gotImage");
		}
		else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
			//System.out.println("Not got image");
		}
		
	
			speeder.draw(g);
			g.setColor(Color.YELLOW); 
			g.drawString("Wave "+ score, 30, 30);
			barrier(g);
			teleportBarrier();
		
			
			enemy.draw(g);
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


	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("Game Over", 350, 250);
		g.drawString("Waves survived: "+score, 350, 300);
		g.drawString("Press ENTER to restart", 350, 400);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
			

		} else if (currentState == GAME) {
			drawGameState(g);
		
		} else if (currentState == END) {
			drawEndState(g);
			
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		// System.out.println("Action");
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(currentState);
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				speeder = new Speeder(250, 750, 50, 50);
				currentState = MENU;
			} else {
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				speeder.up();
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				speeder.down();
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				speeder.left();
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				speeder.right();
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				System.out.println("space");
				speeder.addProjectile(new Projectile(speeder.x, speeder.y, speeder.height,speeder.width));
			}
			if (speeder.x > 800) {
				speeder.x = 800;
			}
			if (speeder.x < 0) {
				speeder.x = 0;
			}
			if (speeder.y > 450) {
				speeder.y = 450;
			}
			if (speeder.y < 0) {
				speeder.y = 0;
			}
		}
		/*if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState == GAME) {
			objectManager.addProjectile(speeder.getProjectile());	
		}*/
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	void barrier(Graphics g) {
		
		g.setColor(Color.GREEN);
		g.fillRect(barrierX, lowerY, 20, 500-lowerY);
		g.fillRect(barrierX, 0, 20, upperHeight);
		barrierX--;
		enemy.update();
	}

	void teleportBarrier() {
		Random rand = new Random();
		if (barrierX == 0) {
			barrierX = SpeederGame.WIDTH;
			upperHeight = rand.nextInt(280);
			lowerY = upperHeight + Gap;
			score += 1;
			enemy.x=SpeederGame.WIDTH;
			Random ran = new Random();
			enemy.y=ran.nextInt(150)+upperHeight;
		}

	}

	void intersectss() {
		if (speeder.y < upperHeight && speeder.x > barrierX && speeder.x < (barrierX + 20)) {
			//System.out.println(" if dead");
			currentState = END;
			
			
		} else if (speeder.y+50 > lowerY && speeder.x > barrierX && speeder.x < (barrierX + 20)) {
			//System.out.println(" else if dead");
			currentState = END;
			
			
		}	
		
		
		}
	void enemyIntersect() {
		
		if (enemy.y >= speeder.y && enemy.y <= (speeder.y+speeder.height) && enemy.x >= speeder.x && enemy.x <= (speeder.x+speeder.width)) {
			System.out.println(" if dead");
		
			currentState = END;
			
		}
		
	}
	

}