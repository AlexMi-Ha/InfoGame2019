import greenfoot.*; 

public class SlotWorld extends World
{
    private static SlotWorld world;         //world singleton
    private SlotButton btn;                 //roll button
    private SlotIcon slot1;                 //slot 1
    private SlotIcon slot2;                 //slot 2
    private SlotIcon slot3;                 //slot 3
    private Scoreboard display;             //Display for the coins
    private InputDisplay inputdisplay1;     //Input Display (Coin Input: 1)
    private InputDisplay inputdisplay2;     //Input Display (Coin Input: 5)
    private boolean running;                //currently running
    private int result1, result2, result3;  //the 3 results for the 3 slots
    private int input;                      //the current coin input
    private final int START_COINS = 20;     //Coins the user starts with
    
    private int achievementTimer = 0;       //timer for the time achievement
    //                   A   B   C   D   E   F  G  H
    int[] propability = {17, 25, 19, 17, 12, 7, 2, 1};      //propabilities for the rewards
    
    private int characterX, characterY, characterFacing;  //Position of the player interacting with the Pool table
    
    private GreenfootSound sound_roll, sound_spin, sound_win, sound_lose;
    
    public SlotWorld(int characterX, int characterY, int characterFacing) {
        super(800, 600, 1);
        
        this.characterX = characterX;
        this.characterY = characterY;
        this.characterFacing = characterFacing;
        
        constructWorld();
    }
    
    public SlotWorld()
    {    
        super(800, 600, 1); 
        constructWorld();
    }
    
    public void constructWorld() {
        world = this;
        
        //GreenfootImage img = new GreenfootImage(800, 600);
        GreenfootImage img = new GreenfootImage("textures/games/slot/slot_machine.png");
        //img.setColor(new Color(64,64,64));
        //img.fill();
        img.scale(800,600);
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(24));
        img.drawString("ESC um zurück zu kommen", getWidth() / 2- 155, getHeight() - 33);
        img.setColor(Color.BLACK);
        img.drawString("-Einsatz-", getWidth() / 2- 61, getHeight() - 167);
        //img.setColor(new Color(168,168,168));
        //img.fillRect(getWidth() / 2 - 180, getHeight() / 2 - 50,2* 180, 100);
        setBackground(img);
        
        btn = new SlotButton();
        slot1 = new SlotIcon(0, 1);
        slot2 = new SlotIcon(0, 2);
        slot3 = new SlotIcon(0, 3);
        display = new Scoreboard(20);
        inputdisplay1 = new InputDisplay(1, true);
        inputdisplay2 = new InputDisplay(5, false);
        addObject(inputdisplay1, 283, 425);
        addObject(inputdisplay2, 489, 425);
        addObject(btn, 729, 110);
        addObject(slot2, 385, 285);
        addObject(slot1, 180, 285);
        addObject(slot3, 590, 285);
        addObject(display, 195, 568);
        
        running = false;
        input = 1;
        
        AchievementSystem.addGameCount(3);
        
