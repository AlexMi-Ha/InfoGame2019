import greenfoot.*; 

//Pillar of FlappyBirds

public class Pillar extends Actor {
    
    private int SCROLL_SPEED = 3;
    
    public Pillar(boolean top) {
        if(top)
            setImage(new GreenfootImage("textures/games/flappy/pipe_top.png"));
        else
            setImage(new GreenfootImage("textures/games/flappy/pipe_bottom.png"));
    }
    //manage movement & despawning
    public void act() {
        if(FlappyBirdWorld.getWorld().getBird().getG() != 0) {
            setLocation(getX() - SCROLL_SPEED, getY());
            if(getX() <= 0)
                FlappyBirdWorld.getWorld().removeObject(this);
        }
    }    
    
    public void setVel(int vel) {
        SCROLL_SPEED = vel;
    }
}
