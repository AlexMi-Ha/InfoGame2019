import greenfoot.*;
import java.util.List; 

public class NannoidWorld extends World
{
    
    private NannoidPlate np;
    private NannoidBall ball;
    
    private static NannoidWorld world;
    
    private boolean over = false;
    private int timer = 0;
    
    private Scoreboard display_score;
    
    public NannoidWorld()
    {    
        super(800, 600, 1, false);
        world = this;
        
        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(new Color(30, 30, 30));
        img.fill();
        setBackground(img);
        
        np = new NannoidPlate();
        ball = new NannoidBall();
        addObject(np, getWidth() / 2, getHeight() - 80);
        addObject(ball, getWidth() / 2, 400);
        
        addWalls();
        addBlocks();
        
        display_score = new Scoreboard("", 0, 200);
        addObject(display_score, 100, 580);
    }
    
    public void act() {
        if(allShot()) 
            gameover();
            
        //Pause the game
        if(Greenfoot.isKeyDown("escape")) 
            Greenfoot.setWorld(new LoadMenu(this, 12, Greenfoot.isKeyDown("escape")));
    }
    
    public boolean allShot() {
        for(NannoidBlock b : getObjects(NannoidBlock.class)) 
            return false;
        return true;
    }
    
    public void gameover() {
        over = true;
        timer++;
        ball.setVel(0, 0);
        
        if(timer >= 50) 
            Greenfoot.setWorld(new LoadMenu(new NannoidWorld(), 12, Greenfoot.isKeyDown("escape")));
    }
    
    public void addBlocks() {
        for(int i = 40; i < getWidth() - 39; i+=45) {
            addObject(new NannoidBlock(4), i, 60);
            addObject(new NannoidBlock(3), i, 90);
            addObject(new NannoidBlock(2), i, 120);
            addObject(new NannoidBlock(1), i, 150);
            addObject(new NannoidBlock(1), i, 180);
        }
    }
    
    public void addWalls() {
        addObject(new Wall(getWidth(), 10, 0), getWidth() / 2, 0);
        addObject(new Wall(10, getHeight(), 0), 0, getHeight() / 2);
        addObject(new Wall(10, getHeight(), 0), getWidth(), getHeight() / 2);
    }
    
    public static NannoidWorld getWorld() {
        return world;
    }
    
    public boolean getOver() {
        return over;
    }
    
    public Scoreboard getScoreDisplay() {
        return display_score;
    }
}
