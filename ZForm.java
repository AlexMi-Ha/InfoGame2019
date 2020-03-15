import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ZForm extends Forms
{
    public ZForm() {
        super(7);
    } 
    
    protected void addedToWorld(World world) {
        direction = generateDirection();
        int start = genStartX();
        world.addObject(pieceArr[0], start, 1);
        world.addObject(pieceArr[1], start + 1, 1);
        world.addObject(pieceArr[2], start + 1, 2);
        world.addObject(pieceArr[3], start + 2, 2);
        setDirection();
    }
    //used to set a new Direction and rotate all Pieces of the Form
    protected void setDirection() {
        switch (direction) {
            case 0:
            case 2:
                pieceArr[0].setLocation(pieceArr[1].getX() - 1, pieceArr[1].getY());
                pieceArr[2].setLocation(pieceArr[1].getX(), pieceArr[1].getY() + 1);
                pieceArr[3].setLocation(pieceArr[1].getX() + 1, pieceArr[1].getY() + 1);
                break;
            default:
                pieceArr[0].setLocation(pieceArr[1].getX(), pieceArr[1].getY() - 1);
                pieceArr[2].setLocation(pieceArr[1].getX() - 1, pieceArr[1].getY());
                pieceArr[3].setLocation(pieceArr[1].getX() - 1, pieceArr[1].getY() + 1);
                break;
        }
    }
    //returns the leftmost Piece
    protected Piece links() {
        switch (direction) {
            case 0:
            case 2:
                return pieceArr[0];
            default:
                return pieceArr[2];
        }
    }
    //returns the rightmost Piece
    protected Piece rechts() {
        switch (direction) {
            case 0:
            case 2:
                return pieceArr[3];
            default:
                return pieceArr[1];
        }
    }
    //checks if its possible to turn
    protected boolean turnPossible() {
        switch (direction) {
            case 0:
            case 2:
                return pieceArr[2].getY() < TetrisWorld.getWorld().getHeight() - 3;
            default:
                return pieceArr[1].getX() < TetrisWorld.getWorld().getWidth() - 1;
        }
    }
    //generates a random x vlaue for the Spawn location
    protected int genStartX() {
        return (int) (Math.random() * (TetrisWorld.getWorld().getWidth() - 2));
    }
}
