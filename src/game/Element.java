package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Element extends Polygon implements KeyListener{
	boolean forward, left, right, backward, rotLeft, rotRight;
	private int stepSize = 20;

    public Element(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
        forward = false;
        left = false;
        right = false;
        backward = false;
        rotLeft = false;
        rotRight = false;
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
        double rotationIncrement = 5.0; // Rotation increment in degrees
        double moveIncrement = 5.0; // Move increment in pixels
    
        // Rotate the element if appropriate keys are pressed
        if(left) {
            rotation -= rotationIncrement;
        }
        if(right) {
            rotation += rotationIncrement;
        }
        // Move the element in the direction it's facing
        double dx = moveIncrement * Math.cos(Math.toRadians(rotation)); // Calculate change in x
        double dy = moveIncrement * Math.sin(Math.toRadians(rotation)); // Calculate change in y
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
