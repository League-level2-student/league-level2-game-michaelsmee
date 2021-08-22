import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	int barrierX=800;
	Speeder speeder = new Speeder(250, 250, 50, 50);
	Enemy enemy = new Enemy(barrierX,250,15,20);
	Random rand = new Random();
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	int score = 0;

	ObjectManager(Speeder speeder) {
		this.speeder = speeder;
	}
	int getScore() {
		return score;
	}
	
	void purgeObjects() {
		for (int i = 0; i < projectiles.size(); i++) {
			if (projectiles.get(i).active == false) {
				projectiles.remove(projectiles.get(i));
				//System.out.println("projectile removed");
			}
		}
		if(enemy.active == false) {
				enemy = new Enemy(barrierX,2500,15,20);
				System.out.println("new enemy created");
			}

	}


	 void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	void update() {
		
		speeder.update();
		
		
		if (speeder.active == true) {


			for (int i = 0; i < projectiles.size(); i++) {
				projectiles.get(i).update();
				
				if (projectiles.get(i).x > SpeederGame.WIDTH) {
					projectiles.get(i).active = false;
					
				}
				else if (enemy.active==false) {
					projectiles.get(i).active = false;
				}
			}
			checkCollision();
			purgeObjects();
		}
	}

	void draw(Graphics g) {
		speeder.draw(g);
		
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		
		}
	}

	void checkCollision() {
			

	
			for(int i=0; i < projectiles.size(); i++) {
		
				if (enemy.y >= projectiles.get(i).y && enemy.y <= (projectiles.get(i).y-projectiles.get(i).height) && enemy.x >= projectiles.get(i).x && enemy.x <= (projectiles.get(i).x+projectiles.get(i).width)) {
					System.out.println(" if dead");
				
					enemy.active=false;
					
				}
			}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
			}
}
