import greenfoot.*;

//coin input display in the slotmachine

public class InputDisplay extends Actor
{
    private boolean selected;   //currently selected
    private int input;          //coin input
    
    public InputDisplay(int input, boolean selected) {
        GreenfootImage img = new GreenfootImage(50, 50);
        img.setColor(Color.GRAY);
        img.fill();
        if(selected) {
            img.setColor(new Color(226, 210, 116));
            img.fill();
            img.setColor(Color.RED);
            img.drawRect(0, 0, 49, 49);
        }
        img.setColor(Color.BLACK);
        img.drawString(input + "", 20, 30);
        setImage(img);
        
        this.input = input;
        this.selected = selected;
    }
    
    public void act() {
        //select if its not selected and the machine is currently not running
        if(Greenfoot.mouseClicked(this) && !selected && !SlotWorld.getWorld().isRunning()) {
            select();
        }
    }
    //select the input amount
    public void select() {
        SlotWorld.getWorld().notSelected();
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(new Color(226, 210, 116));
        img.fill();
        img.setColor(Color.RED);
        img.drawRect(0, 0, 49, 49);
        img.drawString(input + "", 20, 30);
        setImage(img);
        
        SlotWorld.getWorld().setCoinInput(input);
        selected = true;
    }
    //deselect the input amount
    public void notSelected() {
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.GRAY);
        img.fill();
        img.setColor(Color.BLACK);
        img.drawString(input + "", 20, 30);
        setImage(img);
        
        selected = false;
    }
}
