import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FroggerEnd extends Actor
{
    private boolean full = false;   //frog in this end
    
    public FroggerEnd() {
        GreenfootImage img = new GreenfootImage(50, 50);
        img.setColor(new Color(0, 0, 0, 0));
        img.fill();
        setImage(img);
    }
        
    //getter / setter
    
    public void setFull(boolean f) {
        full = f;
        if(f) {
            setImage(new GreenfootImage("textures/games/frogger/frog.png"));
        }else {
            GreenfootImage img = getImage();
            img.clear();
            img.setColor(new Color(0, 0, 0, 0));
            img.fill();
            setImage(img);
        }
    }
    
    public boolean getFull() {
        return full;
    }
}
