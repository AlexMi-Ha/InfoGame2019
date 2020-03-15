import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//main snake head in the snake game

public class SnakeHead extends Actor
{
    private int speed = 10;         //Ticks needed to move 1 cell
    private int moveCount = 0;      //Counter to move 1 cell everytime when moveCount == speed
    private int dotsEaten = 0;      //how long the snake is
    
    private boolean buttonPressed = false;
    
    private GreenfootSound sound_eat;

    public SnakeHead(int rot) {
        setImage(new GreenfootImage("textures/games/snake/snake_head.png"));
        setRotation(rot);
        
        try {
            sound_eat = new GreenfootSound("sound_effects/snake_eat.wav");
            sound_eat.setVolume(92);
        }catch(Exception e) {}
    }
    
    //manage eating and death
    public void act() 
    {
        move();
        if(getOneIntersectingObject(Dot.class) != null) {
            SnakeWorld.getWorld().removeObject(getOneIntersectingObject(Dot.class));
            dotsEaten++;
            SnakeWorld.getWorld().addDot();
            SnakeWorld.getWorld().addScore(1);
            
            try {
                sound_eat.play();
            }catch(Exception  e) {}
        }
        if(getOneIntersectingObject(SnakeTail.class) != null) {
            SnakeWorld.getWorld().gameOver();
        }
    }

    //manage moveing
    public void move() {
        moveCount++;
        
        boolean multibutton = true;
        
        if(getRotation() != 90 && (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) && (!buttonPressed || multibutton)) { 
            setRotation(270);
            buttonPressed = true;
        }
        if(getRotation() != 0 && (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && (!buttonPressed || multibutton)) {
            setRotation(180);
            buttonPressed = true;
        }
        if(getRotation() != 270 && (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) && (!buttonPressed || multibutton)) { 
            setRotation(90);
            buttonPressed = true;
        }
        if(getRotation() != 180 && (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && (!buttonPressed || multibutton)) { 
            setRotation(0);
            buttonPressed = true;
        }

        if(moveCount == speed){
            if(dotsEaten >= 1) {
                SnakeWorld.getWorld().addObject(new SnakeTail(dotsEaten * speed, getRotation()),
                                                getX(), getY());
            }
            move(1);
            moveCount = 0; 
            buttonPressed = false;
        }
    }
    
    //getter
    
    public int getScore() {
        return dotsEaten;
    }
}
