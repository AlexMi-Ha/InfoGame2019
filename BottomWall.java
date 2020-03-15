import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//the bottom wall of the tetris game

public class BottomWall extends Actor
{
    public BottomWall() {
        GreenfootImage img = new GreenfootImage(40, 120);
        img.setColor(Color.BLACK);
        img.fill();
        img.drawRect(0, 0, 40, 1);
        img.drawRect(0, 2, 40, 1);
        img.drawRect(0, 4, 40, 1);
        img.drawRect(0, 6, 40, 1);
        setImage(img);
    }
}
