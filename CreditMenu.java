import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//credit menu of the main lobby

public class CreditMenu extends Actor
{
    private CreditBoard board;
    private int type;

    public CreditMenu(int type, CreditBoard board) {
        GreenfootImage img;
        if(type == 0) {
            this.board = board;
            img = new GreenfootImage("textures/environment/credits.png");
            img.scale(1095, 527);
        }else 
            img = new GreenfootImage("textures/menu/credits.png");
        this.type = type;
        
        
        setImage(img);
    }
    //manage Interactions
    public void act() {
        
        if(Greenfoot.isKeyDown("escape")) {
            if(type == 0) {
                CasinoWorld.getWorld().removeObject(this);
                board.setOpen(false);
                CasinoWorld.getWorld().getCharacter().setMoveAbility(true);
                CasinoWorld.getWorld().setEscapeLock(false);
            }else {
                MainMenu.getWorld().setKeyLock(false);
                MainMenu.getWorld().removeObject(this);
            }
        }
        
    }
}
