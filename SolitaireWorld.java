import greenfoot.*;
import java.util.Stack;

public class SolitaireWorld extends World
{
    private static SolitaireWorld world;    //world singleton
    
    private SolitaireFullDeck deck;     //the deck the game takes the cards for the piles from and then puts it in the top corner 
    private SolitaireCard openCard;     //the current showing card opened by clicking on the deck
    
    private int characterX, characterY, characterFacing;  //Position of the player interacting with the Solitaire table
    
    public SolitaireWorld(int characterX, int characterY, int characterFacing) {    
        super(800, 700, 1); 
        this.characterX = characterX;
        this.characterY = characterY;
        this.characterFacing = characterFacing;
        
        constructWorld();
    }
    
    public SolitaireWorld() {    
        super(800, 700, 1); 
        constructWorld();
    }
    
    public void constructWorld() {
        GreenfootImage img = new GreenfootImage("textures/games/solitaire/solitaire_background.png");
        img.setColor(new Color(240,240,240));
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString("Drücke ESC um zurück zu kommen", 220, getHeight() - 10);
        setBackground(img);
        
        world = this;
        
        deck = new SolitaireFullDeck();
        addObject(deck, 703, 91);
        
        //piles with the cards
        int c = 1; //cards per pile
        for(int i = 97; i < 704; i += 101) { 
            Stack<SolitaireCard> s = new Stack<SolitaireCard>();
            for(int j = 0; j < c; j++) 
                s.push(deck.pickCard());
            c++;
            addObject(new SolitairePile(s), i, 402);
        }
        
        //end piles for the cards to go
        for(int i = 97; i < 401; i += 101)
            addObject(new SolitairePile(), i, 91);
    }
    
    //escape menu
    public void act() {
        if(Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new CasinoWorld(false, Greenfoot.isKeyDown("escape")));
            if(characterX != 0 && characterY != 0) {
                CasinoWorld.getWorld().getCharacter().setLocation(characterX, characterY);
                CasinoWorld.getWorld().getCharacter().setFacing(characterFacing);
            }else {
                CasinoWorld.getWorld().getCharacter().setLocation(785, 265);
                CasinoWorld.getWorld().getCharacter().setFacing(1);
            }
        }
    }
    
    //getter / setter
    
    public static SolitaireWorld getWorld() {
        return world;
    }
    
    public SolitaireCard getOpenCard() {
        return openCard;
    }
    
    public void setOpenCard(SolitaireCard card) {
        openCard = card;
    }
}
