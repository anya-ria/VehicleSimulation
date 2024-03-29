import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class ShootingStar extends Effect
{
    private int actsLeft;
    private double speed;
    private boolean turnedAround;
    private GreenfootSound stars;
    
    public ShootingStar() {
        actsLeft = 360; // 6 seconds
        speed = 3.5;
        turnedAround = false;
        stars = new GreenfootSound("starsSound.wav");
        stars.setVolume(50);
    }
    
    public void addedToWorld(World w) {
        if(image == null) {
            image = Utility.drawStars(4000, 4000, 5000);
        }
        stars.play();
        setImage(image);
    }
    
    /**
     * Act - do whatever the ShootingStars wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turn(1);
        actsLeft--;
        if(actsLeft <= 60) {
            fade(actsLeft, 60);
        }
        // vary speed
        int randomChange = Greenfoot.getRandomNumber(7) - 3; // -3 to 3
        double change = randomChange / 10.0; // -0.3 to 3

        if (getX() > 700 && !turnedAround && Greenfoot.getRandomNumber(120) == 0){
            speed *= -1;
            turnedAround = true;
        }

        if (getX() > 600 && !turnedAround){
            speed *= -1;
            turnedAround = true;
        }

        setLocation (getX() + speed, getY());
        
        // If acts left hits zero, no more stars
        if(actsLeft == 0) {
            getWorld().removeObject(this);
            stars.pause();
        }
    }
}
