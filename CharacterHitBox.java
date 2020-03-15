import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Hit box for the Main lobby Character

public class CharacterHitBox extends Actor
{
    public CharacterHitBox() {
        GreenfootImage img = new GreenfootImage(27, 27);
        setImage(img);
    }
   
    public boolean checkForNoSpace() {
        return getOneIntersectingObject(Wall.class) != null 
        || getOneIntersectingObject(Bush.class) != null 
        || getOneIntersectingObject(Machine.class) != null 
        || getOneIntersectingObject(Table.class) != null;
    }
}
