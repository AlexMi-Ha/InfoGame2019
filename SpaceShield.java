import greenfoot.*;

//shield object in SpaceInvaders

public class SpaceShield extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[4];    //images for the shield
    private int health;     //health
    
    public SpaceShield() {
        images[0] = new GreenfootImage("textures/games/spaceinvader/barrier.png");
        images[1] = new GreenfootImage("textures/games/spaceinvader/barrier_broken1.png");
        images[2] = new GreenfootImage("textures/games/spaceinvader/barrier_broken2.png");
        images[3] = new GreenfootImage("textures/games/spaceinvader/barrier_broken3.png");
        setImage(images[0]);
        
        health = 4;
    }
    //this shield got hit by a bullet
    public void hit() {
        health -= 1;
        
        if(health == 3)
            setImage(images[1]);
        else if(health == 2)
            setImage(images[2]);
        else if(health == 1)
            setImage(images[3]);
        else if(health == 0)
            SpaceWorld.getWorld().removeObject(this);
    }
}
