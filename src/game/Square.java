package game;
import java.awt.Graphics;

public class Square extends Polygon {
	boolean forward, left, right, backward;
	private int stepSize = 20;

    public Square(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
        forward = true;
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
        // Adjust the position of the square's vertices
        Point[] vertices = getPoints();
        if(right) {
        	for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point(vertices[i].getX() + stepSize, vertices[i].getY());
            }
        }
        if(left) {
        	for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point(vertices[i].getX() - stepSize, vertices[i].getY());
            }
        }
        if(forward) {
        	for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point(vertices[i].getX(), vertices[i].getY() + stepSize);
            }
        }
        if(backward) {
        	for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point(vertices[i].getX(), vertices[i].getY() - stepSize);
            }
        }
    }
    
}	
