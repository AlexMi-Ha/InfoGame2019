import greenfoot.*;

public class FroggerTree extends Actor
{
    private int dx;
    
    public FroggerTree(int dx) {
        this.dx = dx;
        setImage(new GreenfootImage("textures/games/frogger/log.png"));
    }
    //Drive to the edge of world and then delete it + Carry a player on the tree
    public void act() 
    {       
        if(!FroggerWorld.getWorld().getDead()) {
            Frog f = (Frog)getOneIntersectingObject(Frog.class);
            if(f != null ) {
                f.setLocation(f.getX() + dx, f.getY());
                if(f.getX() < 0 || f.getX() > FroggerWorld.getWorld().getWidth())
                    FroggerWorld.getWorld().die();
            }
        }
        setLocation(getX() + dx, getY());
        if(getX() < -100 || getX() > FroggerWorld.getWorld().getWidth() + 100)
            FroggerWorld.getWorld().removeObject(this);
    }    
}
