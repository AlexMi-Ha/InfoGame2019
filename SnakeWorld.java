import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

//Snake World

public class SnakeWorld extends World
{
    
    public static SnakeWorld world;         //singleton static
    //Direction finals


    
    SnakeHead head;             //snake engine var
    Scoreboard lblScore;        //score display
    
    private int achievementTimer = 0;       //timer for the time achievement
    
    public SnakeWorld()
    {   
        super(20, 20, 25); 

        world = this; 
        
        setPaintOrder(Scoreboard.class, SnakeTail.class, SnakeHead.class, Dot.class);

        GreenfootImage img = new GreenfootImage(20, 20);
        img.setColor(new Color(54, 54, 54));
        img.fill();
        setBackground(img);

        int x = Greenfoot.getRandomNumber(getWidth()-3);
        int y = Greenfoot.getRandomNumber(getHeight()-3);
        if(x < 3)
            x = 3;
        if(y < 3)
            y = 3;
            
        int rotation = Greenfoot.getRandomNumber(4) * 90;
        
        this.head = new SnakeHead(rotation);
        addObject(head, x, y);
        
        addDot();
        lblScore = new Scoreboard("Score: ", 0);
        addObject(lblScore, 14, getHeight());

    }
    
    public void act() {
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        
        //pause
        if(Greenfoot.isKeyDown("escape")) 
            Greenfoot.setWorld(new LoadMenu(this, 3, Greenfoot.isKeyDown("escape")));
    }
    
    //add dots at random locations
    public void addDot() {
        Dot d = new Dot();
        addObject(d, Greenfoot.getRandomNumber(getWidth()),
            Greenfoot.getRandomNumber(getHeight()));
        List list = getObjectsAt(d.getX(), d.getY(), SnakeTail.class);
        while(list.size() > 0) {
            removeObject(d);
            addObject(d, Greenfoot.getRandomNumber(getWidth()),
            Greenfoot.getRandomNumber(getHeight()));
            list = getObjectsAt(d.getX(), d.getY(), SnakeTail.class);
        }
    }
    //add score
    public void addScore(int scoreToAdd) {
        lblScore.addPoints(scoreToAdd);
        if(getHead().getScore() == 100)
            AchievementSystem.addAchievement("snake_trophy");
    }
    
    public void gameOver() {
        Greenfoot.setWorld(new LoadMenu(new SnakeWorld(), 3, Greenfoot.isKeyDown("escape")));
    }
    
    
    //getter 
    
    public static SnakeWorld getWorld() {
        return world;
    }
   
    public SnakeHead getHead() {
        return head;
    }
    
    
}
