import greenfoot.*;
import java.util.Collections;
import java.util.ArrayList;

public class SolitaireFullDeck extends Actor
{
    
    private ArrayList<SolitaireCard> deckCards; //all cards of the deck
    
    public SolitaireFullDeck() {
        setImage(new GreenfootImage("textures/games/solitaire/cards/redflip.png"));
        
        fillDeck();
        shuffleDeck();
    }  
    
    //manage the clicking on the card deck to go through all remaining cards
    public void act() {
        if(Greenfoot.mouseClicked(this) && deckCards.size() > 0) {
            if(SolitaireWorld.getWorld().getOpenCard() != null && SolitaireWorld.getWorld().getOpenCard().getPile() == null) {
                SolitaireWorld.getWorld().removeObject(SolitaireWorld.getWorld().getOpenCard());
                deckCards.add(SolitaireWorld.getWorld().getOpenCard());
            }
            SolitaireWorld.getWorld().setOpenCard(pickCard());
            SolitaireWorld.getWorld().addObject(SolitaireWorld.getWorld().getOpenCard(), 602, 91);
            SolitaireWorld.getWorld().getOpenCard().setDrag(true);
            SolitaireWorld.getWorld().getOpenCard().setShowing(true);
        }
    }
    
    //fill the deck ArrayList with a new Deck of cards (1x Hearts, 1x Diamonds, 1x Clubs, 1x Spades)
    public void fillDeck() {
        deckCards = new ArrayList<SolitaireCard>();
        for(SolitaireCard.Symbol symbol : SolitaireCard.Symbol.values()) {
            for(SolitaireCard.Num num : SolitaireCard.Num.values())
                deckCards.add(new SolitaireCard(num, symbol));
        }
    }
    
    //shuffle all cards in the deck
    public void shuffleDeck() {
        Collections.shuffle(deckCards);
    }
    
    //add a card to the last slot of the array
    public void addCardAtTheBottom(SolitaireCard card) {
        deckCards.add(card);
    } 
    
    //pick the first card in the array
    public SolitaireCard pickCard() {
        if(deckCards.size() == 0) return null;
        
        SolitaireCard card = deckCards.get(0);
        deckCards.remove(0);
        if(deckCards.size() == 0) {
            GreenfootImage img = new GreenfootImage(90, 120);
            setImage(img);
        }
        return card;
    }
}
