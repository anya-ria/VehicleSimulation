import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Rocket subclass
 */
public class Rocket extends Vehicle
{
    public Rocket(VehicleSpawner origin) {
        super(origin);
        
        maxSpeed = 2.5;
        speed = maxSpeed;
        
        halfWidth = getImage().getWidth() / 2;
        halfHeight = getImage().getHeight() / 2;
    }

    public void act()
    {
        super.act();
    }

    public boolean checkHitPedestrian() {
        Alien alien1 = (Alien) getOneObjectAtOffset(halfWidth, -halfHeight, Alien.class);
        Alien alien2 = (Alien) getOneObjectAtOffset(halfWidth, 0, Alien.class);
        Alien alien3 = (Alien) getOneObjectAtOffset(halfWidth, halfHeight, Alien.class);
        
        if(alien1 != null || alien2 != null || alien3 != null) {
            if(alien1 != null) {
                alien1.knockDown(); // injured the alien that gets hit
            }
            if(alien2 != null) {
                alien2.knockDown(); 
            }
            if(alien3 != null) {
                alien3.knockDown(); 
            }
            return true;
        }
        return false;
    }
}
