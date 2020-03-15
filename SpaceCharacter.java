import greenfoot.*;

//character of SpaceInvaders

public class SpaceCharacter extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[3];  //images for the character
    private boolean shootPossible;      //if shooting is currently possible
    private int lives;  //lives
    
    private GreenfootSound sound_hit;
    
    public SpaceCharacter() {
        images[0] = new GreenfootImage("textures/games/spaceinvader/ship.png");
        images[1] = new GreenfootImage("textures/games/spaceinvader/ship_die1.png");
        images[2] = new GreenfootImage("textures/games/spaceinvader/ship_die2.png");
        setImage(images[0]);
        
        shootPossible = true;
        lives = 3;
        
        try {
            sound_hit = new GreenfootSound("sound_effects/spaceship_hit.wav");
            sound_hit.setVolume(100);
        }catch(Exception e) {}
    }
    
    public void act() {
        //try to move with the speed 2
        tryMove(2);
        //shoot on space input if its possible 
        if(Greenfoot.isKeyDown("space") && shootPossible)
            shoot();
    }    
    
    public void tryMove(int speed) {
        int dx = 0, dy = 0;
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) 
            dx += 1;
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) 
            dx -= 1;
            
        for (int i = 0; i < speed; i++) {
            setLocation(getX() + dx, getY());
            if (getX() < 15 || getX() > SpaceWorld.getWorld().getWidth() - 15) 
                setLocation(getX() - dx, getY());
        }
    }
    //the character has been hit by a bullet
    public void hit() {
        lives -= 1;
        SpaceWorld.getWorld().getHealthD().removePoints(1);
        
        try {
            sound_hit.play();
        }catch(Exception e) {}
        
        setImage(images[1]);
        Greenfoot.delay(15);
        setImage(images[2]);
        Greenfoot.delay(15);
        
        if(lives == 0)  //end the game with 0 lives 
            SpaceWorld.getWorld().gameOver();
        else
            setImage(images[0]);
    }
    //spawns a bullet
    public void shoot() {
        SpaceBullet bullet = new SpaceBullet(0, this);      
        SpaceWorld.getWorld().addObject(bullet, getX(), getY() - 10);
        shootPossible = false;
    }
    
    //setter
    public void shootPossible(boolean arg) {
        shootPossible = arg;
    }
}
