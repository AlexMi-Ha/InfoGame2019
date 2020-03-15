import greenfoot.*;

public class PoolStick extends Actor
{
    private double stickLength; //Length of the stick
    //Normal Distance between the tip of the Stick and the white Ball
    private final double NORMAL_DIST_TO_BALL = PoolWorld.getWorld().getBall().getRadius() + 30;
    private double distToBall = NORMAL_DIST_TO_BALL;    //Current Distance to the white Ball

    private boolean mouseDown = false;  
    private double shootPower = 0;      //power of the shot
    private final double MAX_SHOOT_POWER = 20.0;  //maximum of the shoot force

    private double shootPowerIncrease = 0.017 * MAX_SHOOT_POWER; //Increase of the shoot force / tick when shooting
    private double distToIncrease = 1;  //Increase of the stick Distance / tick when shooting

    private GreenfootSound sound_ball;

    public PoolStick() {
        GreenfootImage img = new GreenfootImage(400, 10);
        img.setColor(new Color(113, 64, 0));
        img.fill();
        setImage(img);
        try {
            sound_ball = new GreenfootSound("sound_effects/pool/pool_ball.mp3");
            sound_ball.setVolume(70);
        }catch(Exception e) {}
        stickLength = getImage().getWidth();
    }

    public void act() 
    {
        if(PoolWorld.getWorld().getShootTime()) {   //locate the Stick around the Ball when shooting
            rotateStick();
            locateStick();
            prepShot();
        }else { //or off-screen when not
            setLocation(-20, -20);
            setRotation(0);
        }
    }
    //Fix the Position of the stick on a circle around the White Ball
    public void locateStick() {
        double rot = getRotation();
        double a = Math.sin(Math.toRadians(rot)) * (stickLength + distToBall) / 2;
        double b = Math.cos(Math.toRadians(rot)) * (stickLength + distToBall) / 2;
        double newX = PoolWorld.getWorld().getBall().getExactX() - b;
        double newY = PoolWorld.getWorld().getBall().getExactY() - a;
        setLocation((int)newX, (int)newY);
    }
    //Rotate the Stick around the white Ball depending on the Mouse position
    public void rotateStick() {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo != null) {
            double mouseX = mouseInfo.getX();
            double mouseY = mouseInfo.getY();
            double rot = 180.0 + Math.atan2(mouseY - PoolWorld.getWorld().getBall().getExactY(),
                    mouseX - PoolWorld.getWorld().getBall().getExactX()) 
                * (180.0f / Math.PI);
            setRotation((int)rot);
        }
    }
    //prepare for a following shot  -> shootPower, stickDistance
    public void prepShot() {
        if(Greenfoot.mousePressed(null)){
            mouseDown = true;
            shootPower = 0;
        }
        if(Greenfoot.mouseClicked(null)){
            if(mouseDown)
                shoot();
            mouseDown = false;
        }
        if(mouseDown){
            shootPower += shootPowerIncrease;
            distToBall += distToIncrease;
            if(shootPower > MAX_SHOOT_POWER) {
                shootPower = MAX_SHOOT_POWER;
                distToBall -= distToIncrease;
            }
        }
    }
    //apply the shootPower to the Ball and reset the Stick
    public void shoot() {
        double dx = shootPower * Math.cos(Math.toRadians(getRotation()));
        double dy = shootPower * Math.sin(Math.toRadians(getRotation()));
        PoolWorld.getWorld().getBall().move(dx, dy);
        shootPower = 0;
        distToBall = NORMAL_DIST_TO_BALL;
        PoolWorld.getWorld().setShootTime(false);
        PoolWorld.getWorld().getMovesDisplay().addPoints(1);
        try{
            sound_ball.play();
        }catch(Exception e) {}
    }
}
