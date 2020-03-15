import greenfoot.*; 

public class FroggerWorld extends World
{
    private static FroggerWorld world;  //singleton
    private Frog frog;      //currently active frog
    
    private int frogsInVictory = 0;
    
    private int[] carSpawnTimer = {0, 0, 0, 0};     //timer for each col of the street
    private int[] nextSpawnTime = {10, 30, 10, 80}; //When the timer hits this value a car will spawn
    
    private int[] treeSpawnTimer = {0, 0, 0, 0};    //timer for each col of the river
    private int[] nextTreeSpawnTime = {0, 30, 80, 80}; //When the timer hits this value a tree will spawn
    
    private boolean dead = false;   //currently dead
    private int dieTimer = 0;       //timer to respawn
    
    private Scoreboard displayScore;
    private Scoreboard displayLives;
    
    private GreenfootSound sound_death, sound_full;
   
    public FroggerWorld()
    {    
        super(600, 600, 1, false); 
        world = this;
        
        displayScore = new Scoreboard("", 0, 200);
        addObject(displayScore, 100, 40);
        
        displayLives = new Scoreboard("Leben: ", 3, 125);
        addObject(displayLives, 530, 40);
        
        setBackground(new GreenfootImage("textures/games/frogger/frogger_background.png"));
        setPaintOrder(FroggerCar.class, Frog.class, FroggerTree.class);
        
        frog = new Frog();
        addObject(frog, 300, 575);
        
        addObject(new FroggerEnd(), 299, 75);
        addObject(new FroggerEnd(), 449, 75);
        addObject(new FroggerEnd(), 149, 75);
        try {
            sound_death = new GreenfootSound("sound_effects/frogger_death.wav");
            sound_death.setVolume(100);
            sound_full = new GreenfootSound("sound_effects/frogger_pointfull.mp3");
            sound_full.setVolume(90);
        }catch(Exception e) {}
    }
    //manage spawning timers
    public void act() {
        for(int i = 0; i < 4; i++) {//add to each timer and spawn if its time
            if(carSpawnTimer[i] >= nextSpawnTime[i])
                spawnCar(i);
            carSpawnTimer[i]++;
            
            if(treeSpawnTimer[i] >= nextTreeSpawnTime[i])
                spawnTree(i);
            treeSpawnTimer[i]++;
        }
        //Die loop if dead
        if(dead) 
            die();
        
        //Pause the game
        if(Greenfoot.isKeyDown("escape"))
            Greenfoot.setWorld(new LoadMenu(this, 11, Greenfoot.isKeyDown("escape")));
    }
    
    public void newLevel() {
        frogsInVictory = 0;
        for(FroggerEnd f : getObjects(FroggerEnd.class))
            f.setFull(false);
        frog.setLocation(300, 575);
        sound_full.play();
    }
    
    //Die Loop with Respawn timer
    public void die() {
        dead = true;
        if(dieTimer == 0) {
            displayLives.removePoints(1);
            try {
                sound_death.play();
            }catch(Exception e) {}
        }
        dieTimer++;
        if(dieTimer == 50) {
            dieTimer = 0;
            dead = false;
            frog.setLocation(300, 575);
            if(displayLives.getScore() == 0)
                Greenfoot.setWorld(new LoadMenu(new FroggerWorld(), 11, Greenfoot.isKeyDown("escape")));
        }
    }
    //spawn a tree in a specific Col
    public void spawnTree(int col) {
        int nT = Greenfoot.getRandomNumber(100) + 280;  //Random nextSpawnTime
        treeSpawnTimer[col] = 0;                        //set the timer to 0
        nextTreeSpawnTime[col] = nT;                    
        int d;
        int x, y;
        //get Direction, x and y
        switch(col) {
            case 0:
                y = 125;
                x = getWidth();
                d = -2;
                break;
            case 1:
                y = 175;
                x = 0;
                d = 2;
                break;
            case 2:
                y = 225;
                x = getWidth();
                d = -2;
                break;
            default:
                y = 275;
                x = 0;
                d = 2;
                break;
        }
        //summon the Tree
        addObject(new FroggerTree(d), x, y);
    }
    //spawn a Car in a specific Col
    public void spawnCar(int col) {
        int nT = Greenfoot.getRandomNumber(200) + 150;  //Random nextSpawnTime
        carSpawnTimer[col] = 0;                 //set the timer to 0
        nextSpawnTime[col] = nT;
        int d;
        int x, y;
        //get Direction, x and y
        switch(col) {
            case 0:
                y = 525;
                x = getWidth();
                d = -2;
                break;
            case 1:
                y = 475;
                x = 0;
                d = 2;
                break;
            case 2:
                y = 425;
                x = getWidth();
                d = -2;
                break;
            default:
                y = 375;
                x = 0;
                d = 2;
                break;
        }
        //summon the Tree
        addObject(new FroggerCar(d), x, y);
    }
    
    //getter / setter
    
    public void setFrogsInVictory(int f) {
        frogsInVictory = f;
    }
    
    public int getFrogsInVictory() {
        return frogsInVictory;
    }
    
    public boolean getDead() {
        return dead;
    }
    
    public Scoreboard getScoreDisplay() {
        return displayScore;
    }
    
    public static FroggerWorld getWorld() {
        return world;
    }
}
