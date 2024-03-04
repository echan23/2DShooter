package game;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;

public class Enemy extends Polygon{
	private double stepSize = 1.0;
    private Turret turret;
    private List<Buff> activeBuffs;

    private static Point[] enemyPoints = {
        new Point(0,0),
        new Point(20,0),
        new Point(20,20),
        new Point(0, 20)
    };

    //Turret is passed as a parameter because the enemies need to always move towards the player
    public Enemy(Turret turret, Point inPosition, double inRotation) {
        super(enemyPoints, inPosition, inRotation);
        this.turret = turret;
        activeBuffs = new ArrayList<>();
    }

    public void paint(Graphics brush) {
        int l = this.getPoints().length;
        int[] x = new int[l];
        int[] y = new int[l];
        //Iterate through the array of points retrieving the x and y values for each
        for(int i = 0; i < l; i++) {
            x[i] = (int) this.getPoints()[i].getX();
            y[i] = (int) this.getPoints()[i].getY();
        }
        if(activeBuffs.size() > 0){
            brush.setColor(Color.red);
        }
        brush.fillPolygon(x, y, l);
    }
    
    //Updates position of enemy
    public void move() {
        // Calculate direction towards turret
        double dx = turret.position.getX() - position.getX();
        double dy = turret.position.getY() - position.getY();
        double angle = Math.atan2(dy, dx);
        
        // Calculate movement in x and y directions
        double moveX = stepSize * Math.cos(angle);
        double moveY = stepSize * Math.sin(angle);

        // Move enemy towards turret
        position.setX(position.getX() + moveX);
        position.setY(position.getY() + moveY);
    }
    
    //Buff class implementation; buffs make the enemies move faster
    private class Buff {
        private double duration; // Duration of the buff in seconds
        private double speedMultiplier; // Multiplier for enemy speed while buff is active

        public Buff(double duration, double speedMultiplier) {
            this.duration = duration;
            this.speedMultiplier = speedMultiplier;
        }

         // Apply buff effect (increase enemy speed)
        public void apply() {
            stepSize *= speedMultiplier;

            // Schedule task to remove buff after duration
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    removeBuff();
                }
            }, (long) (duration * 1000)); // Convert duration to milliseconds
        }

        public void removeBuff() {
            // Remove buff effect (e.g., restore original enemy speed)
            stepSize /= speedMultiplier;
            // Remove buff from list of active buffs
            activeBuffs.remove(this);
        }
    }

    // Method to apply a buff to the enemy
    public void applyBuff(double duration, double speedMultiplier) {
        Buff buff = new Buff(duration, speedMultiplier);
        buff.apply();
        activeBuffs.add(buff); // Add buff to list of active buffs
    }
}
