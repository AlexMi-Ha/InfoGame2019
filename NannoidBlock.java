import greenfoot.*;

public class NannoidBlock extends Actor
{
    private boolean destroyed;
    private int type;
    
    private GreenfootSound sound_break;
    
    public NannoidBlock(int type) {
        setImage(new GreenfootImage("textures/games/nannoid/block" + type + ".png"));
        
        destroyed = false;
        this.type = type;
        
        try {
            sound_break = new GreenfootSound("sound_effects/pong_bup.mp3");
            sound_break.setVolume(50);
        }catch(Exception e) {}
    }

    public void act() {
        if(destroyed) {
            GreenfootImage img = getImage();
            int t = img.getTransparency() - 7;
            if(t < 0) {
                NannoidWorld.getWorld().removeObject(this);
                return;
            }
            img.setTransparency(t);
            setImage(img);
        }
    }
    
    public void playSound() {
        try {
            sound_break.play();
        }catch(Exception e) {}
    }
    
    public boolean getDestroyed() {
        return destroyed;
    }
    
    public int getType() {
        return type;
    }
    
    public void setDestroyed(boolean arg) {
        destroyed = arg;
    }
}
