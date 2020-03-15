import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//character in the main lobby

public class Character extends Actor
{
    private boolean ableToMove = true;      //if it is currently able to move  (false when interacting)
    private boolean moving = false;         //if it is currenty moving
    
    private CharacterHitBox cHitBox;        //the hit detection box for the character
    private CharacterShadow cShadow;        //the shadow for the character
    
    private int animTimer = 0;              //timer for walking animations
    private int facing = 0;                 //facing: 0:down; 1:right; -1:left; 2:up 
    
    private final int nudgeHitBox = 19;     //pixels to nudge the hit box       
    private final int nudgeShadow = 34;     //pixels to nudge the shadow  
    
    private GreenfootImage[] dImg;         //animation image collections
    private GreenfootImage[] rImg;
    private GreenfootImage[] lImg;
    private GreenfootImage[] uImg;
    
    public Character(int x, int y) {

        cHitBox = new CharacterHitBox();
        CasinoWorld.getWorld().addObject(cHitBox, x, y + nudgeHitBox);
        
        cShadow = new CharacterShadow();
        CasinoWorld.getWorld().addObject(cShadow, x, y + nudgeShadow);
        
        dImg = new GreenfootImage[3];
        dImg[0]  = new GreenfootImage("textures/character/front1.png");
        dImg[1]  = new GreenfootImage("textures/character/front2.png");
        dImg[2]  = new GreenfootImage("textures/character/front3.png");
        setImage(dImg[0]);
        
        lImg = new GreenfootImage[3];
        lImg[0]  = new GreenfootImage("textures/character/side1.png");
        lImg[1]  = new GreenfootImage("textures/character/side2.png");
        lImg[2]  = new GreenfootImage("textures/character/side3.png");

        rImg = new GreenfootImage[3];
        rImg[0]  = new GreenfootImage("textures/character/side1.png");
        rImg[1]  = new GreenfootImage("textures/character/side2.png");
        rImg[2]  = new GreenfootImage("textures/character/side3.png");
        
        for(GreenfootImage i : rImg) {
            i.mirrorHorizontally();
        }
        
        uImg = new GreenfootImage[3];
        uImg[0]  = new GreenfootImage("textures/character/back1.png");
        uImg[1]  = new GreenfootImage("textures/character/back2.png");
        uImg[2]  = new GreenfootImage("textures/character/back3.png");
    }
    
    public void act() 
    {
        tryMove(2);
        //trigger the walking animations
        if(moving) {
            switch(facing) {
                case 0:
                    downAnim();
                    break;
                case 1:
                    rightAnim();
                    break;
                case -1:
                    leftAnim();
                    break;
                case 2:
                    upAnim();
                    break;
            }
        }else {
            animTimer = 0;
            switch(facing) {
                case 0:
                    setImage(dImg[0]);
                    break;
                case 1:
                    setImage(rImg[0]);
                    break;
                case -1:
                    setImage(lImg[0]);
                    break;
                case 2:
                    setImage(uImg[0]);
                    break;
            }
        }
    }   
    
    
    //manage the movement
    public void tryMove(int speed) {
        int dx = 0, dy = 0;
        if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && ableToMove) {
            dx += 1;
            facing = 1;
        }
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && ableToMove) {
            dx -= 1;
            facing = -1;
        }
        else if ((Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")) && ableToMove) {
            dy += 1;
            facing = 0;
        }
        else if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")) && ableToMove) {
            dy -= 1;
            facing = 2;
        }
        
        if(dx == 0 && dy == 0)
            moving = false;
        else
            moving = true;
        for (int i = 0; i < speed; i++) {
            setLocation(getX() + dx, getY());
            cHitBox.setLocation(getX() + dx, getY() + nudgeHitBox);
            cShadow.setLocation(getX() + dx, getY() + nudgeShadow);
            if (checkForNoSpace()) {
                setLocation(getX() - dx, getY());
                cHitBox.setLocation(getX() - dx, getY() + nudgeHitBox);
                cShadow.setLocation(getX() - dx, getY() + nudgeShadow);
            }
            setLocation(getX(), getY() + dy);
            cHitBox.setLocation(getX(), getY() + dy + nudgeHitBox);
            cShadow.setLocation(getX(), getY() + dy + nudgeShadow);
            if (checkForNoSpace()) {
                setLocation(getX(), getY() - dy);
                cHitBox.setLocation(getX(), getY() - dy + nudgeHitBox);
                cShadow.setLocation(getX(), getY() - dy + nudgeShadow);
            }
            
        }
    }
    //check for collisions with the Hit Box
    public boolean checkForNoSpace() {
        return cHitBox.checkForNoSpace();
    }

    //setter / getter
    public void setMoveAbility(boolean move) {
        ableToMove = move;
    }
    
    public void setFacing(int f) {
        facing = f;
    }
    
    public int getFacing() {
        return facing;
    }
    
    //WalkingAnimations
    public void upAnim() {
        if(animTimer < 10) 
            setImage(uImg[1]);
        else if(animTimer < 20) 
            setImage(uImg[0]);
        else if(animTimer < 30)
            setImage(uImg[2]);
        else if(animTimer < 40)
            setImage(uImg[0]);
        else
            animTimer = 0;
        animTimer++;
    }
    
    public void downAnim() {
        if(animTimer < 10) 
            setImage(dImg[1]);
        else if(animTimer < 20) 
            setImage(dImg[0]);
        else if(animTimer < 30)
            setImage(dImg[2]);
        else if(animTimer < 40)
            setImage(dImg[0]);
        else
            animTimer = 0;
        animTimer++;
    }
    
    public void leftAnim() {
        if(animTimer < 10) 
            setImage(lImg[1]);
        else if(animTimer < 20) 
            setImage(lImg[0]);
        else if(animTimer < 30)
            setImage(lImg[2]);
        else if(animTimer < 40)
            setImage(lImg[0]);
        else
            animTimer = 0;
        animTimer++;
    }
    
    public void rightAnim() {
        if(animTimer < 10) 
            setImage(rImg[1]);
        else if(animTimer < 20) 
            setImage(rImg[0]);
        else if(animTimer < 30)
            setImage(rImg[2]);
        else if(animTimer < 40)
            setImage(rImg[0]);
        else
            animTimer = 0;
        animTimer++;
    }
}
