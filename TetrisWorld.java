import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//the tetris World

public class TetrisWorld extends World
{
    private static TetrisWorld world;       //world singleton
    private Forms currentForm;              //current falling form
    private int numOfForms;                 //number of all Forms 
    private Scoreboard score;               //score
    
    private int achievementTimer = 0;       //timer for the time achievement

    public TetrisWorld()
    {    
        super(10, 20, 40); 
        world = this;
        Greenfoot.setSpeed(30);
        setPaintOrder(Scoreboard.class, BottomWall.class);
        
        /*GreenfootImage img = new GreenfootImage(40, 40);
        img.drawRect(0, 0, 40, 40);
        setBackground(img);*/
        setBackground(new GreenfootImage("textures/games/tetris/tetris_background.png"));
        
        score = new Scoreboard("Score: ", 0, 10 * 40, Color.WHITE);
        addObject(score, 5, getHeight() - 1);
        for(int i = 0; i < getWidth(); i++)
            addObject(new BottomWall(), i, getHeight());
            
        numOfForms = 0;
        currentForm = generateForm();
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
            Greenfoot.setWorld(new LoadMenu(this, 2, Greenfoot.isKeyDown("escape")));
    }
    //Add a different amount of Points for clearing more Rows at the same time
    //Executed on Row clear
    public void points(int rows) {
        int points = 0;
        switch(rows) {
            case 1:
                points = 40;
                break;
            case 2:
                points = 100;
                break;
            case 3:
                points = 300;
                AchievementSystem.addAchievement("tetris_trophy");
                break;
            default:
                points = 1200;
                AchievementSystem.addAchievement("tetris_trophy");
                break;
        }
        score.addPoints(points);
    }

    //GameOver
    //executed when a Form detects another Form in its Spawn location
    public void end() {
        Greenfoot.setWorld(new LoadMenu(new TetrisWorld(), 2, Greenfoot.isKeyDown("escape")));
    }
   
    //Generates a random Form
    //Executed at the Start & when a Form dies
    public Forms generateForm() {
        numOfForms +=1;
        
        int rand = (int) (Math.random() * 7);
        switch (rand) {
            case 0:
                return new IForm();
            case 1:
                return new JForm();
            case 2:
                return new LForm();
            case 3:
                return new OForm();
            case 4:
                return new TForm();
            case 5:
                return new SForm();
            default:
                return new ZForm();
        }
    }
    
    //getter & setter
    public Forms getCurrent() {
        return currentForm;
    }

    public void setCurrent(Forms f) {
        currentForm = f;
    }

    public static TetrisWorld getWorld() {
        return world;
    }
}
