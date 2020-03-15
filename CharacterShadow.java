import greenfoot.*;

public class CharacterShadow extends Actor
{
    public CharacterShadow() {
        GreenfootImage img = new GreenfootImage(41,21);
        img.setColor(new Color(0,0,0,50));
        img.fillOval(0, 0, 41, 21);
        setImage(img);
    }
}
