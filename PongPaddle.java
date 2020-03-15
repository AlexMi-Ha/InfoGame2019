import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

//paddle in the pong game

public class PongPaddle extends Actor
{
    private int speed;              //moving speed
    private boolean isAI;           //if it is controlled by the computer

    public PongPaddle(boolean isAI) {
        GreenfootImage img = new GreenfootImage(10, 100);
        img.setColor(Color.WHITE);
        img.fill();
        setImage(img);
        if(isAI)
            speed = 5;
        else
            speed = 5;
        this.isAI = isAI;
    }

    public void act() {
        if(isAI)
            manageAI();
    }
    //manage the AI movements
    public void manageAI() {
        if(PongWorld.getWorld().getBall().getX() >= PongWorld.getWorld().getWidth() / 2 - 100) {
            int yToTrack = PongWorld.getWorld().getBall().getY();
            if(PongWorld.getWorld().getBall().getY() < getY() + 5 && PongWorld.getWorld().getBall().getY() > getY() - 5){
                setLocation(getX(), PongWorld.getWorld().getBall().getY());
            }
            else{
                if(getY() < yToTrack)
                    moveDown();
                if(getY() > yToTrack)
                    moveUp();
            }
        } else if(getX() < 500){
            int yToTrack = PongWorld.getWorld().getBall().getY();
            if(PongWorld.getWorld().getBall().getY() < getY() + 5 && PongWorld.getWorld().getBall().getY() > getY() - 5){
            
            }
            else{
                if(getY() < yToTrack)
                    moveDown();
                if(getY() > yToTrack)
                    moveUp();
            }
        }
    }
    //move the paddle up
    public void moveUp() {
        if(getY() > 69)
            setLocation(getX(), getY() - speed);
    }
    //move the paddle down
    public void moveDown() {
        if(getY() < PongWorld.getWorld().getHeight() - 69)
            setLocation(getX(), getY() + speed);
    }

}
