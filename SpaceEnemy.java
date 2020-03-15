import greenfoot.*;
import java.util.List;

//enemy in space invader game

public class SpaceEnemy extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[2];		//texture storage
    private int type;   //type 0: 10 points    1: 20 points    2: 30 points
    private boolean isInGame;   //currently in the game
    private boolean lowest;     //if it is in the lowest row
    
    public SpaceEnemy(int type, boolean lowest) {
        if(type == 0) {
            images[0] = new GreenfootImage("textures/games/spaceinvader/enemy3.png");
            images[1] = new GreenfootImage("textures/games/spaceinvader/enemy3-2.png");
        }else if(type == 1) {
            images[0] = new GreenfootImage("textures/games/spaceinvader/enemy2.png");
            images[1] = new GreenfootImage("textures/games/spaceinvader/enemy2-2.png"); 
        }else {
            images[0] = new GreenfootImage("textures/games/spaceinvader/enemy1.png");
            images[1] = new GreenfootImage("textures/games/spaceinvader/enemy1-2.png");
        }
        setImage(images[0]);
        
        this.type = type;
        isInGame = true;
        this.lowest = lowest;
    }
    //test after getting killed for the new lowest in the column
    public boolean testforNewLowest() {
        List<SpaceEnemy> enemieLower;
        //test for enemies under the current enemy
        for(int i = getY(); i < SpaceWorld.getWorld().getHeight() - 50; i+=25) {
            enemieLower = getObjectsAtOffset(0, i, SpaceEnemy.class);
            
            if(enemieLower.size() > 0 && enemieLower.get(0) != this) 
                return false;
        }
        
        List<SpaceEnemy> enemieHigher;
        //test for enemies above the current enemy
        for(int i = 0; i > -getY(); i -=25) {
            enemieHigher = getObjectsAtOffset(0, i, SpaceEnemy.class);
            
            if(enemieHigher.size() > 0 && enemieHigher.get(0) != this) {
                enemieHigher.get(0).setLowest(true);
                SpaceWorld.getWorld().updateLowerList();
                return true;
            }
        }
        return false;
    }
    //tries spawns a bullet  exeption: if the enemy gets hit & killed while spawning a bullet
    public void shoot() {
        try {
            SpaceBullet bullet = new SpaceBullet(1, this);
            SpaceWorld.getWorld().addObject(bullet, getX(), getY() + 10);
        }catch(Exception e) {
            //e.printStackTrace();
        }
    }
    //move 
    public void moveRight() {
        setLocation(getX() + 10, getY());
    }
    
    public void moveLeft() {
        setLocation(getX() - 10, getY());
    }
    
    public void moveDown() {
        setLocation(getX(), getY() + 10);
    }
    //change the image for the animation
    public void changeImage() {
        if(getImage() == images[0])
            setImage(images[1]);
        else 
            setImage(images[0]);
    }
    //kills this enemy
    public void kill() {
        SpaceWorld.getWorld().removeObject(this);
        isInGame = false;
        lowest = false;
        SpaceWorld.getWorld().updateLowerList();
    }
    
    //setter / getter
    public void setLowest(boolean lowest) {
        this.lowest = lowest;
    }
    
    public boolean isLowest() {
        return lowest;
    }
    
    public int getType() {
        return type;
    }
    
    public boolean isInGame() {
       return isInGame;
    }
}
