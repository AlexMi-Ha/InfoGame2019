import greenfoot.*;

public class ControlPanel extends Actor
{
    public ControlPanel() {
        GreenfootImage img = new GreenfootImage("textures/menu/controls.png");
        //img.scale(900,527);
        setImage(img);
    }  
    
    public void act() {
        if(Greenfoot.isKeyDown("escape")) {
            MainMenu.getWorld().setKeyLock(false);
            MainMenu.getWorld().removeObject(this);
        }
    }
}
