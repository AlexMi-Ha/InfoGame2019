import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Rocket character of Asteroids

public class AsteroidRocket extends SmoothMover
{
    private Vector vel = new Vector();      //current velocity of the rocket
    private int lives;                  //lives of the player
    private int shootDelayTime = 0;         //Timer for the shootDelay
    private final int SHOOTDELAY = 10;      //Shot Delay
    
    private GreenfootSound sound_hit;

    public AsteroidRocket(int lives) {
        GreenfootImage img = new GreenfootImage(40,40);
        img.setColor(Color.WHITE);
        int[] i ={0, 40, 00};
        int[] j ={0, 20, 40};
        img.fillPolygon(i, j, 3);
        setImage(img);

        this.lives = lives;
        
        try {
            sound_hit = new GreenfootSound("sound_effects/spaceship_hit.wav");
            sound_hit.setVolume(100);
        }catch(Exception e) {}
    }

    public void act() {

        shootDelayTime++;
        move();
        turn();
        boost();

        if(Greenfoot.isKeyDown("space"))
            shoot();
        detectHit();

    }
    //boost the rocket in the facing direction
    public void boost() {
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) {
            Vector force = new Vector(getRotation(), 0.1);
            force.scale(0.5);
            vel.add(force);
        }
    }
    //rotate the rocket
    public void turn() {
        if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d"))
            setRotation(getRotation() + 3);
        if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a"))
            setRotation(getRotation() - 3);
    }
    //move the rocket
    public void move() 
    {
        double x = getExactX() + vel.getX();
        double y = getExactY() + vel.getY();
        if(x >= getWorld().getWidth() - 3) 
            x = 4;
        if(x <= 3) 
            x = getWorld().getWidth() - 4;
        if(y >= getWorld().getHeight() - 3) 
            y = 4;
        if(y<= 3) 
            y = getWorld().getHeight() - 4;
        setLocation(x, y);

        vel.scale(0.985);
    }
    //shoot a bullet
    public void shoot() {
        if(shootDelayTime >= SHOOTDELAY) {
            shootDelayTime = 0;
            AsteroidWorld.getWorld().addObject(new AsteroidBullet(getRotation()), getX(), getY());
        }
    }

    //manage collision detection
    public void detectHit() {
        Asteroid a1 = (Asteroid)getOneIntersectingObject(Asteroid.class);
        if(a1 != null) {
            AsteroidWorld.getWorld().removeObject(a1);
            lives--;
            AsteroidWorld.getWorld().getLivesDisplay().removePoints(1);
            
            try {
                sound_hit.play();
            }catch(Exception e) {}
            
            if(lives == 0) 
                AsteroidWorld.getWorld().gameOver(); 
        }
    }

    public void addLive() {
        lives++;
    }
}
