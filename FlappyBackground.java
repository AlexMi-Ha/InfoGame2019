import greenfoot.*;

public class FlappyBackground extends Actor
{
    private int dx = -1;
    private boolean moving = false;
    
    public FlappyBackground() {
        setImage(new GreenfootImage("textures/games/flappy/moving_bg.png"));
    }

    public void act() {
        if(moving) {
            setLocation(getX() + dx, getY());
        }
    }    
    
    public boolean isAtBorder() {
        return getX() < -(getImage().getWidth() / 2);
    }
    
    public void moveWith(FlappyBackground fg) {
        setLocation(fg.getX() + fg.getImage().getWidth() + dx, getY());
    }
    
    public void setMoving(boolean b) {
        moving = b;
    }
    
}
