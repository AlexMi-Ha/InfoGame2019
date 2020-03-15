import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Bullet of the Rocket in Asteroids

public class AsteroidBullet extends Actor
{
    private Vector vel;         //velocity of the Bullet
    private int lifeTime = 50;  //lifeTime of the bullet (range)
    private boolean inWorld;    //currently in the World  (prevents Actor not in World exception)

    private GreenfootSound sound_shot;
    
    public AsteroidBullet(int rot) {
        GreenfootImage img = new GreenfootImage(10,2);
        img.setColor(Color.WHITE);
        img.fill();
        setImage(img);

        setRotation(rot);
        vel = new Vector(getRotation(), 10);
        try {
            sound_shot = new GreenfootSound("sound_effects/laser_shot.wav");
            sound_shot.setVolume(80);
            sound_shot.play();
        }catch(Exception e) {}
        inWorld = true;
    }
    //manage movement
    public void act() {

        lifeTime--;
        setLocation(getX() + (int)vel.getX(), getY() + (int)vel.getY());

        double x = getX() + vel.getX();
        double y = getY() + vel.getY();

        detectHit();
        if(inWorld) {
            if(x >= getWorld().getWidth()) 
                AsteroidWorld.getWorld().removeObject(this);
            else if(x < 0) 
                AsteroidWorld.getWorld().removeObject(this);
            else if(y >= getWorld().getHeight()) 
                AsteroidWorld.getWorld().removeObject(this);
            else if(y< 0) 
                AsteroidWorld.getWorld().removeObject(this);

            if(lifeTime==0) {
                AsteroidWorld.getWorld().removeObject(this);
                inWorld = false;
            }

        }
    }
    //hit detection
    public void detectHit() {
        if(inWorld) {
            Asteroid a1 = (Asteroid)getOneIntersectingObject(Asteroid.class);
            if(a1 != null) {
                a1.hit();
                AsteroidWorld.getWorld().removeObject(this);
                inWorld = false;
            }
        }
    }
}
