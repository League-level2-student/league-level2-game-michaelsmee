import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel  extends JPanel {

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	
	void updateMenuState() {}
	void updateGameState() {}
	void updateEndState() {}
	
	void drawMenuState(Graphics g) {}
	void drawGameState(Graphics g) {}
	void drawEndState(Graphics g) {}
	
@Override
public void paintComponent (Graphics g) {
	if(currentState == MENU) {
		drawMenuState(g);
		g.setColor(Color.BLUE);
		g.fillRect(0,0,SpeederGame.WIDTH, SpeederGame.HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("Welcome", 150, 200);
		g.drawString("Press Enter to start", 150, 300);
		g.drawString("Press space for info", 150, 400);
	}
	else if(currentState == GAME) {
		drawGameState(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,SpeederGame.WIDTH, SpeederGame.HEIGHT);
	}
	else if(currentState == END) {
		drawEndState(g);
		g.setColor(Color.RED);
		g.fillRect(0,0,SpeederGame.WIDTH, SpeederGame.HEIGHT);
	}
}

}