import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Pong World

public class PongWorld extends World
{
    private static PongWorld world;     //world singleton
    private PongBall ball;              //ball var
    private PongPaddle p1;              //paddle 1 var (PLAYER)
    private PongPaddle p2;              //Paddle 2 var (AI)
    private Scoreboard display;     //score display
    
    private int score1 = 0;         //player score
    private int score2 = 0;         //ai score
    
    private int achievementTimer;   //timer for the time achievement
    
    public PongWorld()
    {    
        super(800, 600, 1);
        world = this;
        
        GreenfootImage img = new GreenfootImage(800, 600);
        img.setColor(Color.BLACK);
        img.fill();
        img.setColor(Color.WHITE);
        img.drawLine(400, 10, 400, 590);
        setBackground(img);
        
        setPaintOrder(Scoreboard.class, PongBall.class, PongPaddle.class);
        
        
        p1 = new PongPaddle(false);     //NOAI
        p2 = new PongPaddle(true);      //AI
        ball = new PongBall(6, 0);
        display = new Scoreboard(true);
    
        addObject(display, getWidth() / 2 + 10, 50);
        addObject(new PongWall(), getWidth() / 2, 10);
        addObject(new PongWall(), getWidth() / 2, getHeight() - 10);
        addObject(ball, getWidth() / 2, getHeight() / 2);
        addObject(p1, 20, getHeight() / 2);
        addObject(p2, getWidth() - 20, getHeight() / 2);

    }
    //manage player movement
    public void act() {
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))
            p1.moveUp();
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))
            p1.moveDown();
            
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        
        //pause
        if(Greenfoot.isKeyDown("escape")) 
            Greenfoot.setWorld(new LoadMenu(this, 1, Greenfoot.isKeyDown("escape")));
    }
    //reset the game
    public void resetGame() {
        ball.setLocation(getWidth() / 2, getHeight() / 2);
        ball.setDY(0);
        ball.setDX(6);
        p1.setLocation(p1.getX(), getHeight() / 2);
        p2.setLocation(p2.getX(), getHeight() / 2);
    }
    //add a point to one of the paddles
    public void addPoint(PongPaddle target) {
        if(target == p1) {
            display.addPongPoints(0);
            score1++;
        }
        if(target == p2) {
            display.addPongPoints(1);
            score2++;
        }
        
        if(score1 >= 8 || score2 >= 8)
            win();
    }
    
    public void win() {
        GreenfootImage img = getBackground();
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(40));
        if(score1 >= 8) {
            img.drawString("Gewonnen!", getWidth() / 2 -100, getHeight() / 2 - 20);
            if(score2 == 0) {
                AchievementSystem.addAchievement("pong_trophy");
            }
        }else if(score2 >= 8) 
            img.drawString("Verloren!", getWidth() / 2 - 70, getHeight() / 2 - 20);
         
        setBackground(img);
        Greenfoot.delay(100);
        gameOver();
    }
    
    public void gameOver() {
        Greenfoot.setWorld(new LoadMenu(new PongWorld(), 1, Greenfoot.isKeyDown("escape")));
    }
    
    //getter
    
    public static PongWorld getWorld() {
        return world;
    }
    
    public PongBall getBall() {
        return ball;
    }
    
    public PongPaddle getP1() {
        return p1;
    }
    
    public PongPaddle getP2() {
        return p2;
    }
}
