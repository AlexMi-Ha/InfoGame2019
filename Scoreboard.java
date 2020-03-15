import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Scoreboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Scoreboard extends Actor
{
    private String text;
    private int value;
    private Color c = Color.WHITE;
    private int size = 24;
    
    public Scoreboard(String text, int value) {
        GreenfootImage img = new GreenfootImage(700, 50);
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString(text + value, 20, 25);
        setImage(img);
        
        this.text = text;
        this.value = value;
    }
    
    public Scoreboard(String text, int value, int w) {
        GreenfootImage img = new GreenfootImage(w, 50);
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString(text + value, 20, 25);
        setImage(img);
        
        this.text = text;
        this.value = value;
    }
    
    public Scoreboard(String text, int value, int w, Color c) {
        GreenfootImage img = new GreenfootImage(w, 50);
        img.setColor(c);
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString(text + value, 20, 25);
        setImage(img);
        this.c = c;
        this.text = text;
        this.value = value;
    }
    
    
    public Scoreboard(String text, int value, Color c, int size) {
        GreenfootImage img = new GreenfootImage(700, 50);
        img.setColor(c);
        img.setFont(img.getFont().deriveFont(size));
        img.drawString(text + value, 20, 25);
        setImage(img);
        
        this.c = c;
        this.size = size;
        this.text = text;
        this.value = value;
    }
    
    public void addPoints(int pointsToAdd) {
        value += pointsToAdd;
        
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(c);
        img.setFont(img.getFont().deriveFont(size));
        img.drawString(text + value, 20, 25);
        setImage(img);
    }
    
    public void removePoints(int pointsToRemove) {
        value -= pointsToRemove;
        
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString(text + value, 20, 25);
        setImage(img);
    }
    
    public int getScore() {
        return value;
    }
    
    
    //-----------------------------------------------------------------------
    //Pong display
    
    int score1 = 0;		//score for player 1
    int score2 = 0;		//score for player 2
    
    public Scoreboard(boolean pong) {
        GreenfootImage img = new GreenfootImage(400, 50);
        img.setColor(Color.WHITE);
        img.setFont(new Font("Consolas", 24));
        img.drawString("Player: " + score1 + "          Computer: " + score2, 10, 25);
        setImage(img);
    }
    
    //add points to one of the scores
    public void addPongPoints(int target) {
        if(target == 0) 
            score1 += 1;
        if(target == 1)
            score2 += 1;
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.WHITE);
        img.drawString("Player: " + score1 + "          Computer: " + score2, 10, 25);
        setImage(img);
    }
    
    
    //SlotMachine constructor
    public Scoreboard(int coins) {
        GreenfootImage img = new GreenfootImage(300, 50);
        img.setColor(Color.WHITE);
        this.value = coins;
        this.text = "Coins: ";
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString("Coins: " + value, 10, 25);
        setImage(img);
    }
    
    public void addCoins(int coinsToAdd) {
        value += coinsToAdd;
        GreenfootImage img = getImage();
        img.clear();
        img.drawString("Coins: " + value, 10, 25);
        setImage(img);
    }
    //remove coins from the current amount
    public void removeCoins(int coinsToRemove) {
        value -= coinsToRemove;
        GreenfootImage img = getImage();
        img.clear();
        img.drawString("Coins: " + value, 10, 25);
        setImage(img);
    }
}
