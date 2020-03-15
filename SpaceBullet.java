import greenfoot.*;
import java.util.List;

//bullet in Spaceinvaders

public class SpaceBullet extends Actor
{
    private int type;     //type 0: bullet from character,   type 1: bullet from enemy
    private SpaceCharacter character;       //character var
    
    private GreenfootSound sound_shot;
    
    public SpaceBullet(int type, Object sender) {
        GreenfootImage img = new GreenfootImage(5, 20);
        img.setColor(Color.WHITE);
        img.fill();
        setImage(img);
        
        try {
            if(type == 1)  {
                sound_shot = new GreenfootSound("sound_effects/laser_shot.mp3");
                sound_shot.setVolume(50);
            }else {
                sound_shot = new GreenfootSound("sound_effects/laser_shot.wav");
                sound_shot.setVolume(90);
            }
            sound_shot.play();
        }catch(Exception e) {}
        
        if(type == 0)
            character = (SpaceCharacter)sender;
            
        this.type = type;
    }
    
    public void act() {
        if(type == 0) 
            characterBullet();  //shoot the bullet as a Character Bullet
        else if(type == 1) 
            enemyBullet();      //shoot the bullet as a Enemy Bullet
    }
    
    //Character Bullet
    public void characterBullet() {
        setLocation(getX(), getY() - 3);
        //check for a hit
        List<SpaceEnemy> enemyList = getIntersectingObjects(SpaceEnemy.class);
        SpaceShield shieldhit = (SpaceShield)getOneIntersectingObject(SpaceShield.class);
        
        if(enemyList.size() > 0) {          //Enemy Hit
            if(enemyList.get(0).isInGame()) {
                enemyList.get(0).testforNewLowest();
                enemyList.get(0).setLowest(false);
                
                if(enemyList.get(0).getType() == 0) 
                    SpaceWorld.getWorld().getScoreD().addPoints(10);
                if(enemyList.get(0).getType() == 1) 
                    SpaceWorld.getWorld().getScoreD().addPoints(20);
                if(enemyList.get(0).getType() == 2) 
                    SpaceWorld.getWorld().getScoreD().addPoints(30);
                    
                enemyList.get(0).kill();
                character.shootPossible(true);
                SpaceWorld.getWorld().removeObject(this);
            }
        }else if(getY() <= 20) {            //Bullet out of map
            character.shootPossible(true);
            SpaceWorld.getWorld().removeObject(this);
        }else if(shieldhit != null) {       //Shield hit
            shieldhit.hit();
            character.shootPossible(true);
            SpaceWorld.getWorld().removeObject(this);
        }
    }
    
    //EnemyBullet
    public void enemyBullet() {
        setLocation(getX(), getY() + 3);
        //check for a hit
        SpaceCharacter characterhit = (SpaceCharacter)getOneIntersectingObject(SpaceCharacter.class);
        SpaceShield shieldhit = (SpaceShield)getOneIntersectingObject(SpaceShield.class);
        
        if(characterhit != null) {          //character hit
            characterhit.hit();
            SpaceWorld.getWorld().removeObject(this);
        }else if(getY() >= SpaceWorld.getWorld().getHeight() - 50) {    //bullet out of map
            SpaceWorld.getWorld().removeObject(this);
        }else if(shieldhit != null) {       //Shield hit
            shieldhit.hit();
            SpaceWorld.getWorld().removeObject(this);
        }
    }
    
    //getter
    public int getType() {
        return type;
    }
}
