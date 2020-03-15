import greenfoot.*;

public class FroggerCar extends Actor
{
    private int dx;
    
    public FroggerCar(int dx) {
        this.dx = dx;
        GreenfootImage img;
        int r = Greenfoot.getRandomNumber(4);
        img = new GreenfootImage("textures/games/frogger/car"+ (r + 1) + ".png");
        if(dx < 0) 
            img.mirrorHorizontally();
        setImage(img);
    }
    //Drive to the edge of world and then delete it
    public void act() 
    {
        setLocation(getX() + dx, getY());
        if(getX() < 0 || getX() > FroggerWorld.getWorld().getWidth())
            FroggerWorld.getWorld().removeObject(this);
    }    
}
