import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

//Forms of Tetris

public abstract class Forms extends Actor
{
    protected int direction;
    protected boolean dead;
    
    //KeyPressCount prevents a bug from Button Spamming
    protected int count;

    protected Piece[] pieceArr;

    //Spawns a new Form with a color
    //color:  1:red  2:orange  3:yellow  4:green  5:blue  6:magenta   7:pink
    public Forms(int color) {
        pieceArr = new Piece[4];
        for (int i = 0; i<4; i++) 
            pieceArr[i] = new Piece(color);
        
        count = 0;
        dead = false;
        
        TetrisWorld.getWorld().addObject(this, 0, TetrisWorld.getWorld().getHeight() - 1);
    }

    //Abstract methods for all Forms
    abstract protected void setDirection();

    abstract protected Piece links();

    abstract protected Piece rechts();
    
    abstract protected boolean turnPossible();

    //Deletes the Form
    //executed at GameOver
    public void delete() {
        for(int i = 0; i<4; i++) {
            TetrisWorld.getWorld().removeObject(pieceArr[i]);
        }
        dead = true;
    }

    //move left
    //returns true after completing or returns false if it is not possible 
    public boolean left() {
        if(leftNotPossible())
            return false;
        for(int i = 0; i<4; i++) 
            pieceArr[i].setLocation(pieceArr[i].getX() - 1, pieceArr[i].getY());
        return true;
    }
    //move right
    //returns true after completing or returns false if it is not possible 
    public boolean right() {
        if(rightNotPossible())
            return false;
        for(int i = 0; i<4; i++) 
            pieceArr[i].setLocation(pieceArr[i].getX() + 1, pieceArr[i].getY());
        return true;
    }
    //checks for each Piece if its not possible to move left
    public boolean leftNotPossible() {
        if(links().getX() == 0)
            return true;
        Pieces: for(int i = 0; i<4; i++) {
            List list = TetrisWorld.getWorld().getObjectsAt(pieceArr[i].getX() -1,
            pieceArr[i].getY(), Piece.class);

            if(list.size() == 0) 
                continue;
            
            for(int j = 0; j<4; j++) {
                if(i == j)
                    continue;
                if(list.get(0) == pieceArr[j])
                    continue Pieces;
            }
            return true;
        }
        return false;
    }
    //checks for each Piece if its not possible to move right
    public boolean rightNotPossible() {
        if(rechts().getX() == TetrisWorld.getWorld().getWidth() - 1)
            return true;
        Pieces: for(int i = 0; i<4; i++) {
            List list = TetrisWorld.getWorld().getObjectsAt(pieceArr[i].getX() +1,
            pieceArr[i].getY(), Piece.class);

            if(list.size() == 0) 
                continue;
            
            for(int j = 0; j<4; j++) {
                if(i == j)
                    continue;
                if(list.get(0) == pieceArr[j])
                    continue Pieces;
            }
            return true;
        }
        return false;
    }
    //trys to turn the piece left
    //returns true on completion  returns false if its not possible to turn
    public boolean turn() {
        if(!turnPossible()) 
            return false;
        
        int oldDirection = direction;
        direction = (direction + 1) % 4;
        setDirection();
        //not possible to turn if there is a piece in the way
        for(int i = 0; i<4; i++) {
            List list = TetrisWorld.getWorld().getObjectsAt(pieceArr[i].getX(),
                    pieceArr[i].getY(), null);

            if(list.size() > 1) {
                direction = oldDirection;
                setDirection();
                return false;
            }
        }
        return true;
    }
    //moves the Form one cell Down
    //returns true one completion   returns false if its not possible to move down
    public boolean oneDown() {
        for(int i = 0; i<4; i++) {
            if(!blockFree(i)) {
                checkRow();
                stop();
                return false;
            }
        }
        for(int i = 0; i < 4; i++) 
            pieceArr[i].setLocation(pieceArr[i].getX(), pieceArr[i].getY() + 1);
        
        return true;
    }
    //repeats moving down until its at the bottom or on another form
    public void down() {
        while(oneDown())
            ;
    }
    //checks if the cell under a piece is empty 
    //executed when moving down
    public boolean blockFree(int a) {
        List list = TetrisWorld.getWorld().getObjectsAt(pieceArr[a].getX(),
                pieceArr[a].getY() + 1,null);
        if(list.size() == 0)
            return true;

        for(int i = 0; i< 4; i++) {
            if(i == a)
                continue;
            if(list.get(0) == pieceArr[i])
                return true;
        }
        return false;
    }
    //searches for full rows
    //executed when the form is at the bottom or on another form
    public void checkRow() {
        int numOfRows = 0;
        rows: for(int row = TetrisWorld.getWorld().getHeight() -3; row >=0; row--) {
            cols: for(int col = 0; col < TetrisWorld.getWorld().getWidth(); col++) {
                List list = TetrisWorld.getWorld().getObjectsAt(col, row, Piece.class);
                if(list.size() == 0)
                    continue rows;
            }
            clearRow(row);
            numOfRows++;
            slide(row);
            row++;
        }
        if(numOfRows > 0)
            TetrisWorld.getWorld().points(numOfRows);
    }
    //moving the remaining things of the rows down
    //executed after checkRow() == true
    public void slide(int row) {
        for (int r = row - 1; r >= 0; r--) {
            for (int col = 0; col < TetrisWorld.getWorld().getWidth(); col++) {
                List list = TetrisWorld.getWorld().getObjectsAt(
                        col, r, Piece.class);
                if (list.size() > 0) {
                    Piece piece = (Piece) list.get(0);
                    piece.setLocation(piece.getX(),
                        piece.getY() + 1);
                }
            }
        }
    }
    //clearing a Row
    //executed after checkRow() == true
    public void clearRow(int row) {
        for(int col = 0; col < TetrisWorld.getWorld().getWidth(); col++) {
            TetrisWorld.getWorld().removeObjects(TetrisWorld.getWorld().getObjectsAt(
                    col, row, Piece.class));
        }
    }
    //deletes the current Forms object, generates a new Form, checks if the game should end
    //executed when the form is at the bottom
    public void stop() {
        TetrisWorld.getWorld().removeObject(this);
        dead = true;
        Forms form = TetrisWorld.getWorld().generateForm();
        if(checkEnd(form)) {
            form.delete();
            TetrisWorld.getWorld().setCurrent(null);
            TetrisWorld.getWorld().end();
        }else 
            TetrisWorld.getWorld().setCurrent(form);
    }
    //checks if the game should end   true when there already is a piece at the spawn 
    //location of the new Form
    public boolean checkEnd(Forms form) {
        for (int i = 0; i < 4; i++) {
            List list = TetrisWorld.getWorld().getObjectsAt(
                    form.pieceArr[i].getX(),form.pieceArr[i].getY(),
                    Piece.class);
            if (list.size() > 1) 
                return true;
        }
        return false;
    }
    //generates a random Direction for the Form
    public int generateDirection() {
        return (int)(Math.random() * 4);
    }
    
    public void act() 
    {
        TetrisWorld world = TetrisWorld.getWorld();
        if(world.getCurrent() == null) {
            world.end();
            return;
        }
        if(dead)
            return;
        //keyPressed prevents bug from button spamming
        boolean keyPressed = false;
        if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && count < 4) {
            if(left()) {
                count++;
                keyPressed = true;
            }
        }
        if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && count < 4) {
            if(right()) {
                count++;
                keyPressed = true;
            }
        }
        if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))) {
            if(turn()) {
                count++;
                keyPressed = true;
            }
        }
        count = 0;
        if(Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("")) {
            down();
            return;     
        }
        if(keyPressed)
            return;
            
        oneDown();
    }    
}
