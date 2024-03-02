package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.util.ArrayList;

class YourGameName extends Game{
	static int counter = 0;
	private Point center;
	private Element one;
	private ArrayList<Element> elements;
	
	public YourGameName() {
	    super("YourGameName!",800,600);
	    this.setFocusable(true);
		this.requestFocus();
		this.elements = new ArrayList<Element>();
		Point[] squarePoints = {
	            new Point(0, 0),
	            new Point(20, 0),
	            new Point(20, 20),
	            new Point(0, 20)
	    };
		center = new Point(width/2, height/2);
		one = new Element(squarePoints, center, 90);
		this.addKeyListener(one);
		elements.add(one);
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
		for(Element element: elements){
			element.move();
			element.paint(brush);
		}
    }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
    }
	
}