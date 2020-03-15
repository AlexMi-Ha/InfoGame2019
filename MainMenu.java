import greenfoot.*;

public class MainMenu extends World
{
    private boolean keyPressed = false;     //if a button is currently pressed(prevents spamming)
    private boolean keysLocked = false;     //if the key input is locked
    
    private MenuButton btn_play, btn_controls, btn_credits, btn_exit;
    
    private static MainMenu world;
    
    private World worldToEnter; //the world to go after pressing play

    public MainMenu(World worldToEnter, boolean resetSounds, boolean keyPressed)
    {    
        super(1000, 527, 1, false); 
        world = this;
        
        this.keyPressed = keyPressed;
        this.worldToEnter = worldToEnter;
        
        setBackground(new GreenfootImage("textures/menu/menu.png"));

        btn_play = new MenuButton("play", true);
        btn_controls = new MenuButton("controls", false);
        btn_credits = new MenuButton("credits", false);
        btn_exit = new MenuButton("exit", false);
        addObject(btn_play, getWidth() / 2 + 1, 267);
        addObject(btn_controls, getWidth() / 2, 317);
        addObject(btn_credits, getWidth() / 2 + 1, 367);
        addObject(btn_exit, getWidth() / 2 , 417);
        
        if(resetSounds) {
            CasinoWorld.resetSounds();
            if(!AudioManager.isPlaying("soundtrack/knddl_casino.mp3"))
                AudioManager.addPlaying("soundtrack/knddl_casino.mp3");
        }
    }

    //Manage key input for the Buttons
    public void act() {
        //scrolling
        if(Greenfoot.isKeyDown("up")) {
            if(!keyPressed && !keysLocked) {
                if(btn_play.isSelected()) {
                    btn_play.setSelected(false);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(true);
                }else if(btn_controls.isSelected()){
                    btn_play.setSelected(true);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(false);
                }else if(btn_credits.isSelected()){
                    btn_play.setSelected(false);
                    btn_controls.setSelected(true);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(false);
                }else {
                   btn_play.setSelected(false);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(true);
                    btn_exit.setSelected(false); 
                }
                keyPressed = true;
            }
        }else if(Greenfoot.isKeyDown("down")) {
            if(!keyPressed && !keysLocked) {
                if(btn_play.isSelected()) {
                    btn_play.setSelected(false);
                    btn_controls.setSelected(true);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(false);
                }else if(btn_controls.isSelected()){
                    btn_play.setSelected(false);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(true);
                    btn_exit.setSelected(false);
                }else if(btn_credits.isSelected()){
                    btn_play.setSelected(false);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(true);
                }else {
                    btn_play.setSelected(true);
                    btn_controls.setSelected(false);
                    btn_credits.setSelected(false);
                    btn_exit.setSelected(false);
                }
                keyPressed = true;
            }
            //activating
        }else if(Greenfoot.isKeyDown("enter")) {
            if(!keyPressed && !keysLocked) {
                if(btn_play.isSelected()) {
                    CasinoWorld.setSplash(false);
                    Greenfoot.setWorld(worldToEnter);
                }else if(btn_controls.isSelected()) {
                    addObject(new ControlPanel(), getWidth() / 2, getHeight() / 2);
                    keysLocked = true;
                }else if(btn_credits.isSelected()) {
                    addObject(new CreditMenu(1, null), getWidth() / 2, getHeight() / 2);
                    keysLocked = true;
                }else if(btn_exit.isSelected())
                    System.exit(0);
                keyPressed = true;
            }
        }else
            keyPressed = false;
    }
    
    //getter / setter
    public void setKeyLock(boolean keysLocked) {
        this.keysLocked = keysLocked;
    } 
    
    public static MainMenu getWorld() {
        return world;
    }
}
