import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	Speeder speeder = new Speeder(250,250,50,50);

	GamePanel() {
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		titleFont =  new Font("Arial", Font.PLAIN, 30);
		gameFont =  new Font("Arial", Font.PLAIN, 30);
		gameInstructions =  new Font("Arial", Font.PLAIN, 30);
	}


	

	void updateMenuState() {
	}

	void updateGameState() {
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
	}

	void drawGameState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
		speeder.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, SpeederGame.WIDTH, SpeederGame.HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("Game Over", 350, 250);
		g.drawString("You kill _ many enimies", 350, 300);
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
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
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
			if(speeder.x>800) {
				speeder.x=0;
			}
			if(speeder.x<0) {
				speeder.x=800; 
			}
			if(speeder.y>500) {
				speeder.y=0;
			}
			if(speeder.y<0) {
				speeder.y=500;
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

}