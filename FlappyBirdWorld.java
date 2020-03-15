import greenfoot.*;  

public class FlappyBirdWorld extends World {

    private Bird bird;                      //character var
    private static FlappyBirdWorld _world;  //world singleton
    private int birdCounter = 0;            //Counter for the score
    private int pillarCounter = 90;         //Counter for pipe spacing/spawning
    private final int PIPE_SPACE = 110;     //Space between the the two parts of the Pipe
    private final int PIPE_CONST = 280;     //First Pipe after PIPE_CONST Game ticks
    private int score = 0;                  //score
    private Scoreboard scoreObj;           //score Display var
    
    private int achievementTimer = 0;      //timer for the time achievement
    
    private int restartTimer = 0;
    private boolean restarting = false;
    
    private FlappyGround fg1, fg2;
    private int cGroundMoving = 1;
    
    private FlappyBackground bg1, bg2;
    private int cBgMoving = 1;
    
    private GreenfootSound sound_point, sound_die;
    
    public FlappyBirdWorld() {    
        super(600, 400, 1, false); 
        setPaintOrder(Bird.class, Scoreboard.class, FlappyGround.class, Pillar.class, FlappyBackground.class);

        GreenfootImage img = new GreenfootImage("textures/games/flappy/background.png");
        setBackground(img);
        
        
        _world = this;
        bird = new Bird();
        addObject(bird,getWidth() / 2 - 200, getHeight() / 2);

        scoreObj = new Scoreboard("", 0, Color.BLACK, 32);
        addObject(scoreObj, 345 , 35);
        
        fg1 = new FlappyGround();
        fg2 = new FlappyGround();
        addObject(fg1, getWidth() / 2, 368);
        addObject(fg2, getWidth() + fg2.getImage().getWidth() / 2, 368);
        
        bg1 = new FlappyBackground();
        bg2 = new FlappyBackground();
        addObject(bg1, getWidth() / 2, getHeight() / 2);
        addObject(bg2, getWidth() + bg2.getImage().getWidth() / 2, getHeight() / 2);
        
        GreenfootImage imgBg = bg1.getImage();
        imgBg.setColor(Color.WHITE);
        imgBg.setFont(img.getFont().deriveFont(20));
        imgBg.drawString("Drücke Pfeil nach oben um das Spiel zu starten!", 95, 300);
        bg1.setImage(imgBg);
        
        try {
            sound_point = new GreenfootSound("sound_effects/flappy/flappy_point.wav");
            sound_point.setVolume(95);
            sound_die = new GreenfootSound("sound_effects/flappy/flappy_die.wav");
            sound_die.setVolume(95);
        }catch(Exception e) {}
    }
    //manage pillars and score
    public void act() {
        if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w")) && getBird().getG() == 0) {
            bg1.setImage(new GreenfootImage("textures/games/flappy/moving_bg.png"));
            fg1.setMoving(true);
            bg1.setMoving(true);
        }
        if(getBird().getG() != 0 && !restarting) {
            pillarCounter++;
            if(pillarCounter % 100 == 0) 
                addPillar();

            if(pillarCounter >= PIPE_CONST) {
                if(birdCounter % 100 == 0) {
                    score++;
                    scoreObj.addPoints(1);
                    try {
                        sound_point.play();
                    }catch(Exception e) {}
                    if(score == 20) {
                        AchievementSystem.addAchievement("flappy_trophy");
                    }    
                }
                birdCounter++;
            }
            
            if(cGroundMoving == 1)
                fg2.moveWith(fg1);
            else if(cGroundMoving == 2)
                fg1.moveWith(fg2);
        
            if(fg1.isAtBorder()) {
                cGroundMoving = 2;
                fg2.setMoving(true);
                fg1.setMoving(false);
            }
            if(fg2.isAtBorder()) {
                cGroundMoving = 1;
                fg1.setMoving(true);
                fg2.setMoving(false);
            }
            
            if(cBgMoving == 1)
                bg2.moveWith(bg1);
            else if(cBgMoving == 2)
                bg1.moveWith(bg2);
        
            if(bg1.isAtBorder()) {
                cBgMoving = 2;
                bg2.setMoving(true);
                bg1.setMoving(false);
            }
            if(bg2.isAtBorder()) {
                cBgMoving = 1;
                bg1.setMoving(true);
                bg2.setMoving(false);
            }
        }
        
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        
        if(restarting) 
            gameOver();
            
        //pause
        if(Greenfoot.isKeyDown("escape")) 
            Greenfoot.setWorld(new LoadMenu(this, 4, Greenfoot.isKeyDown("escape")));
    }
    //add new pillars at a random height
    public void addPillar() {
        Pillar topP = new Pillar(true);
        Pillar botP = new Pillar(false);

        GreenfootImage imgTop = botP.getImage();
        int y = Greenfoot.getRandomNumber(getHeight() - PIPE_SPACE);
        if(y < PIPE_SPACE + 5)
            y = PIPE_SPACE + 5;
        addObject(botP, getWidth(), y + imgTop.getHeight() / 2);
        addObject(topP, getWidth(), y - imgTop.getHeight() / 2 - PIPE_SPACE);
    }
    
    public void gameOver() {
        if(restartTimer == 0)
            sound_die.play();
        restartTimer++;
        restarting = true;
        bird.setYSpeed(0);
        fg1.setMoving(false);
        fg2.setMoving(false);
        bg1.setMoving(false);
        bg2.setMoving(false);
        for(Pillar p : getObjects(Pillar.class))
            p.setVel(0);
        if(restartTimer >= 50)
            Greenfoot.setWorld(new LoadMenu(new FlappyBirdWorld(), 4, Greenfoot.isKeyDown("escape")));
    }
    
    //getter
    public Bird getBird() {
        return bird;
    }

    public static FlappyBirdWorld getWorld() {
        return _world;
    }
}
