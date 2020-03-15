import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//table object in the lobby 

public class Table extends Actor
{
    private int type;
    
    public Table(int type) {
        if(type == 0) {
            GreenfootImage img = new GreenfootImage("textures/environment/billard.png");
            setImage(img);
        }else if(type == 1) {
            GreenfootImage img = new GreenfootImage("textures/environment/solitaire_table.png");
            setImage(img);
        }else {
            GreenfootImage img = new GreenfootImage(80, 80);
            img.setColor(Color.MAGENTA);
            img.fill();
            setImage(img);
        }
        this.type = type;
    } 
    
    public void act() {
        if(type == 0) {
            Character c = CasinoWorld.getWorld().getCharacter();
            if(c.getX() > getX() - getImage().getWidth() / 2 - 27 
                && c.getX() < getX() + getImage().getWidth() / 2 + 27) {
                 if(c.getY() > getY() - getImage().getHeight() / 2 - 41 
                 && c.getY() < getY() + getImage().getHeight() / 2 + 27) {
                     if(Greenfoot.isKeyDown("e"))
                        loadGame(0);
                 }
            }
        }else if(type == 1) {
            Character c = CasinoWorld.getWorld().getCharacter();
            if(c.getX() > getX() - getImage().getWidth() / 2 - 27 
                && c.getX() < getX() + getImage().getWidth() / 2 + 27) {
                 if(c.getY() > getY() - getImage().getHeight() / 2 - 41 
                 && c.getY() < getY() + getImage().getHeight() / 2 + 27) {
                     if(Greenfoot.isKeyDown("e"))
                        loadGame(1);
                 }
            }
        }
    }
    
    public void loadGame(int t) {
        if(t == 0) {
            //System.out.println("Billard");
            Greenfoot.setWorld(new PoolWorld(CasinoWorld.getWorld().getCharacter().getX(), CasinoWorld.getWorld().getCharacter().getY(), CasinoWorld.getWorld().getCharacter().getFacing()));
        }else if(t == 1) {
            //System.out.println("Solitaire");
            Greenfoot.setWorld(new SolitaireWorld(CasinoWorld.getWorld().getCharacter().getX(), CasinoWorld.getWorld().getCharacter().getY(), CasinoWorld.getWorld().getCharacter().getFacing()));
        }
    }
    
}
