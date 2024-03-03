package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Turret extends Polygon implements KeyListener{
	boolean forward, left, right, backward;
	private double stepSize = 5.0, rotationIncrement = 5.0;
    private static Point[] turretPoints = {
        new Point(0, 0),
        new Point(0, 60),
        new Point(30, 60),
        new Point(30, 35),
        new Point(50, 35),
        new Point(50, 25),
        new Point(30, 25),
        new Point(30, 0)
    };

    public Turret(Point inPosition, double inRotation) {
        super(turretPoints, inPosition, inRotation);
        forward = false;
        left = false;
        right = false;
        backward = false;
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
        brush.fillPolygon(x, y, l);
    }
    
    public void move() {    
        // Rotate the element if appropriate keys are pressed
        if(left) {
            rotation -= rotationIncrement;
        }
        if(right) {
            rotation += rotationIncrement;
        }
        // Move the element in the direction it's facing
        double dx = stepSize * Math.cos(Math.toRadians(rotation)); // Calculate change in x
        double dy = stepSize * Math.sin(Math.toRadians(rotation)); // Calculate change in y
        if(forward) {
            position.setX(position.getX() + dx);
            position.setY(position.getY() + dy);
        }
        if(backward) {
            position.setX(position.getX() - dx);
            position.setY(position.getY() - dy);
        }
    }

    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == 'w' || keyChar == 'W') {
            forward = true;
        } else if (keyChar == 's' || keyChar == 'S') {
            backward = true;
        } else if (keyChar == 'a' || keyChar == 'A') {
            left = true;
        } else if (keyChar == 'd' || keyChar == 'D') {
            right = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if(keyChar == 'w' || keyChar == 'W') {
            forward = false;
        }else if(keyChar == 's' || keyChar == 'S') {
            backward = false;
        }else if(keyChar == 'a' || keyChar == 'A') {
            left = false;
        }else if(keyChar == 'd' || keyChar == 'D') {
            right = false;
        }
    }
    
    public void keyTyped(KeyEvent e){}
}	
