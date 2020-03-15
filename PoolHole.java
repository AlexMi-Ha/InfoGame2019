import greenfoot.*;
import java.util.List;

public class PoolHole extends Actor
{
    GreenfootSound sound_hole;
    
    public PoolHole() {
        GreenfootImage img = new GreenfootImage(40, 40);
        setImage(img);
        try {
            sound_hole = new GreenfootSound("sound_effects/pool/pool_hole.mp3");
            sound_hole.setVolume(80);
        }catch(Exception e) {}
    }
    //remove all GameObjects colliding with this
    public void act() {
        List<PoolBall> l = getIntersectingObjects(PoolBall.class);
        for(PoolBall p : l) {
            PoolWorld.getWorld().removeObject(p);
            p.setInWorld(false);
            
            try {
                sound_hole.play();
            }catch(Exception e) {}
            
            if(PoolWorld.getWorld().getBall() != p) 
                PoolWorld.getWorld().getScoreDisplay().addPoints(100);
        }
    }
}
