import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;

//world of the Asteroid Game

public class AsteroidWorld extends World
{
    private int AsteroidDelayTimer = 300;       //timer for Asteroid spawing
    private final int ASTEROID_DELAY = 400;     //Asteroid spawn delay
    private final int START_LIVES = 3;          //starting lives for the rocket
    private static AsteroidWorld world;         //world singleton
    private AsteroidRocket rocket;              //character var
    private Scoreboard livesDisplay;       //lives Display
    private Scoreboard scoreDisplay;       //score Display

    private int score = 0;                 //score counter
    private int score2 = 0;                //counter for live adding (every 50000 points)
    //private int achievementTimer = 0;      //timer for the time achievement

    public AsteroidWorld()
    {
        super(400, 300, 2, false); 
        world = this;

        setPaintOrder(Scoreboard.class, Asteroid.class, AsteroidRocket.class);

        GreenfootImage img = new GreenfootImage(20,20);
        img.setColor(Color.BLACK);
        img.fill();
        setBackground(img);

        rocket = new AsteroidRocket(START_LIVES);
        addObject(rocket, getWidth() / 2, getHeight() / 2);

        livesDisplay = new Scoreboard("Lives: ", START_LIVES);
        scoreDisplay = new Scoreboard("Score: ", 0);
        addObject(scoreDisplay, 174, getHeight() - 10);
        addObject(livesDisplay, getWidth() + 115, getHeight() - 10);

    }
    //manage Asteroid Spawning
    public void act() {

        AsteroidDelayTimer++;
        if(AsteroidDelayTimer == ASTEROID_DELAY) {
            Point p = getCoord();

            addObject(new Asteroid(new Vector(ranRotation(), .4)), (int)p.getX(), (int)p.getY());
            AsteroidDelayTimer = 0;
        }

        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        //pause
        if(Greenfoot.isKeyDown("escape"))
            Greenfoot.setWorld(new LoadMenu(this, 6, Greenfoot.isKeyDown("escape")));
    }
    //get a random Spawning point for the Asteroid
    public Point getCoord() {
        int r = Greenfoot.getRandomNumber(4);
        switch(r) {
            case 0: 
                return new Point(5, Greenfoot.getRandomNumber(getHeight() - 10) + 5);
            case 1:
                return new Point(getWidth() - 5, Greenfoot.getRandomNumber(getHeight() - 10) + 5);
            case 2:
                return new Point(Greenfoot.getRandomNumber(getWidth() - 10) + 5, 5);
            default:
                return new Point(Greenfoot.getRandomNumber(getWidth() - 10) + 5, getHeight() - 5);
        }
    }
    //get a random Rotation for the Asteroid
    public int ranRotation() {
        int r = Greenfoot.getRandomNumber(4);
        switch(r) {
            case 0:
                return Greenfoot.getRandomNumber(70) + 10;
            case 1:
                return Greenfoot.getRandomNumber(70) + 10 + 90;
            case 2:
                return Greenfoot.getRandomNumber(70) + 10 + 90 + 90;
            default:
                return Greenfoot.getRandomNumber(70) + 10 + 90 + 90 + 90;
        }
    }

    public void addPoints(int pointsToAdd) {
        scoreDisplay.addPoints(pointsToAdd);
        score += pointsToAdd;
        if(score >= 1000000) {
            AchievementSystem.addAchievement("asteroids_trophy");
        }
        score2 += pointsToAdd;
        if(score2 >= 50000) {
            addLive();
            score2 = 0;
        }
    }

    public void addLive() {
        livesDisplay.addPoints(1);
        rocket.addLive();
    }

    public void gameOver() {
        Greenfoot.setWorld(new LoadMenu(new AsteroidWorld(), 6, Greenfoot.isKeyDown("escape")));
    }

    //getter


    public Scoreboard getLivesDisplay() {
        return livesDisplay;
    }

    public Scoreboard getScoreDisplay() {
        return scoreDisplay;
    }

    public static AsteroidWorld getWorld() {
        return world;
    }
}
