import greenfoot.*;

public class MenuButton extends Actor
{
    
    private boolean selected;
    
    //type = part of the filename to get the right image
    public MenuButton(String type, boolean selected) {
        if(selected) 
            setImage(new GreenfootImage("textures/menu/" + type + "_btn.png"));
        else {
            GreenfootImage img = new GreenfootImage("textures/menu/" + type + "_btn.png");
            img.setTransparency(130);
            setImage(img);
        }
        
        this.selected = selected;
    }  
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        GreenfootImage img = getImage();
        if(selected) {
            img.setTransparency(255);
            setImage(img);
        }else {
            img.setTransparency(130);
            setImage(img);
        }
    }
}
