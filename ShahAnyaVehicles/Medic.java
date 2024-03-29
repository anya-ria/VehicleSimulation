import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Medic subclass
 */
public class Medic extends Vehicle
{
    public Medic(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        maxSpeed = 2.0;
        speed = maxSpeed;
        
        halfWidth = getImage().getWidth() / 2;
        halfHeight = getImage().getHeight() / 2;
    }

    public void act()
    {
        super.act();
    }

    public boolean checkHitPedestrian() {
        Alien alien1 = (Alien) getOneObjectAtOffset(halfWidth, 0, Alien.class);
        Alien alien2 = (Alien) getOneObjectAtOffset(-halfWidth, -halfHeight, Alien.class);
        Alien alien3 = (Alien) getOneObjectAtOffset(-halfWidth, halfHeight, Alien.class);
        if(alien1 != null || alien2 != null || alien3 != null) {
            if(alien1 != null) {
                if(!alien1.isAwake()) {
                    alien1.healMe();
                }
            }
            if(alien2 != null) {
                if(!alien2.isAwake()) {
                    alien2.healMe();
                }
            }
            if(alien3 != null) {
                if(!alien3.isAwake()) {
                    alien3.healMe();
                }
            }
            return true;
        }
        return false;
    }
}
