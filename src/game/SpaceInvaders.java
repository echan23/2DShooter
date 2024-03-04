package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

class SpaceInvaders extends Game{
	static int counter = 0;
	private Turret turret;
	private List<Enemy> enemies;
	
	public SpaceInvaders() {
        super("SpaceInvaders!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
        turret = new Turret(new Point(width / 2, height / 2), 270);
		this.addKeyListener(turret);
        enemies = new ArrayList<>();
        spawnEnemy();
    }
  
	public void spawnEnemy() {
		// Generate random position for enemy spawn
		Random random = new Random();
		double minDistance = 150.0; // Adjust as needed
		double maxDistance = 600.0; // Adjust as needed
		double distance = minDistance + random.nextDouble() * (maxDistance - minDistance);
		double angle = random.nextDouble() * 2 * Math.PI; // Random angle in radians
	
		// Calculate spawn position
		double turretX = turret.position.getX();
		double turretY = turret.position.getY();
		double spawnX = turretX + distance * Math.cos(angle);
		double spawnY = turretY + distance * Math.sin(angle);
	
		// Ensure spawn position is within canvas bounds
		spawnX = Math.max(0, Math.min(spawnX, width));
		spawnY = Math.max(0, Math.min(spawnY, height));
	
		// Create new enemy
		Enemy enemy = new Enemy(turret, new Point(spawnX, spawnY), 0); // You can adjust the initial rotation as needed
		enemy.applyBuff(10.0, 2.0);
		enemies.add(enemy);
	}
	
	@Override
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
		turret.move();
		turret.paint(brush);
		turret.updateBullets(brush);
		for (Enemy enemy : enemies) {
			enemy.move();
            enemy.paint(brush);
        }
    }
  
	public static void main (String[] args) {
   		SpaceInvaders a = new SpaceInvaders();
		a.repaint();
    }
	
}
