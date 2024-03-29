import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction; // direction is always -1 or 1, for moving down or up, respectively
    protected boolean awake, entering;
    
    protected static GreenfootSound[] alienSounds;
    protected static int alienSoundsIndex;
    
    protected abstract void slowDown();

    public Pedestrian(int direction) {
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        entering = true;
        this.direction = direction;
    }
    
    public static void init() {
        alienSoundsIndex = 0;
        alienSounds = new GreenfootSound[20];
        for(int i = 0; i < alienSounds.length; i++) {
            alienSounds[i] = new GreenfootSound("alienSound.wav");
        }
    }
    
    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Awake is false if the Pedestrian is "knocked down"
        if (awake){
            // Check in the direction I'm moving vertically for a Vehicle -- and only move if there is no Vehicle in front of me.
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                setLocation (getX(), getY() + (speed*direction));
            }
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > getWorld().getHeight() - 30){
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public void knockDown() {
        if(this instanceof Alien) {
            playAlienSound();
        }
        speed = 0;
        setRotation (direction * 90);
        awake = false;
    }
    
    public void playAlienSound() {
        alienSounds[alienSoundsIndex].setVolume(50);
        alienSounds[alienSoundsIndex].play();
        alienSoundsIndex++;
        if(alienSoundsIndex >= alienSounds.length) {
            alienSoundsIndex = 0;
        }
    }

    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe() {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }

    public boolean isAwake() {
        return awake;
    }
}
