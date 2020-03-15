import greenfoot.*;

public class NannoidBall extends Actor
{

    private int dx;
    private int dy;
    private int speed = 5;
    
    private GreenfootSound sound_paddle;
    
    public NannoidBall() {
        GreenfootImage img = new GreenfootImage(16,16);
        img.setColor(new Color(200, 200, 200));
        img.fillOval(0, 0, 16, 16);
        setImage(img);
        
        dx = 0;
        dy = speed;
        
        try {
            sound_paddle = new GreenfootSound("sound_effects/pong_bup.mp3");
            sound_paddle.setVolume(80);
        }catch(Exception e) {
        }
    }
    
    public void act() 
    {
        if(getOneIntersectingObject(NannoidPlate.class) != null) { 
            dy = -dy;
            dx = dx + ((getX() - getOneIntersectingObject(NannoidPlate.class).getX()) / 10);
            if(dx > speed)
                dx = speed;
            else if(dx < -speed)
                dx = -speed;
            
            try {
                sound_paddle.play();
            }catch(Exception e) {}
        }
        
        Wall w = (Wall)getOneIntersectingObject(Wall.class);
        if(w != null && w.getAngle(0, 0, 0) == 90) {
            dy = -dy;
            setLocation(getX(), getY() + dy);
            w.playSound();
        }
        if(w != null && w.getAngle(0, 0, 0) == 0) {
            dx = -dx;
            setLocation(getX() + dx, getY());
            w.playSound();
        }
        
        NannoidBlock b = (NannoidBlock)getOneIntersectingObject(NannoidBlock.class);
        if(b != null && !b.getDestroyed()) {
            if((getX() + 8 < b.getX() - 10 && getX() + 8 > b.getX() - 19) 
            || (getX() - 8 > b.getX() + 10 && getX() - 8 < b.getX() + 19)) {
                dx = -dx;
                b.setDestroyed(true);
                b.playSound();
                NannoidWorld.getWorld().getScoreDisplay().addPoints(b.getType());
            }
            if((getY() - 8 < b.getY() - 1 && getY() - 8 > b.getY() - 16) 
            || (getY() + 8 > b.getY() + 1 && getY() + 8 < b.getY() + 16)) {
                dy = -dy;
                b.setDestroyed(true);
                b.playSound();
                NannoidWorld.getWorld().getScoreDisplay().addPoints(b.getType());
            }
        }
        
        if(getY() >= NannoidWorld.getWorld().getHeight()) 
            NannoidWorld.getWorld().gameover();
        
        setLocation(getX() + dx, getY() + dy);
    }    
    
    public void setVel(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
