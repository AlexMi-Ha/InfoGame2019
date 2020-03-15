import greenfoot.*;

//roll button of the slotmachine

public class SlotButton extends Actor
{
    private boolean update;     //update the machine state
    
    public SlotButton() {
        GreenfootImage img = new GreenfootImage(61,61);
        img.setColor(new Color(119, 191, 93));
        img.fill();
        img.setColor(Color.BLACK);
        img.setFont(img.getFont().deriveFont(16));
        img.drawString("Roll", 17, 35);
        setImage(img);
        
        update = false;
    }

    public void act() 
    {
        //start the machine if its not already running and the user has enough coins
        if((Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("space")) && !SlotWorld.getWorld().isRunning()) {
            if(SlotWorld.getWorld().getDisplay().getScore() - SlotWorld.getWorld().getCoinInput() >= 0) {
                SlotWorld.getWorld().roll();
            
                GreenfootImage img = getImage();
                img.setColor(new Color(206, 86, 86));
                img.fill();
                setImage(img);
            
                SlotWorld.getWorld().getDisplay().removeCoins(SlotWorld.getWorld().getCoinInput());
                update = true;
            }
        }
        //stop the machine when its finished
        if(!SlotWorld.getWorld().getSlot1().Running() 
        && !SlotWorld.getWorld().getSlot2().Running()
        && !SlotWorld.getWorld().getSlot3().Running() && update)
            stopRunning();
    }
    //stop & reset the machine
    public void stopRunning() {
        GreenfootImage img = getImage();
        img.setColor(new Color(119, 191, 93));
        img.fill();
        img.setColor(Color.BLACK);
        img.setFont(img.getFont().deriveFont(16));
        img.drawString("Roll", 17, 35);
        setImage(img);
        
        SlotWorld.getWorld().setRunning(false);
        SlotWorld.getWorld().manageScore();
        update = false;
    }
}
