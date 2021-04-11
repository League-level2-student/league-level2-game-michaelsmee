import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font gameFont;
	Font gameInstructions;
	Timer frameDraw;
	Speeder speeder = new Speeder(250, 250, 50, 50);
	int upperPipeHeight = 100;
	int pipeGap = 200;
	int lowerY = upperPipeHeight + pipeGap;
	int score;
	int barrierX = 800;
	int barrierY = 0;

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 30);
		gameFont = new Font("Arial", Font.PLAIN, 30);
		gameInstructions = new Font("Arial", Font.PLAIN, 30);
	}

	void updateMenuState() {
	}

	void updateGameState() {
		intersectsPipes();
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
		barrierX = 750;
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
		speeder.draw(g);
		g.setColor(Color.YELLOW);
		g.drawString("Wave "+ score, 30, 30);
		barrier(g);
		teleportBarrier();
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
		System.out.println(currentState);
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
		
		g.setColor(Color.YELLOW);
		g.fillRect(barrierX, upperPipeHeight+pipeGap, 20, lowerY);
		g.fillRect(barrierX, 0, 20, upperPipeHeight);
		barrierX--;
	}

	void teleportBarrier() {
		Random rand = new Random();
		if (barrierX == 0) {
			barrierX = SpeederGame.WIDTH;
			upperPipeHeight = rand.nextInt(200) + 100;
			lowerY = barrierX-upperPipeHeight- pipeGap;
			score += 1;
		}

	}

	void intersectsPipes() {
		if (speeder.y < upperPipeHeight && speeder.x > barrierX && speeder.x < (barrierX + 20)) {
			//System.out.println(" if dead");
			currentState = END;
			
			
		} else if (speeder.y+50 > lowerY && speeder.x > barrierX && speeder.x < (barrierX + 20)) {
			//System.out.println(" else if dead");
			currentState = END;
			
			
		} 
			
		}
	

}