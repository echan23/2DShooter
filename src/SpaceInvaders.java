package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;

class SpaceInvaders extends Game{
	static int counter = 0;
	private Point center;
	private Turret turret;
	
	public SpaceInvaders() {
	    super("YourGameName!",800,600);
	    this.setFocusable(true);
		this.requestFocus();
		center = new Point(width/2, height/2);
		turret = new Turret(center, 270);
		this.addKeyListener(turret);
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
    }
  
	public static void main (String[] args) {
   		YourGameName a = new YourGameName();
		a.repaint();
    }
	
}
