import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel  extends JPanel {

	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	
	void updateMenuState() {}
	void updateGameState() {}
	void updateEndState() {}
@Override
public void paintComponent (Graphics g) {
	g.fillRect(10,10,100,100);
}

}