        try {
            sound_roll = new GreenfootSound("sound_effects/slot/slot_pull.wav");
            sound_spin = new GreenfootSound("sound_effects/slot/slot_spin.wav");
            sound_lose = new GreenfootSound("sound_effects/slot/slot_lose.wav");
            sound_win = new GreenfootSound("sound_effects/slot/slot_win.wav");
            
            sound_spin.setVolume(95);
            sound_roll.setVolume(99);
        }catch(Exception e) {}
    }
    
    public void act() {
        /*//add 1 to the "time achievement Timer" every second
        if(!AchievementSystem.getTimeTrophyState()) {
            achievementTimer = (achievementTimer+1) % 55;
            if (achievementTimer == 0)
                AchievementSystem.timeDifference();
        }*/
        
        if(Greenfoot.isKeyDown("escape")) {
            
            try {
                sound_spin.stop();
            }catch(Exception e) {}
            
            Greenfoot.setWorld(new CasinoWorld(false, Greenfoot.isKeyDown("escape")));
            if(characterX != 0 && characterY != 0) {
                CasinoWorld.getWorld().getCharacter().setLocation(characterX, characterY);
                CasinoWorld.getWorld().getCharacter().setFacing(characterFacing);
            }else {
                CasinoWorld.getWorld().getCharacter().setLocation(440, 335);
                CasinoWorld.getWorld().getCharacter().setFacing(2);
            }
        }
    }
    
    //roll the 3 slots
    public void roll() {
        running = true;
        
        try {
            sound_roll.play();
            sound_spin.playLoop();
        }catch(Exception e) {}
        
        //generate 3 random results for the slots
        result1 = slot1.generateResult();          
        result2 = slot2.generateResult();
        result3 = slot3.generateResult();
        //generate 3 random rolling times for each slot
        int size1 = Greenfoot.getRandomNumber(20);
        int size2 = Greenfoot.getRandomNumber(20);
        int size3 = Greenfoot.getRandomNumber(20);
        if(size1 == 0)
            size1 = 20;
        if(size2 == 0)
            size2 = 20;
        if(size3 == 0)
            size3 = 20;
        //generate 3 random animations (images orders) for the slots    
        //slot1
        int[] imgOrder1 = new int[size1];
        for(int i = 0; i < imgOrder1.length - 1; i++) 
            imgOrder1[i] = slot1.generateResult();
        imgOrder1[imgOrder1.length - 1] = result1;
        
        //slot2
        int[] imgOrder2 = new int[size2];
        for(int i = 0; i < imgOrder2.length - 1; i++) {
            imgOrder2[i] = slot2.generateResult();
        }
        imgOrder2[imgOrder2.length - 1] = result2;
        
        //slot3
        int[] imgOrder3 = new int[size3];
        for(int i = 0; i < imgOrder3.length - 1; i++) {
            imgOrder3[i] = slot3.generateResult();
        }
        imgOrder3[imgOrder3.length - 1] = result3;
        //start the animation for the 3 slots
        slot1.startAnimation(imgOrder1);
        slot2.startAnimation(imgOrder2);
        slot3.startAnimation(imgOrder3);
    }
    //manage the coin output
    public void manageScore() {
        int coinInput = input;
        if((result1 == 0 ^ result2 == 0 ^ result3 == 0) ^ (result1 == 0 && result2 == 0 && result3 == 0))
            coinInput *= 2; //1 A  =  input * 2
        else if(!((result1 == 0 ^ result2 == 0 ^ result3 == 0) ^ (result1 == 0 && result2 == 0 && result3 == 0 )) 
        && !(result1 == 0 && result2 == 0 && result3 == 0) && !(!(result1 == 0) && !(result2 == 0) && !(result3 == 0)))
            coinInput *= 3; //2 A  =  input * 3 
        else if(result1 == 0 && result2 == 0 && result3 == 0)
            coinInput *= 5; //3 A  =  input * 5
        else if(result1 == 1 && result2 == 1 && result3 == 1)
            coinInput *= 10; //3 B  =  input * 10
        else if(result1 == 2 && result2 == 2 && result3 == 2)
            coinInput *= 20; //3 C  =  input * 20
        else if(result1 == 3 && result2 == 3 && result3 == 3)
            coinInput *= 50; //3 D  =  input * 50
        else if(result1 == 4 && result2 == 4 && result3 == 4)
            coinInput *= 100; //3 E  =  input * 100
        else if(result1 == 5 && result2 == 5 && result3 == 5) {
            AchievementSystem.addAchievement("lucky_trophy");
            coinInput *= 200; //3 F  =  input * 200
        }else if(result1 == 6 && result2 == 6 && result3 == 6) {
            AchievementSystem.addAchievement("lucky_trophy");
            coinInput *= 500; //3 G  =  input * 500
        }else if(result1 == 7 && result2 == 7 && result3 == 7) {
            AchievementSystem.addAchievement("lucky_trophy");
            coinInput *= 1000; //3 H  =  input * 1000
        }else    //no win    coin output = 0
           coinInput = 0; 
        //System.out.println("Coins returned: " + coinInput);
        
        try {
            if(coinInput == 0) 
                sound_lose.play();
            else
                sound_win.play();
        }catch(Exception e) {}
        //add the coins to the display
        display.addCoins(coinInput);
    }
    //set both coin input displays to not selected
    public void notSelected() {
        inputdisplay1.notSelected();
        inputdisplay2.notSelected();
    }
    
    //getter / setter
    
    public int[] getPropability() {
        return propability;
    } 
    
    public SlotButton getButton() {
        return btn;
    }
    
    public Scoreboard getDisplay() {
        return display;
    }
    
    public SlotIcon getSlot1() {
        return slot1;
    }
    
    public SlotIcon getSlot2() {
        return slot2;
    }
    
    public SlotIcon getSlot3() {
        return slot3;
    }
    
    public void setCoinInput(int input) {
        this.input = input;
    }
    
    public int getCoinInput() {
        return input;
    }
    
    public static SlotWorld getWorld() {
        return world;
    }
    
    public boolean isRunning() {
        return running;
    }
    
    public void setRunning(boolean running) {
        this.running = running;
        try {
            if(!running)
                sound_spin.stop();
        }catch(Exception e) {}
    }
}
