import greenfoot.*;

public class SplashScreen extends World
{

    public SplashScreen()
    {    
        super(500, 500, 1); 
        
        GreenfootImage img = new GreenfootImage(40,40);
        img.setColor(new Color(0,0,5));
        img.fill();
        setBackground(img);
        
        addObject(new Splash(), getWidth() / 2, getHeight() / 2);
    }
}
