import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.File;
//the main casino lobby

public class CasinoWorld extends World
{
    private Character character;        //character var
    
    private static CasinoWorld world;   //world singleton
    private static boolean splash = true;
    //private int achievementTimer = 0;   //timer for the time achievement
    private int width, height;

    private boolean escapeKeyDown = false;
    private boolean escapeLock = false;
    
    //The default Constructor for the Main World
    public CasinoWorld(boolean resetAudio, boolean escapeKeyDown)
    {    
        super(1000, 527, 1);
        world = this;
        Greenfoot.setSpeed(50);
        
        this.escapeKeyDown = escapeKeyDown;
        
        width = getWidth();
        height = getHeight();
        
        GreenfootImage img = new GreenfootImage("images/textures/environment/casino.png");
        img.scale(width, height);
        setBackground(img);
        
        createWalls();
        createInteractables();

        setPaintOrder(CreditMenu.class, BillardLamp.class, Character.class, Machine.class, Table.class, Bush.class, CreditBoard.class);
        
        character = new Character(165, 177);
        addObject(character, 165, 177);
        
        if(resetAudio) {
            resetSounds();
            if (!AudioManager.isPlaying("soundtrack/knddl_casino.mp3"))
                AudioManager.addPlaying("soundtrack/knddl_casino.mp3");
        }
        
        AudioManager.setVolume("soundtrack/knddl_casino.mp3", 60);
        //AchievementSystem.timeDifference(AchievementSystem.evalGameTime());
        
    }
    

    //Constructor for just the first Start of the Game
    //Tries to start into the Splash Screen
    public CasinoWorld()
    {    
        super(1000, 527, 1);
        world = this;
        Greenfoot.setSpeed(50);
        GreenfootImage img = new GreenfootImage("images/textures/environment/casino.png");
        img.scale(1000, 527);
        setBackground(img);

        createWalls();
        createInteractables();
        
        setPaintOrder(CreditMenu.class, BillardLamp.class, Character.class, Machine.class, Table.class, Bush.class, CreditBoard.class);
        
        character = new Character(165, 177);
        addObject(character, 165, 177);
        
        if(splash) {
            Greenfoot.setWorld(new SplashScreen());
            return;
        }
          
        resetSounds();
        
        if (!AudioManager.isPlaying("soundtrack/knddl_casino.mp3")) 
            AudioManager.addPlaying("soundtrack/knddl_casino.mp3");
        
        AudioManager.setVolume("soundtrack/knddl_casino.mp3", 60);
        
        Greenfoot.start();
        /*
        File achievementData = new File("achievement.data"); 
        if(!achievementData.exists()) 
            AchievementSystem.resetAchievements();
           
        AchievementSystem.startTimeCounter();
        */
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("escape")) {
            if(!escapeKeyDown) {
                Greenfoot.setWorld(new MainMenu(this, false, Greenfoot.isKeyDown("enter")));
                escapeKeyDown = true;
            }
        }else
            escapeKeyDown = false;
            
        if(escapeLock)
            escapeKeyDown = true;
    }
    
    //Stops all playing Song in the AudioManager
    public static void resetSounds() {
        AudioManager.stopPlaying("soundtrack/knddl_casino.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_flappybird.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_tetris.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_frogger.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_asteroids.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_snake.mp3");
        AudioManager.stopPlaying("soundtrack/knddl_spaceinvader.mp3");
    }

    //Create all Wall colliders and add them to the World
    public void createWalls() {
        addObject(new Wall(600,5, 0), 340, 100);
        addObject(new Wall(5, 360,0), 3, 311);
        addObject(new Wall(600, 5,0), 340, 522);
        addObject(new Wall(55, 5, 45), 24, 505);
        addObject(new Wall(55, 5, 45), 643, 118);
        addObject(new Wall(55, 5, 45), 736, 370);
        addObject(new Wall(55, 5, 135), 23, 114);
        addObject(new Wall(55, 5, 135), 648, 502);
        addObject(new Wall(55, 5, 45), 976, 119);
        addObject(new Wall(55, 5, 135), 977, 370);
        addObject(new Wall(55, 5, 135), 739, 117);
        addObject(new Wall(5, 190,0), 663, 401);
        addObject(new Wall(5, 55,0), 663, 160);
        addObject(new Wall(5, 55,0), 720, 160);
        addObject(new Wall(5, 55,0), 719, 333);
        addObject(new Wall(220, 5, 0), 860, 389);
        addObject(new Wall(220, 5 ,0), 860, 100);
        addObject(new Wall(5, 225,0), 996, 244);
        addObject(new Wall(60, 5 ,0), 692, 185);
        addObject(new Wall(60, 5 ,0), 692, 308);
    }
    
    //Create all Interactable Colliders and add them to the World
    public void createInteractables() {
        //Shop  -  NOT ADDED YET
        //addObject(new CoinShop(), 126, 102);
        //Game Machines
        addObject(new Machine(1), 24, 256);
        addObject(new Machine(2), 24, 334);
        addObject(new Machine(3), 353, 91);
        addObject(new Machine(4), 409, 91);
        addObject(new Machine(5), 465, 91);
        addObject(new Machine(6), 577, 91);
        addObject(new Machine(7), 391, 304);
        addObject(new Machine(8), 439, 304);
        addObject(new Machine(9), 487, 304);
        addObject(new Machine(10), 534, 304);
        addObject(new Machine(11), 145, 91);
        addObject(new Machine(12), 210, 91);
        //Tables
        addObject(new Table(0), 216, 408);
        addObject(new BillardLamp(), 216, 380);
        addObject(new Table(1), 860, 280);
        //Bush
        addObject(new Bush(1), 624, 118);
        addObject(new Bush(3), 40, 122);
        addObject(new Bush(0), 751, 122);
        addObject(new Bush(2), 953, 105);
        //Boards
        //addObject(new TrophyBoard(), 185, 42); NOT ADDED YET
        addObject(new CreditBoard(), 857, 54);
    }

    //getter / setter
    
    public static CasinoWorld getWorld() {
        return world;
    }
    
    public static void setSplash(boolean b) {
        splash = b;
    }

    public Character getCharacter() {
        return character;
    }
    
    public void setEscapeLock(boolean escapeLock) {
        this.escapeLock = escapeLock;
    }
}
