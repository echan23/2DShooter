package game;
import java.awt.Graphics;

public class Square extends Polygon {
	boolean forward, left, right, backward;
	private int stepSize = 20;

    public Square(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
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
        if (right) {
            position.setX(position.getX() + stepSize); // Update x position
        }
        if (left) {
            position.setX(position.getX() - stepSize); // Update x position
        }
        if (forward) {
            position.setY(position.getY() - stepSize); // Update y position
        }
        if (backward) {
            position.setY(position.getY() + stepSize); // Update y position
        }
    }

    public void keyPressed(KeyEvent e){

    }

    public void keyReleased(KeyEvent e){
        
    }
}	
