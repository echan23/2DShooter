package game;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class PowerUp extends Polygon{
    private Turret turret;
    private boolean active;
    private static final int DURATION = 5000; // Duration of the power-up effect in milliseconds
    private Timer timer;
    private static Point[] circlePoints = new Point[360];

    static{
        circlePoints = new Point[360];
        for (int i = 0; i < 360; i++) {
            double angle = i * 2 * Math.PI / 360;
            int x = (int) (25 * Math.cos(angle)); // Adjust the center and radius as needed
            int y = (int) (25 * Math.sin(angle)); // Adjust the center and radius as needed
            circlePoints[i] = new Point(x, y);
        }
    }

    public PowerUp(Turret turret, Point inPosition, double inRotation) {
        super(circlePoints, inPosition, inRotation);
        this.turret = turret;
    }

    //Paints the powerup item
    public void paint(Graphics brush) {
        brush.setColor(Color.PINK);
        int l = this.getPoints().length;
        int[] x = new int[l];
        int[] y = new int[l];
        //Iterate through the array of points retrieving the x and y values for each
        for(int i = 0; i < l; i++) {
            x[i] = (int) this.getPoints()[i].getX();
            y[i] = (int) this.getPoints()[i].getY();
        }
        brush.fillPolygon(x, y, l);
    }
    
    // Method to activate the power-up effect
    public void applyPowerUp() {
        turret.togglePowerUp();
        active = true;
    
        // Start the timer to deactivate the power-up after the duration using an anonymous class
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deactivatePowerUp();
            }
        }, DURATION);
    }

    // Method to deactivate the power-up effect
    private void deactivatePowerUp() {
        turret.togglePowerUp();
        active = false;
        timer.cancel(); // Cancel the timer task
    }

    
}	
