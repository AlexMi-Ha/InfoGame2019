import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Wall object of the pong game

public class PongWall extends Actor
{
    public PongWall() {
        GreenfootImage img = new GreenfootImage(800, 10);
        img.setColor(Color.WHITE);
        img.fill();
        setImage(img);
    }
}
