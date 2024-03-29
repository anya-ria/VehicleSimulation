import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LaneSpawnCheck extends Actor
{
    private GreenfootImage image;
    
    public LaneSpawnCheck(int x, int y, Vehicle theTestVehicle) {
        image = new GreenfootImage(x + 15, y);
        image.fillRect(0, 0, x, y);
        image.setTransparency(0);
        setImage(image);
    }
    
    public void act()
    {
        getWorld().removeObject(this);
    }
    
    public boolean vehiclePresent() {
        return getOneIntersectingObject(Vehicle.class) != null;
    }
}
