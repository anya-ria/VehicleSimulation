import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Effect extends SuperSmoothMover
{
    protected GreenfootImage image;
    
    // Transparency values (transperent -> opaque: 0 -> 255)
    protected void fade(int timeLeft, int totalFadeTime) {
        double percent = timeLeft / (double) totalFadeTime;
        if(percent > 1.00) return;
        int newTransparency = (int) (percent * 255);
        image.setTransparency (newTransparency);
    }
}
