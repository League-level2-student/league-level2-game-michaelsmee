/*
 * There will be a set of 2 trees, one above
 *  and one below at random height, allowing a gap
 * of around 300-400ish space in between them
 * you will be a bark speeder that can go up or down
 * the trees will gradually come forward to you at the same time
 * one the trees reach a x value of 0, they de-spawn
 * after 50 pair of trees, a grey rectangle (may be changed later)
 * go into the grey rectangle to end the game
 * 
 * Classes:
 * Main:
 * 
 * ObjectManager:
 * 		creates/initializes the variables used in the constructors
 * Trees:
 * 		makes a random number
 * 		use this number for a tree length
 *		make it just a green rectangle
 *		rectangle moves forward, has 2 hit boxes(above and below)
 * Speeder:
 * 		will make the bark speeder
 * 		have its movement and how it can fire
 * 		has 3 health
 * 		main character
 * Gamepanel:
 * 		will contain all of the movements for the speeder
 * 		also has the background image of trees

 * Gameobject:
 * Projectile:
 * 		controls both your and the enimies projectiles
 * 		will do a check for the space bar, then fires
 * Enemy:
 * 		would have a human who will shoot a bullet on a timer
 * 		has 1 health, moves forward at a constant rate
 */
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpeederGame{
	JFrame frame; 
	GamePanel panel;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500; 

		public static void main(String[] args) {
			SpeederGame speederGame = new SpeederGame();
			speederGame.setup();
		}

		SpeederGame() {
			frame = new JFrame();
			frame.setVisible(true);
			panel = new GamePanel();
			frame.add(panel);
			
		}

		void setup() {
			frame.setSize(WIDTH, HEIGHT);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	}

