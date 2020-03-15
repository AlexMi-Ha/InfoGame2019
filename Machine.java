import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

//game machine in the main lobby
public class Machine extends Actor
{
    //1: pong , 2: Tetris , 3: Snake , 4: FlappyBird , 5: SpaceInvader ,
    //6: Asteroids   , 7-10 slotmachine
    private int type;                   //texture type and game
    private boolean opened = false;     //currently opened

    public Machine(int nr) {
        GreenfootImage img;
        if(nr == 3)
            img = new GreenfootImage("images/textures/arcade/machines/machine1.png");
        else if(nr == 4)
            img = new GreenfootImage("images/textures/arcade/machines/machine2.png");
        else if(nr == 5)
            img = new GreenfootImage("images/textures/arcade/machines/machine3.png");
        else if(nr == 6)
            img = new GreenfootImage("images/textures/arcade/machines/machine4.png");
        else if(nr == 1)
            img = new GreenfootImage("images/textures/arcade/machines/side1.png");
        else if(nr == 2)
            img = new GreenfootImage("images/textures/arcade/machines/side2.png");
        else if(nr == 7) 
            img = new GreenfootImage("images/textures/arcade/machines/machine2.png");
        else if(nr == 8) 
            img = new GreenfootImage("images/textures/arcade/machines/machine4.png");
        else if(nr == 9)
            img = new GreenfootImage("images/textures/arcade/machines/machine1.png");
        else if(nr == 10) 
            img = new GreenfootImage("images/textures/arcade/machines/machine2.png");
        else if(nr == 11)
            img = new GreenfootImage("images/textures/arcade/machines/machine3.png");
        else if(nr == 12)
            img = new GreenfootImage("images/textures/arcade/machines/machine4.png");
        else{
            img = new GreenfootImage(40, 40);
            img.setColor(Color.CYAN);
            img.fill();
            img.setColor(Color.BLACK);
            img.drawString(nr + "", 20, 20);
        }
        img.scale(33, 66);
        setImage(img);
        type = nr;
    } 

    //manage interactions
    public void act() {
        if(type == 1 || type == 2) {
            if (getOneObjectAtOffset(15, 0, Character.class) != null && Greenfoot.isKeyDown("e")) 
                openGame();
        }else if(type == 3 || type == 4 || type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11 || type == 12) {
            if (getOneObjectAtOffset(0, 28, Character.class) != null && Greenfoot.isKeyDown("e"))
                openGame();
        }
    }

    //open a game
    //1: Pong , 2: Tetris , 3: Snake , 4: FlappyBird , 5: SpaceInvader ,
    //6: Asteroids , 7-10 slotmachine, 11 frogger, 12Nannoid
    public void openGame() {
        if(!opened) {
            if(!(type >= 7 && type <=10) && type != 1 && type != 12)
                AudioManager.stopPlaying("soundtrack/knddl_casino.mp3");
            if(type == 1 || type == 12)
                AudioManager.setVolume("soundtrack/knddl_casino.mp3", 30);
            switch(type) {
                case 1: //Pong
                    //System.out.println("Pong");
                    Greenfoot.setWorld(new LoadMenu(new PongWorld(), 1, Greenfoot.isKeyDown("escape")));
                    break;
                case 2: //Tetris
                    //System.out.println("Tetris");
                    Greenfoot.setWorld(new LoadMenu(new TetrisWorld(), 2, Greenfoot.isKeyDown("escape")));
                    break;
                case 3: //Snake
                    //System.out.println("Snake");
                    Greenfoot.setWorld(new LoadMenu(new SnakeWorld(), 3, Greenfoot.isKeyDown("escape")));
                    break;
                case 4: //Flappy
                    //System.out.println("Flappy");
                    Greenfoot.setWorld(new LoadMenu(new FlappyBirdWorld(), 4, Greenfoot.isKeyDown("escape")));
                    break;
                case 5: //SpaceInvader
                    //System.out.println("SpaceInvader");
                    Greenfoot.setWorld(new LoadMenu(new SpaceWorld(), 5, Greenfoot.isKeyDown("escape")));
                    break;
                case 6: //Asteroids
                    //System.out.println("Asteroids");
                    Greenfoot.setWorld(new LoadMenu(new AsteroidWorld(), 6, Greenfoot.isKeyDown("escape")));
                    break;
                case 11: //Frogger
                    //System.out.println("Frogger");
                    Greenfoot.setWorld(new LoadMenu(new FroggerWorld(), 11, Greenfoot.isKeyDown("escape")));
                    break;
                case 12: //Nannoid
                    //System.out.println("Nannoid");
                    Greenfoot.setWorld(new LoadMenu(new NannoidWorld(), 12, Greenfoot.isKeyDown("escape")));
                    break;    
                default: //Slotmachines
                    //System.out.println("SlotMachine");
                    Greenfoot.setWorld(new SlotWorld(CasinoWorld.getWorld().getCharacter().getX(), CasinoWorld.getWorld().getCharacter().getY(), CasinoWorld.getWorld().getCharacter().getFacing()));
                    break;
            }
            opened = true;
        }
    }

    //getter
    public int getType() {
        return type;
    }
}
