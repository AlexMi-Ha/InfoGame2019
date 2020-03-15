import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Asteroid object in Asteroids

public class Asteroid extends SmoothMover
{

    private int r;          //size of the Asteroid
    private Vector vel;     //velocity of the Asteroid
    
    private GreenfootSound sound_break;

    //constructor for a not moving Asteroid
    public Asteroid(int r) {
        GreenfootImage img = new GreenfootImage(r, r);
        img.setColor(Color.LIGHT_GRAY);
        img.fillOval(0, 0, r, r);
        setImage(img);

        this.r = r;
        
        try {
            sound_break = new GreenfootSound("sound_effects/meteor_explode.wav");
            sound_break.setVolume(87);
        }catch(Exception e) {}
    }
    //constructor for a moving Asteroid with a size
    public Asteroid(int r, Vector vel) {
        GreenfootImage img = new GreenfootImage(r, r);
        img.setColor(Color.LIGHT_GRAY);
        img.fillOval(0, 0, r, r);
        setImage(img);

        this.r = r;
        this.vel = vel;
        
        try {
            sound_break = new GreenfootSound("sound_effects/meteor_explode.wav");
            sound_break.setVolume(87);
        }catch(Exception e) {}
    }
    //constructor for a moving Asteroid with a random size
    public Asteroid(Vector vel) {
        r = Greenfoot.getRandomNumber(35) + 90;     //random size between 90 & 125

        GreenfootImage img = new GreenfootImage(r, r);
        img.setColor(Color.LIGHT_GRAY);
        img.fillOval(0, 0, r, r);
        setImage(img);

        this.vel = vel;
        
        try {
            sound_break = new GreenfootSound("sound_effects/meteor_explode.wav");
            sound_break.setVolume(87);
        }catch(Exception e) {}
    }
    //manage movement
    public void act() {

        setLocation(getExactX() + vel.getX(), getExactY() + vel.getY());

        double x = getExactX() + vel.getX();
        double y = getExactY() + vel.getY();
        if(x >= getWorld().getWidth() -3) {
            x = 4;
        }
        if(x <= 3) {
            x = getWorld().getWidth() - 4;
        }
        if(y >= getWorld().getHeight() - 3) {
            y = 4;
        }
        if(y<= 3) {
            y = getWorld().getHeight() - 4;
        }
        setLocation(x, y);

    }
    //mange collision detection and splitting
    public void hit() {
        if(r >= 100) 
            AsteroidWorld.getWorld().addPoints(20);
        else if(r >= 90 && r < 100) 
            AsteroidWorld.getWorld().addPoints(50);
        else if(r >= 70 && r < 90) 
            AsteroidWorld.getWorld().addPoints(100);
        else if(r >= 35 && r < 70) 
            AsteroidWorld.getWorld().addPoints(200);
        else if(r < 35) 
            AsteroidWorld.getWorld().addPoints(1000);
            
        try {
            sound_break.play();
        }catch(Exception e) {}

        if(r <= 25) 
            AsteroidWorld.getWorld().removeObject(this);
        else {
            Vector speed = new Vector((vel.getDirection() + Greenfoot.getRandomNumber(45)) + 60,
                    vel.getLength());
            Vector speed1 = new Vector((vel.getDirection() + Greenfoot.getRandomNumber(45)) - 60,
                    vel.getLength());                                      
            AsteroidWorld.getWorld().addObject(new Asteroid(r / 2, speed), getX(), getY());
            AsteroidWorld.getWorld().addObject(new Asteroid(r / 2, speed1), getX(), getY());
            AsteroidWorld.getWorld().removeObject(this);
        }
    }
}
