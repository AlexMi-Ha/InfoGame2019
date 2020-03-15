import greenfoot.*;

public class Splash extends Actor
{
    private GreenfootImage[] images;
    private int n = 0;
    
    public Splash() {
        images = new GreenfootImage[4];
        images[0] = new GreenfootImage("textures/logos/alexmiha_logo_full.png");
        images[1] = new GreenfootImage("textures/logos/thetek_logo_full.png");
        images[2] = new GreenfootImage("textures/logos/knuddel_logo_full.png");
        images[3] = new GreenfootImage("textures/logos/victor_logo_full.png");
        
        for(GreenfootImage img : images) 
            img.setTransparency(0);

        GreenfootImage img = new GreenfootImage(20, 20);
        img = images[0];
        setImage(img);
    }

    public void act() 
    {
        int t = getImage().getTransparency() + 2;
        
        //skipping
        if(Greenfoot.isKeyDown("enter")) {
            t = 247;
        }
        
        if(t >= 250) {
            n++;
            if(n >= images.length) {
                openGame();
                return;
            }
            newImage(n);
            return;
        }
        GreenfootImage img = getImage();
        img.setTransparency(t);
        setImage(img);
        
        
    }    
    
    public void openGame() {
        Greenfoot.setWorld(new MainMenu(new CasinoWorld(false, Greenfoot.isKeyDown("escape")), true, Greenfoot.isKeyDown("enter")));
    }

    public void newImage(int n) {
        setImage(images[n]);
    } 
}
