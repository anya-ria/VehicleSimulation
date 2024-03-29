import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Asteroid subclass
 */
public class Asteroid extends Vehicle
{
    public Asteroid (VehicleSpawner origin) {
        super(origin);
        
        maxSpeed = 3.0;
        speed = maxSpeed;
        
        halfWidth = getImage().getWidth() / 2;
        halfHeight = getImage().getHeight() / 2;
    }
    
    public void act()
    {
        super.act();
    }
    
    public boolean checkHitPedestrian() {
        Astronaut astronaut1 = (Astronaut) getOneObjectAtOffset(halfWidth, -halfHeight, Astronaut.class);
        Astronaut astronaut2 = (Astronaut) getOneObjectAtOffset(halfWidth, 0, Astronaut.class);
        Astronaut astronaut3 = (Astronaut) getOneObjectAtOffset(halfWidth, halfHeight, Astronaut.class);
        if(astronaut1 != null || astronaut2 != null || astronaut3 != null) {
            if(astronaut1 != null) {
                astronaut1.speed = 3.0;
            }
            if(astronaut2 != null) {
                astronaut2.speed = 3.0;
            }
            if(astronaut3 != null) {
                astronaut3.speed = 3.0;
            }
            return true;
        }
        return false;
    }
}
