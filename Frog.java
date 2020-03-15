import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Frog extends Actor
{
    private int buttonCoolDown = 0;     //cooldown for button Presses
    
    private GreenfootSound sound_jump, sound_point;
    
    private boolean dieAnim = false;
    private int animTimer = 0;
    
    public Frog() {
        setImage(new GreenfootImage("textures/games/frogger/frog.png"));
        
        sound_point = new GreenfootSound("sound_effects/frogger_point.wav");
        sound_point.setVolume(100);
        sound_jump = new GreenfootSound("sound_effects/frogger_jump.wav");
        sound_jump.setVolume(80);
    }
    //Manage Death, walking and Victory
    public void act() {
        if(dieAnim)
            deathAnimation();
        if(!FroggerWorld.getWorld().getDead()) {
            if(buttonCoolDown == 0) 
                walk();
            if(buttonCoolDown > 0)
                buttonCoolDown--;
            
            FroggerTree t = (FroggerTree)getOneIntersectingObject(FroggerTree.class);
            FroggerEnd e = (FroggerEnd)getOneIntersectingObject(FroggerEnd.class);
            FroggerCar c = (FroggerCar)getOneIntersectingObject(FroggerCar.class);
            if((getY() < 300 && t == null && e == null) || c != null || (e != null && e.getFull())) {
                FroggerWorld.getWorld().die();
                deathAnimation();
            }
            if(e != null && !e.getFull()) {
                FroggerWorld.getWorld().setFrogsInVictory(FroggerWorld.getWorld().getFrogsInVictory() + 1);
                e.setFull(true);
                
                sound_point.play();
                FroggerWorld.getWorld().getScoreDisplay().addPoints(100);
                if(FroggerWorld.getWorld().getFrogsInVictory() >= 3) {
                    FroggerWorld.getWorld().newLevel();
                }else {
                    setLocation(300, 575);
                }
            }
        }
    }
    
    public void deathAnimation() {
        GreenfootImage img = getImage();
        animTimer++;
        
        if(animTimer >= 50) {
            img.setTransparency(255);
            animTimer = 0;
            dieAnim = false;
            return;
        }
        
        dieAnim = true;
        int t = 255;
        int dT = 25;
        if(animTimer <= 8)
            t = img.getTransparency() - dT;
        else if(animTimer <= 16)
            t = img.getTransparency() + dT;
        else if(animTimer <= 24)
            t = img.getTransparency() - dT;
        else if(animTimer <= 32)
            t = img.getTransparency() + dT;
        else if(animTimer <= 40)
            t = img.getTransparency() - dT;
        else if(animTimer <= 48)
            t = img.getTransparency() + dT;

        if(t <= 255 && t >= 0)
            img.setTransparency(t);
        setImage(img);        
    }
    
    //Manage walking
    public void walk() {
        int dx = 0;
        int dy = 0;
        int buttonCoolDownMax = 17;
        int rot = getRotation();
        if(Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("up")) {
            dy = -50;
            buttonCoolDown = buttonCoolDownMax;
            rot = 0;
        }else if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("down")) {
            dy = 50;
            buttonCoolDown = buttonCoolDownMax;
            rot = 180;
        }else if(Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("right")) {
            dx = 50;
            buttonCoolDown = buttonCoolDownMax;
            rot = 90;
        }else if(Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("left")) {
            dx = -50;
            buttonCoolDown = buttonCoolDownMax;
            rot = 270;
        }
        
        setLocation(getX() + dx, getY());
        if(getX() <= 0 || getX() >= FroggerWorld.getWorld().getWidth())
            setLocation(getX() - dx, getY());
        setLocation(getX(), getY() + dy);
        if(getY() <= 0 || getY() >= FroggerWorld.getWorld().getHeight())
            setLocation(getX(), getY() - dy);
        if(!(dx == 0 && dy == 0))
            setRotation(rot);
        
        try {
            if(dx != 0 || dy != 0)
                sound_jump.play();
        }catch(Exception e) {}
    }
}
