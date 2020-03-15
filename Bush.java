import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//a bush object in the main lobby

public class Bush extends Actor
{
    public Bush(int type) {
        GreenfootImage img;
        switch(type) {
            case 0:
                img = new GreenfootImage("textures/environment/plant1.png");
                break;
            case 1:
                img = new GreenfootImage("textures/environment/plant2.png");
                img.mirrorHorizontally();
                break;
            case 2:
                img = new GreenfootImage("textures/environment/plant3.png");
                break;
            case 3:
                img = new GreenfootImage("textures/environment/plant4.png");
                break;
            default:
                img = new GreenfootImage(30, 30);
                img.setColor(Color.GREEN);
                img.fillOval(0, 0, 30, 30);
        }
        setImage(img);
    }    
}
