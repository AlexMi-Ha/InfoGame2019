import greenfoot.*;

public class FlappySound extends Actor
{
    public FlappySound() {
        try {
            GreenfootSound sound_fly = new GreenfootSound("sound_effects/flappy/flappy_fly.wav");
            sound_fly.setVolume(95);
            sound_fly.play();
        }catch(Exception e) {}
    }
    
    public void act() {
        FlappyBirdWorld.getWorld().removeObject(this);
    }
}
