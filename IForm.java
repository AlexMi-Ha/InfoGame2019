import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class IForm extends Forms
{
    public IForm() {
        super(6);
    }  

    protected void addedToWorld(World world) {
        direction = generateDirection();
        int start = genStartX();
        for(int i = 0; i < 4; i++) {
            world.addObject(pieceArr[i], start + i, 2);
        }
        setDirection();
    }
    //used to rotate all Pieces in the Form
    protected void setDirection() {
        switch(direction) {
            case 0:
            case 2:
                for (int i = 0; i < 4; i++) {
                    if(i == 1)
                        continue;
                    pieceArr[i].setLocation(pieceArr[1].getX(), pieceArr[1].getY() + 1 -i);
                }
                break;
            default:
                for(int i = 0; i < 4; i++) {
                    if(i == 1) 
                        continue;
                    pieceArr[i].setLocation(pieceArr[1].getX() - 1 + i, pieceArr[1].getY());
                }
                break;
        }
    }
    //returns the leftmost Piece
    protected Piece links() {
        return pieceArr[0];
    }
    //returns the rightmost Piece
    protected Piece rechts() {
        return pieceArr[3];
    }
    //checks if a turn is possible
    protected boolean turnPossible() {
        switch (direction) {
            case 0:
            case 2:
                return pieceArr[0].getX() >= 1
                && pieceArr[3].getX() <= TetrisWorld.getWorld().getWidth() - 3;
            default: 
                return pieceArr[0].getY() < TetrisWorld.getWorld().getHeight() - 3;
        }
    }
    //generates a random x value to start
    protected int genStartX() {
        return (int) (Math.random() * (TetrisWorld.getWorld().getWidth() - 3));
    }
}
