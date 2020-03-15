import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class SolitaireCard extends Actor
{

    private Num num;    //the number of the card (enum Num)
    private Symbol symbol;  //the symbol of the card (enum Symbol)
    private boolean ableToDrag; //is the card dragable at the moment?
    private boolean show;       //flipped or not / number showing or not

    private SolitairePile pile; //the pile this card is a part of

    private int[] dragOffset = new int[2];  //the offset {x, y} from the mouse while dragging
    private int[] initalCoords = new int[2];    //the coords you took the card from before dragging

    private List<SolitaireCard> tempCardStack;  //the card stack above the dragged card

    //enums for the Num and Symbol of the card
    public enum Num {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    public enum Symbol {CLUBS, SPADES, HEARTS, DIAMONDS};

    public SolitaireCard(Num num, Symbol symbol) {
        this.num = num;
        this.symbol = symbol;
        loadImage();
    }

    //load the image of the card
    public void loadImage() {
        String file = "textures/games/solitaire/cards/";
        if(show) {
            file += num + "_of_";
            file += symbol;
        }else
            file += "redflip";
        file += ".png";
        file = file.toLowerCase();
        setImage(file);
    }

    //manage dragging and double clicking
    public void act() {
        if(!ableToDrag) return; //return when not able to Drag the card

        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(!(mouse != null && mouse.getActor() == this)) return;

        //initial press of the drag on the card
        if(Greenfoot.mousePressed(this)) {
            dragOffset[0] = getX() - mouse.getX();
            dragOffset[1] = getY() - mouse.getY();
            initalCoords[0] = getX();
            initalCoords[1] = getY();

            if(pile != null) {
                tempCardStack = null;
                tempCardStack = pile.getCardsOnTop(this);
                //System.out.println(tempCardStack.size());
                for(SolitaireCard card : tempCardStack)
                    card.setInitialCoords(card.getX(), card.getY());
            }else
                tempCardStack = new ArrayList<SolitaireCard>();
        }

        //card dragging
        if(Greenfoot.mouseDragged(this)) {
            if(tempCardStack != null) {
                setLocation(mouse.getX() + dragOffset[0], mouse.getY() + dragOffset[1]);
                for(int i = 0; i < tempCardStack.size(); i++) {
                    SolitaireCard card = tempCardStack.get(i);
                    card.setLocation(mouse.getX() + dragOffset[0], mouse.getY() + dragOffset[1] + (i+1) * 25);
                }
                reAddToWorld(); //reAdding the card to the world the set the right paint order
                for(SolitaireCard card : tempCardStack) 
                    card.reAddToWorld();   //reAdding the card to the world the set the right paint order
            }
        }
        
        //add to the pile the drag ended on or reset the card to the initial pile
        if(Greenfoot.mouseDragEnded(this)) {
            if(tempCardStack != null) {
                List<SolitairePile> piles = getIntersectingObjects(SolitairePile.class);
                boolean legalMove = false;

                for(SolitairePile pile : piles) {
                    if(!legalMove) {
                        legalMove = pile.addCardToStack(this);
                        if(legalMove) {
                            for(SolitaireCard card : tempCardStack)
                                pile.addCardToStack(card);
                        }
                    }
                }

                if(!legalMove) {
                    for(SolitaireCard card : tempCardStack) 
                        card.setLocation(card.getInitialCoords()[0], card.getInitialCoords()[1]);
                    setLocation(initalCoords[0], initalCoords[1]);
                }
            }
        }
        
        //double clicking on card to try to add it on the right endpile
        if(mouse.getClickCount() == 2 && (pile == null || (!pile.isEndStack() && pile.getTopCard() == this))) {
            List<SolitairePile> piles = SolitaireWorld.getWorld().getObjects(SolitairePile.class);
            boolean added = false;
            
            for(int i = 0; i < piles.size(); i++) {
                if(!added) {
                    if(!piles.get(i).isEndStack()) continue;
                    added = piles.get(i).addCardToStack(this);
                }
            }
            if(added)
                reAddToWorld();   //readding the card to the world the set the right paint order
        }
    }

    //ReAdding the Actor to get the right PaintOrder
    private void reAddToWorld() {
        int x = getX();
        int y = getY();
        SolitaireWorld.getWorld().removeObject(this);
        SolitaireWorld.getWorld().addObject(this, x, y);
    }

    //getter / setter
    
    public void setInitialCoords(int x, int y) {
        initalCoords[0] = x;
        initalCoords[1] = y;
    }

    public int[] getInitialCoords() {
        return initalCoords;
    }

    public void setPile(SolitairePile pile) {
        this.pile = pile;
    }

    public SolitairePile getPile() {
        return pile;
    }

    public void setDrag(boolean drag) {
        ableToDrag = drag;
    }

    public void setShowing(boolean show) {
        this.show = show;
        loadImage();
    }

    public boolean getShowing() {
        return show;
    }

    public Num getNum() {
        return num;
    }

    public Symbol getSymbol() {
        return symbol;
    }

}
