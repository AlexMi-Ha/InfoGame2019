import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PoolBall extends Ball
{

    private double dx = 0;  //Horizontal speed
    private double dy = 0;  //Vertical speed

    private GreenfootSound sound_ball, sound_wall;

    private boolean inWorld = true; //active in the World

    public PoolBall(Color c, int x, int y) {
        super(10, c, x, y); //ball with radius 10, color c, and coords (x | y)

        sound_ball = new GreenfootSound("sound_effects/pool/pool_ball.mp3");
        sound_wall = new GreenfootSound("sound_effects/pool/pool_wall.mp3");
    }
    //Manage the Movement
    public void act() {
        double x = getExactX() + dx;
        double y = getExactY() + dy;
        setLocation(x, y);
    }
    //set the velocity
    protected void move(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void playBallSound() {
        double volume = Math.hypot(dx, dy) * 20 + 60;
        if(volume >= 100)
            volume = 100;
        else if(volume <= 40)
            volume = 40;
            
        try {
            sound_ball.setVolume((int)volume);
            sound_ball.play();
        }catch(Exception e) {}
    }

    public void playWallSound() {
        double volume = Math.hypot(dx, dy) * 20 +  60;
        if(volume >= 90)
            volume = 90;
        else if(volume <= 30)
            volume = 30;
            
        try {
            sound_wall.setVolume((int)volume);
            sound_wall.play();
        }catch(Exception e) {}
    }

    //getter / setter

    protected double getXVel() {
        return dx;
    }

    protected double getYVel() {
        return dy;
    }

    public double getMovedX() {
        return getExactX() + dx;
    }

    public double getMovedY() {
        return getExactY() + dy;
    }

    public boolean getInWorld() {
        return inWorld;
    }

    public void setInWorld(boolean b) {
        inWorld = b;
    }
}
