import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BlackHole extends Actor
{
    private boolean entering;
    private int destination;

    private GreenfootImage blackHole;
    
    private GreenfootSound blackHoleSound;
    public BlackHole() {
        entering = true;
        blackHole = new GreenfootImage(300, 300);
        blackHole.setColor(Color.BLACK);
        blackHole.fillOval(0, 0, 299, 299);
        
        
        blackHoleSound = new GreenfootSound("blackHoleSound.wav");
        blackHoleSound.setVolume(100);
    }

    public void addedToWorld(World w) {
        blackHoleSound.play();
        destination = 427;
    }

    /**
     * Act - do whatever the BlackHole wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(entering) {
            turn(1);
            setLocation (getX(), getY() + 2);
            if(getY() >= destination) {
                entering = false;
                setImage(blackHole);
                removeTouching();
            }
        } 
        else {
            getWorld().addObject(new Vacuum(300), getX(), getY());
            getWorld().removeObject(this);
        }
    }

    public void removeTouching() {
        for (Vehicle v : getIntersectingObjects(Vehicle.class)){
            getWorld().removeObject (v);
        }
        for (Pedestrian p : getIntersectingObjects(Pedestrian.class)){
            getWorld().removeObject (p);
        }
    }
}
