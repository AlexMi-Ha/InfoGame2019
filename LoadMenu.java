import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Load menu between worlds

public class LoadMenu extends World
{
    private World worldToEnter;
    private int gameType;
    private static LoadMenu world;
    
    private int achievementTimer = 0;
    
    private boolean escapeKeyDown = false;
    
    public LoadMenu(World worldToEnter, int gameType, boolean escapeKeyDown)
    {
        super(676, 396, 1); 
        this.worldToEnter = worldToEnter;
        this.gameType = gameType;
        world = this;
        
        this.escapeKeyDown = escapeKeyDown;
        
        //1: pong , 2: Tetris , 3: Snake , 4: FlappyBird , 5: SpaceInvader ,
        //6: Asteroids   , 7 Pool, 11 Frogger
        switch(gameType) {
            case 1:
                setBackground(new GreenfootImage("textures/descriptions/pong.png"));
                break;
            case 2:
                setBackground(new GreenfootImage("textures/descriptions/tetris.png"));
                if (!AudioManager.isPlaying("soundtrack/knddl_tetris.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_tetris.mp3");
                break;
            case 3:
                setBackground(new GreenfootImage("textures/descriptions/snake.png"));
                if(!AudioManager.isPlaying("soundtrack/knddl_snake.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_snake.mp3");
                break;
            case 4:
                setBackground(new GreenfootImage("textures/descriptions/flappybird.png"));
                if (!AudioManager.isPlaying("soundtrack/knddl_flappybird.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_flappybird.mp3");
                break;
            case 5:
                setBackground(new GreenfootImage("textures/descriptions/spaceinvaders.png"));
                if (!AudioManager.isPlaying("soundtrack/knddl_spaceinvader.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_spaceinvader.mp3");
                break;
            case 6:
                setBackground(new GreenfootImage("textures/descriptions/asteroids.png"));
                if(!AudioManager.isPlaying("soundtrack/knddl_asteroids.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_asteroids.mp3");
                break;
            case 11:
                setBackground(new GreenfootImage("textures/descriptions/frog.png"));
                if (!AudioManager.isPlaying("soundtrack/knddl_frogger.mp3"))
                    AudioManager.addPlaying("soundtrack/knddl_frogger.mp3");
                break;
            case 12:
                setBackground(new GreenfootImage("textures/descriptions/nannoid.png"));
                break;
            default:
                GreenfootImage img = new GreenfootImage(getWidth(), getHeight());
                img.setFont(img.getFont().deriveFont(30));
                img.drawString("Do host reingschissn", getWidth() / 2, getHeight() / 2);
                setBackground(img);
                break;
        }
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("enter")) 
            startGame();
        if(Greenfoot.isKeyDown("escape")) {
            if(!escapeKeyDown) {
                exit();
                escapeKeyDown = true;
            }
        }else
            escapeKeyDown = false;
        
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
    }
    
    public void startGame() {
        Greenfoot.setWorld(worldToEnter);
        //1: pong , 2: Tetris , 3: Snake , 4: FlappyBird , 5: SpaceInvader ,
        //6: Asteroids
        switch(gameType) {
            case 1:
                AchievementSystem.addGameCount(2);
                break;
            case 2:
                AchievementSystem.addGameCount(6);
                break;
            case 3:
                AchievementSystem.addGameCount(4);
                break;
            case 4:
                AchievementSystem.addGameCount(1);
                break;
            case 5:
                AchievementSystem.addGameCount(5);
                break;
            case 6:
                AchievementSystem.addGameCount(0);
                break;
        }
    }
    
    
    //1: pong , 2: Tetris , 3: Snake , 4: FlappyBird , 5: SpaceInvader ,
        //6: Asteroids
    public void exit() {
        switch(gameType) {
            case 1:
                Greenfoot.setWorld(new CasinoWorld(false, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(56, 255);
                break;
            case 2:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(56, 327);
                AudioManager.stopPlaying("soundtrack/knddl_tetris.mp3");
                break;
            case 3:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(354, 120);
                AudioManager.stopPlaying("soundtrack/knddl_snake.mp3");
                break;
            case 4:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(408, 120);
                AudioManager.stopPlaying("soundtrack/knddl_flappybird.mp3");
                break;
            case 5:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(466, 120);
                AudioManager.stopPlaying("soundtrack/knddl_spaceinvader.mp3");
                break;
            case 6:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(580, 120);
                AudioManager.stopPlaying("soundtrack/knddl_asteroids.mp3");
                break;
            case 11:
                Greenfoot.setWorld(new CasinoWorld(true, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(142, 120);
                AudioManager.stopPlaying("soundtrack/knddl_frogger.mp3");
                break;
            case 12:
                Greenfoot.setWorld(new CasinoWorld(false, Greenfoot.isKeyDown("escape")));
                CasinoWorld.getWorld().getCharacter().setLocation(212, 120);
                break;
        }
    }
    
    public static LoadMenu getWorld() {
        return world;
    }
}
