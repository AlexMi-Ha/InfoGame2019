import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class SolitairePile extends Actor
{
    private Stack<SolitaireCard> cards; //cards on this pile

    private boolean endStack = false;   //if this pile is a pile for the final stack of cards
    private SolitaireCard topEndCard;   //the top card of this endStack

    //constructor for a normal pile of cards
    public SolitairePile(Stack<SolitaireCard> cards) {
        GreenfootImage img = new GreenfootImage(90, 390);
        setImage(img);

        this.cards = cards;
    }

    //constructor for a end pile of cards
    public SolitairePile() {
        GreenfootImage img = new GreenfootImage(90, 120);
        setImage(img);

        endStack = true;
        cards = new Stack<SolitaireCard>();
    }

    //add all the cards to the world
    public void addedToWorld(World world) {
        for(int i = 0; i < cards.size(); i++) {
            SolitaireCard card = cards.get(i);
            if(i == cards.size() - 1) {
                card.setShowing(true);
                card.setDrag(true);
            }else {
                card.setShowing(false);
                card.setDrag(false);
            }
            card.setPile(this);
            SolitaireWorld.getWorld().addObject(card, getX(), getY() + i * 10);
        }
    }

    //get all the Cards on top of another card
    //returns null if there is no Card on top
    public List<SolitaireCard> getCardsOnTop(SolitaireCard card) {
        if(!cards.contains(card)) return null;
        List <SolitaireCard> cardsOnTop = new ArrayList<SolitaireCard>();

        for(int i = cards.indexOf(card) + 1; i < cards.size(); i++) 
            cardsOnTop.add(cards.get(i));
        return cardsOnTop;
    }

    //add a card to the pile
    //just executed after knowing the card fits on the pile
    private void addCard(SolitaireCard card) {
        if(!endStack) {
            if(card.getPile() != null)
                card.getPile().removeTop();

            card.setPile(this);
            card.setLocation(getX(), getY() + calcOffset());
            cards.push(card);
            card.setDrag(true);
        }else {
            if(card.getPile() != null) {
                card.getPile().removeTop();
                card.setPile(this);
            }
            card.setPile(this);
            topEndCard = card;
            card.setLocation(getX(), getY());
            card.setDrag(false);
        }
    }
    
    //Try to add a Card to the pile
    public boolean addCardToStack(SolitaireCard card) {
        if(!endStack) {
            if(cards.size() == 0 && card.getNum() == SolitaireCard.Num.KING) {
                addCard(card);
                return true;
            }
            if(cards.size() == 0) return false;

            SolitaireCard topC = cards.peek();
            if(topC.getSymbol() == SolitaireCard.Symbol.DIAMONDS || topC.getSymbol() == SolitaireCard.Symbol.HEARTS) 
                if(card.getSymbol() == SolitaireCard.Symbol.DIAMONDS || card.getSymbol() == SolitaireCard.Symbol.HEARTS)
                    return false;

            if(topC.getSymbol() == SolitaireCard.Symbol.SPADES || topC.getSymbol() == SolitaireCard.Symbol.CLUBS) 
                if(card.getSymbol() == SolitaireCard.Symbol.SPADES || card.getSymbol() == SolitaireCard.Symbol.CLUBS)
                    return false;

            if(topC.getNum().ordinal() - 1 == card.getNum().ordinal()) {
                addCard(card);
                return true;
            }
            return false;
            
        }else {
            
            if(topEndCard == null && card.getNum() == SolitaireCard.Num.ACE) {
                addCard(card);
                return true;
            }
            if(topEndCard == null) return false;
            
            if(card.getSymbol() == topEndCard.getSymbol() && topEndCard.getNum().ordinal() + 1 == card.getNum().ordinal()) {
                addCard(card);
                return true;
            }
            return false;
        }
    }

    //Remove the top Card of the Pile
    public void removeTop() {
        cards.pop();
        if(cards.size() > 0) {
            cards.peek().setShowing(true);
            cards.peek().setDrag(true);
        }
    }
    
    //if the endStack is Full
    public boolean isFull() {
        if(topEndCard == null) return false;
        return topEndCard.getNum() == SolitaireCard.Num.KING;
    }

    //calc the offset for a card from the coords of the Pile
    public int calcOffset() {
        int o = 0;
        for(SolitaireCard card : cards) {
            if(card.getShowing())
                o += 30;
            else
                o += 10;
        }
        return o;
    }
    
    //getter / setter
    
    public boolean isEndStack() {
        return endStack;
    }
    
    public SolitaireCard getTopCard() {
        return cards.peek();
    }
    
    //get normalized Y
    public int getY() {
        int y = super.getY();
        y -= getImage().getHeight() / 2 - 120 / 2;
        return y;
    }
}
