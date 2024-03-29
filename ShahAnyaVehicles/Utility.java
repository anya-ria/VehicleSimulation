import greenfoot.*;

public class Utility  
{
    /**
     * density should be 1-100. 100 will be almost completely white
     */
    public static GreenfootImage drawStars (int width, int height, int density){

        Color[] swatch = new Color [64];
        int red = 128;
        int blue = 192;

        // Build a color pallete out of shades of near-white yellow and near-white blue      
        for (int i = 0; i < swatch.length/2; i++){ // first half blue tones
            swatch[i] = new Color (red, 240, 255);
            red += 2;
        }
        for (int i = swatch.length/2; i < swatch.length; i++){ // second half yellow tones
            swatch[i] = new Color (255, 255, blue);
            blue ++;
        }

        // The temporary image, my canvas for drawing
        GreenfootImage temp = new GreenfootImage (width, height);
        //temp.setColor (Color.BLACK);
        //temp.fill();

        // Run this loop one time per "density"

        for (int i = 0; i < density; i++){
            for (int j = 0; j < 100; j++){ // draw 100 circles
                int randSize;
                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(swatch.length);
                int randTrans = Greenfoot.getRandomNumber(220) + 35; // around half transparent
                temp.setColor (swatch[randColor]);

                //setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);

                int tempVal = Greenfoot.getRandomNumber(250);
                if (tempVal >= 1){
                    //randSize = 2;
                    temp.drawRect (randX, randY, 0, 0);
                }else{
                    randSize = Greenfoot.getRandomNumber (2) + 2;
                    temp.fillOval (randX, randY, randSize, randSize);
                }
                // silly way to draw a dot..
            }
        }

        return temp;
    }
}
