import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Astronaut subclass
 */
public class Astronaut extends Pedestrian
{
    private Alien targetAlien;
    private ArrayList<Alien> aliens;
    
    private static GreenfootSound[] captureSounds;
    private static int captureSoundsIndex;

    public Astronaut(int direction) {
        super(direction);

        maxSpeed = 1.0;
        speed = maxSpeed;
    }
    
    public static void init() {
        captureSoundsIndex = 0;
        captureSounds = new GreenfootSound[20];
        for(int i = 0; i < captureSounds.length; i++) {
            captureSounds[i] = new GreenfootSound("captureSound.mp3");
        }
    }

    public void act()
    {
        if(targetAlien != null && targetAlien.getWorld() == null) {
            targetAlien = null;
        }
        if(targetAlien == null || VehicleWorld.getDistance(this, targetAlien) > 20) {
            targetClosestAlien();
        }
        // If the current target alien exists,
        // then move towards it. 
        if(targetAlien != null) {
            moveTowardAlien();
        }

        if(awake) {
            // Check in the direction I'm moving vertically for a Vehicle -- and only move if there is no Vehicle in front of me.
            if(getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null) {
                setLocation (getX(), getY() + (speed*direction));
            }
            if(direction == -1 && getY() < 100) {
                getWorld().removeObject(this);
            } else if(direction == 1 && getY() > getWorld().getHeight() - 30) {
                getWorld().removeObject(this);
            }
        }
    }

    public void slowDown() {
        speed = 0.5;
    }

    public void playCaptureSound() {
        captureSounds[captureSoundsIndex].setVolume(50);
        captureSounds[captureSoundsIndex].play();
        captureSoundsIndex++;
        if(captureSoundsIndex >= captureSounds.length) {
            captureSoundsIndex = 0;
        }
    }

    /**
     * Method that constantly check for closer aliens
     */
    public void targetClosestAlien() {
        double closestTargetDistance = 0;
        double distanceToActor;
        // Gets a list of all the aliens in the World,
        // in the specified range, and casts it 
        // to an ArrayList.
        aliens = (ArrayList<Alien>) getObjectsInRange(20, Alien.class);
        if (aliens.size() == 0) {
            aliens = (ArrayList<Alien>) getObjectsInRange(100, Alien.class);
        }
        if (aliens.size() == 0) {
            aliens = (ArrayList<Alien>) getObjectsInRange(150, Alien.class);
        }

        if (aliens.size() > 0) {
            // Sets the first alien in the list
            // as the target.
            targetAlien = aliens.get(0);
            // World method gets the distance to 
            // the target alien.
            closestTargetDistance = VehicleWorld.getDistance(this, targetAlien);
            // Loop throught all the objects in the
            // ArrayList to find the closest alien.
            for (Alien a : aliens) {
                // Measures the distance from me (astronaut)
                distanceToActor = VehicleWorld.getDistance(this, a);
                // If an alien is found that is closer
                // than the current target alien,
                // then the targets will change.
                if (distanceToActor < closestTargetDistance) {
                    targetAlien = a;
                    closestTargetDistance = distanceToActor;
                }
            }
            enableStaticRotation();
            // Only capture the alien if it is awake.
            if(targetAlien.isAwake()) {
                turnTowards(targetAlien.getX(), targetAlien.getY());
                move(speed);
            }
        }
    }

    /**
     * Method that moves toward the alien.
     */
    public void moveTowardAlien() {
        if (targetAlien.isAwake() && VehicleWorld.getDistance(this, targetAlien) < 18) {
            targetAlien.slowDown();
            enableStaticRotation();
            setLocation(targetAlien.getX(), targetAlien.getY()); // Allows the astronaut to capture the alien.
            sleepFor(30);
            getWorld().removeObject(targetAlien);
            playCaptureSound();
        }
        else {
            move(speed);
        }
    }
}
