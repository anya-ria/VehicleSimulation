import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

public class Vacuum extends Effect
{
    private int maxSize;
    private int transparency;
    private int speed;
    private int radius;
    private int steps;
    
    public Vacuum(int maxSize) {
        image = new GreenfootImage(maxSize, maxSize);
        transparency = 255;
        speed = Math.max((int) Math.sqrt(maxSize / 2), 1);
        radius = 5;
        steps = (maxSize) / speed;
        this.maxSize = maxSize;
        redraw();
        this.setImage(image);
    }
    
    /**
     * Act - do whatever the Vacuum wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        redraw();
        if(radius + speed <= maxSize) {
            radius += speed;
        }
        else {
            ArrayList <Vehicle> vehicles = (ArrayList <Vehicle>) getIntersectingObjects(Vehicle.class);
            for(Vehicle v : vehicles) {
                getWorld().removeObject(v);
            }
            ArrayList <Pedestrian> pedestrians = (ArrayList <Pedestrian>) getObjectsInRange (maxSize, Pedestrian.class);
            for (Pedestrian p : pedestrians){
                getWorld().removeObject(p);
            }
            
            getWorld().removeObject(this);
        }
    }
    
    public void redraw() {
        if(transparency - (255 / steps) > 0) {
            transparency -= (255 / steps);
        }
        else {
            transparency = 0;
        }
        image.setTransparency(transparency);
        image.fillOval((maxSize - radius)/2, (maxSize - radius)/2, radius, radius);
        image.setTransparency(transparency);
    }
}
