    package game;
    import java.awt.Graphics;
    import java.util.List;
    import java.util.ArrayList;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyListener;

    public class Turret extends Polygon implements KeyListener{
        private boolean forward, left, right, backward;
        private final double stepSize = 5.0, rotationIncrement = 5.0;
        // Set shape for the turret
        private static final Point[] turretPoints = {
            new Point(0, 0),
            new Point(0, 60),
            new Point(30, 60),
            new Point(30, 35),
            new Point(50, 35),
            new Point(50, 25),
            new Point(30, 25),
            new Point(30, 0)
        };
        private List<Bullet> bullets;
        // Constructor initializes turret with default values and creates bullet list
        public Turret(Point inPosition, double inRotation) {
            super(turretPoints, inPosition, inRotation);
            forward = false;
            left = false;
            right = false;
            backward = false;
            bullets = new ArrayList<>();
        }
    
        // Draws the turret polygon on the screen
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
    
        // Moves the turret based on key inputs
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
    
        // Detects key press events and updates corresponding movement flags
        public void keyPressed(KeyEvent e) {
            char keyChar = e.getKeyChar();
            if(keyChar == 'w' || keyChar == 'W') {
                forward = true;
            }else if (keyChar == 's' || keyChar == 'S') {
                backward = true;
            }else if (keyChar == 'a' || keyChar == 'A') {
                left = true;
            }else if (keyChar == 'd' || keyChar == 'D') {
                right = true;
            }else if(e.getKeyCode() == KeyEvent.VK_SPACE) { // Check for spacebar
                shootBullet();
            }
        }
    
        // Detects key release events and updates corresponding movement flags
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
    
        // Unused method required by KeyListener interface
        public void keyTyped(KeyEvent e){}
    
        // Inner class representing a bullet fired from the turret
        class Bullet extends Polygon{
            private static final double BULLET_SPEED = 15.0;
            private static final Point[] bulletPoints = {
                new Point(0, 0),
                new Point(10, 0),
                new Point(10, 10),
                new Point(0, 10)
            };
            // Constructor initializes bullet with points and position
            public Bullet(Point position, double rotation) {
                super(bulletPoints, position, rotation);
            }
    
            // Moves the bullet according to its rotation
            public void move() {
                double dx = BULLET_SPEED * Math.cos(Math.toRadians(rotation)); // Calculate change in x
                double dy = BULLET_SPEED * Math.sin(Math.toRadians(rotation)); // Calculate change in y
                this.position.setX(position.getX() + dx);
                this.position.setY(position.getY() + dy);
            }
    
            // Draws the bullet polygon on the screen
            public void paint(Graphics brush) {
                int l = this.getPoints().length;
                int[] x = new int[l];
                int[] y = new int[l];
                // Iterate through the array of points retrieving the x and y values for each
                for (int i = 0; i < l; i++) {
                    x[i] = (int) this.getPoints()[i].getX();
                    y[i] = (int) this.getPoints()[i].getY();
                }
                brush.fillPolygon(x, y, l);
            }
        }
    
        // Calculates and creates a bullet fired from the turret
        private void shootBullet() {
            double offsetX = Math.cos(Math.toRadians(rotation)) * 5 / 2; // Adjust for turret height
            double offsetY = Math.sin(Math.toRadians(rotation)) * 5 / 2; // Adjust for turret height
            Point bulletPosition = new Point(position.getX() + offsetX, position.getY() + offsetY);
            
            // Create a new bullet object with the calculated position
            Bullet bullet = new Bullet(bulletPosition, rotation);
            
            // Add the bullet to the list of bullets
            bullets.add(bullet);
        }
    
        // Updates bullet positions and draws them on the screen
        public void updateBullets(Graphics brush){
            for(Bullet bullet: bullets){
                bullet.move();
                bullet.paint(brush);
            }
        }
    }	
