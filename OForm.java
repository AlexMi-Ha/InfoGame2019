import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class OForm extends Forms
{
    public OForm() {
        super(5);
    }
    
    protected void addedToWorld(World world) {
        direction = 0;
        int start = genStartX();
        world.addObject(pieceArr[0], start, 0);
        world.addObject(pieceArr[1], start + 1, 0);
        world.addObject(pieceArr[2], start, 1);
        world.addObject(pieceArr[3], start + 1, 1);
    }
    //used to rotate   not used by a SquareForm
    protected void setDirection() {}
    //checks if its possible to turn   false on a SquareForm
    protected boolean turnPossible() {
        return false;
    }
    //returns the leftmost piece
    protected Piece links() {
        return pieceArr[0];
    }
    //returns the rightmost Piece
    protected Piece rechts() {
        return pieceArr[1];
    }
    
    protected int genStartX() {
        return (int) (Math.random() * (TetrisWorld.getWorld().getWidth() - 1));
    }
}
