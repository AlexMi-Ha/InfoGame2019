import greenfoot.*;  

//FlappyBird character

public class Bird extends Actor {
    private double ySpeed = 0;           //current (falling) Speed
    private double g = 0;              //temp gravity
    private final double gCONSTANT = 0.5;//Gravity constant
    private final int BOOST_SPEED = -6;  //Constant for Boosting upwards force
    
    private boolean buttonDown = false;

    public Bird() {
        setImage(new GreenfootImage("textures/games/flappy/komischervogel.png"));
    }
    
    public void act() {
        if(isTouching(Pillar.class)) 
            FlappyBirdWorld.getWorld().gameOver();

        rotateBird();
        setLocation(getX(), (int)(getY() + ySpeed));        
        if(Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("space") || Greenfoot.isKeyDown("w"))  {
            if(!buttonDown) {
                ySpeed = BOOST_SPEED;
                g = gCONSTANT;
                FlappyBirdWorld.getWorld().addObject(new FlappySound(), -20, -20);
            }
            buttonDown = true;
        }else
            buttonDown = false;

        if(getY() > FlappyBirdWorld.getWorld().getHeight() - 70|| getY() < -20) 
            FlappyBirdWorld.getWorld().gameOver();

        ySpeed = ySpeed + g;
    }   
    //Rotating the Bird at different velocitys
    public void rotateBird() {
        if(g != 0) {
            if(ySpeed < 1)
                setRotation(-30);
            else if(ySpeed > 1 && ySpeed < 8) 
                setRotation(30);
            else if(ySpeed > 8 && ySpeed < 12)
                setRotation(50);
            else if(ySpeed > 12)
                setRotation(80);
            else
                setRotation(0);
        }
    }

    //getter
    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }
    
    public double getG() {
        return g;
    }
}
