import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//wall colliders of the main lobby

public class Wall extends Actor
{
    int width, height;
    
    private GreenfootSound sound_nan_bup;
    
    public Wall(int width, int height, int rotation) {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(new Color(0,0,0,0));
        img.fill();
        setImage(img);
        setRotation(rotation);
        
        this.width = width;
        this.height = height;
        
        try {
           
            sound_nan_bup = new GreenfootSound("sound_effects/pong_bup.mp3");
            sound_nan_bup.setVolume(40);
            
        }catch(Exception e) {}
    } 
    
    //ONLY FOR NANNOID GAME
    public void playSound() {
        try {
            sound_nan_bup.play();
        }catch(Exception e) {}
    }
    
    //ONLY FOR THE POOL GAME
    public boolean intersectsCircle(double x, double y, int radius) {
        int hW = width / 2;
        int hH = height / 2;
        if(width <= height)
            return y + radius >= getY() - hH && y - radius <= getY() + hH &&
               !(x + radius <= getX() - hW || x - radius >= getX() + hW);
        else
            return x + radius >= getX() - hW && x - radius <= getX() + hW &&
               !(y + radius <= getY() - hH || y - radius >= getY() + hH);
    }
    
    public int getAngle(double x, double y, int radius) {
        if(width >= height)
            return 90;
        else 
            return 0;
    }
}
