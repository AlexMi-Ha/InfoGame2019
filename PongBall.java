import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//ball of the Pong game

public class PongBall extends Actor
{
    private int dx;     //speed in the x direction
    private int dy;     //speed in the y direction
    
    private GreenfootSound sound_pong;
    
    public PongBall(int dx, int dy) {
        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(Color.WHITE);
        img.fillOval(0, 0, 20, 20);
        setImage(img);
        try {
            sound_pong = new GreenfootSound("sound_effects/pong_bup.mp3");
            sound_pong.setVolume(90);
        }catch(Exception e) {}
        
        this.dx = dx;
        this.dy = dy;
    }  
    //manage ball reflection
    public void act() {
        if(getOneIntersectingObject(PongPaddle.class) != null) { 
            dx = -dx;
            dy = (getY() - getOneIntersectingObject(PongPaddle.class).getY())/2;
            try {
                sound_pong.play();
            }catch(Exception e) {}
        }
        if(getOneIntersectingObject(PongWall.class) != null) {
            dy = -dy;
            try {
                sound_pong.play();
            }catch(Exception e) {}
        }
        
        move();
        if(dy > 7)
            dy = 7;
        if(dy < -7)
            dy = -7;
        
        if(getX() <= 2) {
            PongWorld.getWorld().resetGame();
            PongWorld.getWorld().addPoint(PongWorld.getWorld().getP2());
        }
        if(getX() >= PongWorld.getWorld().getWidth() - 2) {
            PongWorld.getWorld().resetGame();
            PongWorld.getWorld().addPoint(PongWorld.getWorld().getP1());
        }
        
        /*if(getY() < 20)
            setLocation(getX(), 23);
        if(getY() > 580)
            setLocation(getX(), 570);*/
    }
    //moveing
    public void move() {
        setLocation(getX() + dx, getY() + dy);
    }
    
    
    //getter  /  setter
    
    
    public Actor getIntersectingPaddle() {
        return getOneIntersectingObject(PongPaddle.class);
    }
    
    public int getDX() {
        return dx;
    }
    
    public int getDY() {
        return dy;
    }
    
    public void setDX(int dx) {
        this.dx = dx;
    }
    
    public void setDY(int dy) {
        this.dy = dy;
    } 
}
