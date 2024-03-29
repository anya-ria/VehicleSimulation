import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Alien subclass
 */
public class Alien extends Pedestrian
{
    public Alien(int direction) {
        super(direction);
        
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
    }
    
    public void act()
    {
        super.act();
    }
    
    public void slowDown() {
        speed = 0.6;
    }
}
