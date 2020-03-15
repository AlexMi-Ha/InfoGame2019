import greenfoot.*;

public class NannoidPlate extends Actor
{
    private int speed = 7;
    
    public NannoidPlate() {
        setImage(new GreenfootImage("textures/games/nannoid/plate.png"));
    }
    
    public void act() 
    {   
        if(!NannoidWorld.getWorld().getOver()) {
            if(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) 
                left();
            else if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) 
                right();
        }
    }    
    
    public void left() {
        if(getX() > getImage().getWidth() / 2)
            setLocation(getX() - speed, getY());
    }
    
    public void right() {
        if(getX() < NannoidWorld.getWorld().getWidth() - getImage().getWidth() / 2)
            setLocation(getX() + speed, getY());
    }
}
