import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

//credit board in the main lobby

public class CreditBoard extends Actor
{
    private static boolean menuOpen = false;	//menu open?
    
    public CreditBoard() {
        setImage(new GreenfootImage("images/textures/environment/bulletin.png"));
    }
    //manage Interactions
    public void act() {
        List<Character> objects = getObjectsInRange(80,Character.class);
        if (objects.size() > 0) {
            if(Greenfoot.isKeyDown("e")) 
                openMenu();
        } 
    }
    //open the menu
    public void openMenu() {
        if(!menuOpen)
            CasinoWorld.getWorld().addObject(new CreditMenu(0, this),
            CasinoWorld.getWorld().getWidth()/2, CasinoWorld.getWorld().getHeight()/2);
        menuOpen = true;
        CasinoWorld.getWorld().getCharacter().setMoveAbility(false);
        CasinoWorld.getWorld().setEscapeLock(true);
    }
    
	
	//setter
	
    public void setOpen(boolean arg) {
        menuOpen = arg;
    }
    
}
