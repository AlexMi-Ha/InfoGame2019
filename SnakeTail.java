import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//tail of the snake in snake

public class SnakeTail extends Actor
{
    private int age = 0;        //age of the tail object
    private int lifeTime;       //lifetime of the tail
    
    public SnakeTail(int lifeTime, int rotation) {
        setImage(new GreenfootImage("textures/games/snake/snake_body.png"));
        this.lifeTime = lifeTime;
        setRotation(rotation);
    }
    //removing this Tail instance when age == lifetime
    public void act() {
        if(age == lifeTime) 
            SnakeWorld.getWorld().removeObject(this);
            
        if(lifeTime - age == 10)
            setImage(new GreenfootImage("textures/games/snake/snake_tail.png"));
        age++;
    }    
}
