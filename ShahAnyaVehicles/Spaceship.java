import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Spaceship subclass
 */
public class Spaceship extends Vehicle
{
    private static GreenfootSound[] spaceshipSounds;
    private static int spaceshipSoundsIndex;
    
    public Spaceship(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first

        maxSpeed = 1.5;
        speed = maxSpeed;

        halfHeight = getImage().getWidth() / 2;
    }
    
    public static void init() {
        spaceshipSoundsIndex = 0;
        spaceshipSounds = new GreenfootSound[20];
        for(int i = 0; i < spaceshipSounds.length; i++) {
            spaceshipSounds[i] = new GreenfootSound("ufoSound.mp3");
        }
    }
    
    public void act()
    {
        super.act();
    }

    public boolean checkHitPedestrian() {
        Astronaut astronaut1 = (Astronaut) getOneObjectAtOffset(0, halfHeight, Astronaut.class);
        Astronaut astronaut2 = (Astronaut) getOneObjectAtOffset(0, -halfHeight, Astronaut.class);
        if(astronaut1 != null || astronaut2 != null) {
            if(astronaut1 != null) {
                playSpaceshipSound();
                moving = false;
                sleepFor(30);
                getWorld().removeObject(astronaut1);
            }
            else if(astronaut2 != null) {
                playSpaceshipSound();
                moving = false;
                sleepFor(30);
                getWorld().removeObject(astronaut2);
            }
            return true;
        }
        return false;
    }
    
    public void playSpaceshipSound() {
        spaceshipSounds[spaceshipSoundsIndex].setVolume(50);
        spaceshipSounds[spaceshipSoundsIndex].play();
        spaceshipSoundsIndex++;
        if(spaceshipSoundsIndex >= spaceshipSounds.length) {
            spaceshipSoundsIndex = 0;
        }
    }
}
