import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LForm extends Forms
{
    public LForm() {
        super(1);
    }

    protected void addedToWorld(World world) {
        direction = generateDirection();
        int start = genStartX();
        world.addObject(pieceArr[0], start + 2, 0);
        world.addObject(pieceArr[1], start + 2, 1);
        world.addObject(pieceArr[2], start + 1, 1);
        world.addObject(pieceArr[3], start, 1);
        setDirection();
    }
    //used to set a new Direction and rotate all Pieces in the Form
    protected void setDirection() {
        switch (direction) {
            case 0:
                pieceArr[0].setLocation(pieceArr[2].getX() + 1, pieceArr[2].getY() + 1);
                pieceArr[1].setLocation(pieceArr[2].getX(), pieceArr[2].getY() + 1);
                pieceArr[3].setLocation(pieceArr[2].getX(), pieceArr[2].getY() - 1);
                break;
            case 1:
                pieceArr[0].setLocation(pieceArr[2].getX() + 1, pieceArr[2].getY() - 1);
                pieceArr[1].setLocation(pieceArr[2].getX() + 1, pieceArr[2].getY());
                pieceArr[3].setLocation(pieceArr[2].getX() - 1, pieceArr[2].getY());
                break;
            case 2:
                pieceArr[0].setLocation(pieceArr[2].getX() - 1, pieceArr[2].getY() - 1);
                pieceArr[1].setLocation(pieceArr[2].getX(), pieceArr[2].getY() - 1);
                pieceArr[3].setLocation(pieceArr[2].getX(), pieceArr[2].getY() + 1);
                break;
            case 3:
                pieceArr[0].setLocation(pieceArr[2].getX() - 1, pieceArr[2].getY() + 1);
                pieceArr[1].setLocation(pieceArr[2].getX() - 1, pieceArr[2].getY());
                pieceArr[3].setLocation(pieceArr[2].getX() + 1, pieceArr[2].getY());
                break;
        }
    }
    //returns the leftmost Piece
    protected Piece links() {
       switch (direction) {
            case 0:
                return pieceArr[2];
            case 1:
                return pieceArr[3];
            case 2:
                return pieceArr[0];
            default:
                return pieceArr[0];
        }
    }
    //returns the rightmost Piece
    protected Piece rechts() {
        switch (direction) {
            case 0:
                return pieceArr[0];
            case 1:
                return pieceArr[0];
            case 2:
                return pieceArr[1];
            default:
                return pieceArr[3];
        }
    }
    //generates a random x value for the spawn location
    protected int genStartX() {
        return (int)(Math.random() * (TetrisWorld.getWorld().getWidth() -2));
    }
    //checks if its possible to turn
    protected boolean turnPossible() {
        switch(direction) {
            case 0:
                return pieceArr[2].getX() >= 1;
            case 1:
                return pieceArr[2].getY() < TetrisWorld.getWorld().getHeight() - 3;
            case 2:
                return pieceArr[2].getX() <= TetrisWorld.getWorld().getWidth() - 1;
            default: 
                return true;
        }
    }
}
