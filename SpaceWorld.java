import greenfoot.*;

//Space World

public class SpaceWorld extends World
{
    private static SpaceWorld world;                     //world singleton var
    private SpaceEnemy[] enemies = new SpaceEnemy[50];   //Enemies in the World
    private SpaceEnemy[] lowestEnemies;                  //Enemies in the Bottom Row
    private SpaceShield[] shields = new SpaceShield[4];  //Shields in the World
    private SpaceCharacter character;                    //character var
    private Scoreboard healthDisplay, scoreDisplay;    //displays
    private String MOVE_STATE;                           //move state of the enemies
    private int enemyMoveTimer;                          //timer to update enemy position
    private int enemyShootTimer;                         //timer for shooting
    private int numberOfLowestEnemies;                   //number of enemies in the bottom row
    private final int ENEMY_MOVE_INTERVAL = 45;          //time needed to update the position
    private final int ENEMY_SHOOT_INTERVAL = 60;         //time needed to shoot
    
    private int achievementTimer = 0;                   //timer for the time achievement
   
    public SpaceWorld()
    {    
        super(400, 300, 2); 
        world = this;
        
        GreenfootImage img = new GreenfootImage(800, 600);
        img.setColor(Color.BLACK);
        img.fill();
        setBackground(img);
        
        character = new SpaceCharacter();
        healthDisplay = new Scoreboard("Leben: ", 3, 300);
        scoreDisplay = new Scoreboard("Score: ", 0, 200);
        addObject(character, 170, 245);
        addObject(healthDisplay, 400, 289);
        addObject(scoreDisplay, 49, 289);
        
        addEnemies();
        addShields();
        updateLowerList();
        
        MOVE_STATE = "RIGHT";
        numberOfLowestEnemies = 0;
        enemyMoveTimer = 0;
        enemyShootTimer = 0;
        
        
    }
    
    public void act() {
        //change the image and update position
        if(enemyMoveTimer == ENEMY_MOVE_INTERVAL) {
            enemyMoveTimer = 0;
            for(int i = 0; i < enemies.length; i++)
                enemies[i].changeImage();
            moveEnemies();
        }
        enemyMoveTimer++;
        //shoot
        if(enemyShootTimer == ENEMY_SHOOT_INTERVAL) {
            enemyShootTimer = 0;
            int r = Greenfoot.getRandomNumber(lowestEnemies.length);
            lowestEnemies[r].shoot();
        }
        enemyShootTimer++;
        
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        
        if(allDead())
            win();
        
        //pause
        if(Greenfoot.isKeyDown("escape"))
            Greenfoot.setWorld(new LoadMenu(this, 5, Greenfoot.isKeyDown("escape")));
    }
    //add the shields at the creation of the world instance
    public void addShields() {
        int j = 0;
        for(int i = 0; i<shields.length; i++) 
            shields[i] = new SpaceShield();
            
        for(int i = 50; i < getWidth(); i+=100) {
            addObject(shields[j], i, 200);
            j++;
        }
    }
    //add the enemies at the creation of the world instance
    public void addEnemies() {
        int j=0;
        for(int i = 0; i<20;i++) 
            enemies[i] = new SpaceEnemy(0, false);
        for(int i = 20; i<40;i++) 
            enemies[i] = new SpaceEnemy(1, false);
        for(int i = 40; i<50;i++) 
            enemies[i] = new SpaceEnemy(2, false);
        //Bottom enemy row
        for(int i = 25; i<251; i+=25) {
            addObject(enemies[j], i, 110);
            
            enemies[j].setLowest(true);
            numberOfLowestEnemies++;
            j++;
        }
        
        for(int i = 25; i<251; i+=25) {
            addObject(enemies[j], i, 90);
            j++;
        }
        for(int i = 25; i<251; i+=25) {
            addObject(enemies[j], i, 70);
            j++;
        }
        for(int i = 25; i<251; i+=25) {
            addObject(enemies[j], i, 50);
            j++;
        }
        for(int i = 25; i<251; i+=25) {
            addObject(enemies[j], i, 30);
            j++;
        }
    }
    
    public boolean allDead() {
        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i].isInGame()) 
                return false;
        }
        return true;
    }
    
    public void win() {
        AchievementSystem.addAchievement("space_trophy");
        
        GreenfootImage img = getBackground();
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(40));
        img.drawString("Gewonnen!", getWidth() / 2 + 100, getHeight() / 2 + 120);
        setBackground(img);
        Greenfoot.delay(100);
        gameOver();
    }
    
    //manage the movement of every enemy in game
    public void moveEnemies() {
        if(canMoveRight() && MOVE_STATE == "RIGHT") {   //Move Right
            for(int i = 0; i < enemies.length; i++) {
                if(enemies[i].isInGame())
                    enemies[i].moveRight();
                }
        }else if(!canMoveRight() && MOVE_STATE == "RIGHT") { //move down
            MOVE_STATE = "LEFT";
            if(canMoveDown()) {
                for(int i = 0; i < enemies.length; i++) {
                    if(enemies[i].isInGame())
                        enemies[i].moveDown();
                }
            }else 
                gameOver();
        }else if(canMoveLeft() && MOVE_STATE == "LEFT") {   //move left
            for(int i = 0; i < enemies.length; i++) {
                if(enemies[i].isInGame())
                    enemies[i].moveLeft();
            }
        }else if(!canMoveLeft() && MOVE_STATE == "LEFT") {  //move down
            MOVE_STATE = "RIGHT";
            if(canMoveDown()) {
                for(int i = 0; i < enemies.length; i++) {
                    if(enemies[i].isInGame())
                    enemies[i].moveDown();
                }
            }else
                gameOver();
        }
    }
    
    //update lowestEnemies[] when a enemy got killed
    public void updateLowerList() {
        numberOfLowestEnemies = 0;
        //count the lowest enemies
        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i].isInGame() && enemies[i].isLowest()) 
                numberOfLowestEnemies ++;
        }
        lowestEnemies = new SpaceEnemy[numberOfLowestEnemies];
        numberOfLowestEnemies = 0;
        //put the lowest enemies in lowestEnemies[]
        for(int i = 0; i<enemies.length; i++) {
            if(enemies[i].isInGame() && enemies[i].isLowest()) {
                lowestEnemies[numberOfLowestEnemies] = enemies[i];
                numberOfLowestEnemies ++;
            }
        }
    }
    
    //End the game
    public void gameOver() {
        Greenfoot.setWorld(new LoadMenu(new SpaceWorld(), 5, Greenfoot.isKeyDown("escape")));
    }
    
    //check for every enemy if it is possible to move right
    public boolean canMoveRight() {
        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i].isInGame()) {
                if(enemies[i].getX() >= getWidth() - 20)
                    return false;
            }
        }
        return true;
    }
    //check for every enemy if it is possible to move left
    public boolean canMoveLeft() {
        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i].isInGame()) {
                if(enemies[i].getX() <= 15)
                    return false;
            }
        }
        return true;
    }
    //check for every enemy if it is possible to move down
    public boolean canMoveDown() {
        for(int i = 0; i < enemies.length; i++) {
            if(enemies[i].isInGame()) {
                if(enemies[i].getY() >= 240)
                    return false;
            }
        }
        return true;
    }
    
    //getter
    public static SpaceWorld getWorld() {
        return world;
    }
    
    public Scoreboard getScoreD() {
        return scoreDisplay;
    }
    
    public Scoreboard getHealthD() {
        return healthDisplay;
    }
}
