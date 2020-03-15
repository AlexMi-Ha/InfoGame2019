import greenfoot.*;

public class FlappyGround extends Actor
{
    private int dx = -3;
    private boolean moving = false;
    
    public FlappyGround() {
        setImage(new GreenfootImage("textures/games/flappy/flappy_ground.png"));
    }
    
    public FlappyGround(boolean moving) {
        setImage(new GreenfootImage("textures/games/flappy/flappy_ground.png"));
        this.moving = moving;
    }
    
    public void act() {
        if(moving) {
            setLocation(getX() + dx, getY());
        }
    }    
    
    public boolean isAtBorder() {
        return getX() < -(getImage().getWidth() / 2);
    }
    
    public void moveWith(FlappyGround fg) {
        setLocation(fg.getX() + fg.getImage().getWidth() + dx, getY());
    }
    
    public void setMoving(boolean b) {
        moving = b;
    }
    
}
