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
import java.util.Iterator;
import java.util.ArrayList;

class SpaceInvaders extends Game{
	static int counter = 0;
	private Turret turret;
	private List<Enemy> enemies;
	private List<PowerUp> powerups;
	public int score;
	private int waveNumber = 1;
	private int enemiesPerWave = 1;
	public static boolean gameOver;
	
	public SpaceInvaders() {
        super("SpaceInvaders!", 800, 600);
		this.setFocusable(true);
		this.requestFocus();
        turret = new Turret(new Point(width / 2, height / 2), 270);
		this.addKeyListener(turret);
        enemies = new ArrayList<>();
		powerups = new ArrayList<>();
		gameOver = false;
        spawnEnemy();
		spawnPowerUp();
    }
  
	public void spawnEnemy() {
		// Generate random position for enemy spawn
		Random random = new Random();
		double minDistance = 250.0; // Adjust as needed
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

	//Spawns powerup item
	public void spawnPowerUp(){
		//Generates random position for powerup spawn
		Random random = new Random();
		
		// Calculate spawn position within canvas bounds
		double canvasWidth = 750; // Width of the canvas
		double canvasHeight = 550; // Height of the canvas
		double spawnX = random.nextDouble() * canvasWidth;
		double spawnY = random.nextDouble() * canvasHeight;

		PowerUp powerup = new PowerUp(turret, new Point(spawnX, spawnY), 0);
		powerups.add(powerup);  
	}

	/// Method to spawn enemies for the current wave
    public void spawnEnemiesForCurrentWave() {
        for (int i = 0; i < enemiesPerWave; i++) {
            spawnEnemy();
        }
    }

    // Method to advance to the next wave
    public void nextWave() {
		if(enemies.size() == 0){
			waveNumber++;
			enemiesPerWave ++; // Increase the number of enemies per wave
			spawnEnemiesForCurrentWave();	
		}
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
		brush.drawString("Score: " + score, 10, 40); //Writes the score
		brush.drawString("Wave: " + waveNumber, 10, 70); 
		turret.move();
		turret.paint(brush);
		turret.updateBullets(brush);

		//Displays GameOver sign if game is over
		if (gameOver) {
			// Set font for the "Game Over" message
			brush.setColor(Color.red);
			brush.setFont(new Font("Arial", Font.BOLD, 72));
			FontMetrics fm = brush.getFontMetrics();
			int textWidth = fm.stringWidth("Game Over Wave " + waveNumber);
			int textHeight = fm.getHeight();
			int x = (width - textWidth) / 2;
			int y = (height - textHeight) / 2;
		
			// Draw the "Game Over" message
			brush.drawString("Game Over Wave " + waveNumber, x, y);
		}

		// Iterate over enemies and remove collided ones
		Iterator<Enemy> enemyIterator = enemies.iterator();
		while (enemyIterator.hasNext()) {
			Enemy enemy = enemyIterator.next();
			enemy.move();
			enemy.paint(brush);
			if (enemy.collides(turret)) {
				enemyIterator.remove();
				gameOver = true; //The game ends if an enemy can touch the turret
			}
		}
	
		// Draw power-ups
		for (PowerUp powerup : powerups) {
			powerup.paint(brush);
		}
	
		// Check for collisions between bullets and objects
		Iterator<Turret.Bullet> bulletIterator = turret.getBullets().iterator();
		while (bulletIterator.hasNext()) {
			Turret.Bullet bullet = bulletIterator.next();
			// Check collision with enemies
			for (Enemy enemy : enemies) {
				if (enemy.collides(bullet)) {
					enemies.remove(enemy);
					bulletIterator.remove(); // Remove bullet
					scoreboard.increaseScore();
					break;
				}
			}
			// Check collision with power-ups
			for (PowerUp powerup : powerups) {
				if (powerup.collides(bullet)) {
					bulletIterator.remove(); // Remove bullet
					powerup.applyPowerUp(); // Apply power-up effect
					powerups.remove(powerup); // Remove power-up
					break;
				}
			}
		}
		if(!gameOver){
			nextWave();
		}
	}

	//Lambda expression that increments score
	ScoreBoard scoreboard = () -> {
		score++;
	};
  
	public static void main (String[] args) {
   		SpaceInvaders a = new SpaceInvaders();
		a.repaint();
    }
	
}
