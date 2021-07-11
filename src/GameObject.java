import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameObject implements ActionListener {
	 int x;
	 int y;
	 int width;
	 int height;
	 int speed=10;
	 boolean active=true;
	 
	
public GameObject(int x, int y, int width, int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	//collisionBox = new Rectangle(x,y,width,height);
	}




void update() {
	
}


@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}