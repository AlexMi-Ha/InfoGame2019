import greenfoot.*;
import java.util.List;

public class PoolWorld extends World
{
    private static PoolWorld world;     //singleton variable
    
    private PoolBall p_ball;            //white ball of the player
    private PoolStick p_stick;          //stick of the player
    
    private boolean shootTime = true;   //if its time to shoot
    
    private Scoreboard displayMoves;
    private Scoreboard displayScore;
    
    private int resetTimer = -1;
    private int loseWinLoop = 0;
    
    private int characterX, characterY, characterFacing;  //Position of the player interacting with the Machine
    
    public PoolWorld(int characterX, int characterY, int characterFacing) {
        super(800, 500, 1, false);
        
        this.characterX = characterX;
        this.characterY = characterY;
        this.characterFacing = characterFacing;
        
        constructWorld();
    }
    
    public PoolWorld()
    {    
        super(800, 500, 1, false);
        
        constructWorld();
    }
    
    public void constructWorld() {
        world = this;
        
        displayMoves = new Scoreboard("Züge: ", 0, 200);
        displayScore = new Scoreboard("Punkte: ", 0, 200);
        
        addObject(displayMoves, 90, 30);
        addObject(displayScore, 240, 30);
        
        GreenfootImage img = new GreenfootImage("textures/games/pool/billardtable.png");
        img.scale(800, 500);
        img.setColor(Color.WHITE);
        img.setFont(img.getFont().deriveFont(24f));
        img.drawString("Drücke ESC um zurück zu kommen", 220, getHeight() - 10);
        setBackground(img);
        
        addWalls();
        addHoles();
        addBalls();
        
        p_stick = new PoolStick();
        addObject(p_stick, -10, -10);
    }
    
    public void act() {
        if(noRolling())         //stop shooting when balls are rolling
            shootTime = true;
        else
            shootTime = false;
        
        for(PoolBall p : getObjects(PoolBall.class)) //apply Friction to the balls
            friction(p);

        for(PoolBall p : getObjects(PoolBall.class)) {  //manage the collisions
            collideBalls(p);
            collideWalls(p);
        }
        
        if(allBlackDead() || loseWinLoop == 2)  //win if all black are in a hole
            win();
            
        if((p_ball != null && !p_ball.getInWorld()) || loseWinLoop == 1)  //loose if the white falls in a hole
            lose();
            
        if(Greenfoot.isKeyDown("escape")) {
            Greenfoot.setWorld(new CasinoWorld(false, Greenfoot.isKeyDown("escape")));
            if(characterX != 0 && characterY != 0) {
                CasinoWorld.getWorld().getCharacter().setLocation(characterX, characterY);
                CasinoWorld.getWorld().getCharacter().setFacing(characterFacing);
            }else {
                CasinoWorld.getWorld().getCharacter().setLocation(213, 287);
                CasinoWorld.getWorld().getCharacter().setFacing(-1);
            }
        }
            
    }
    //
    public boolean allBlackDead() {
        for(PoolBall p : getObjects(PoolBall.class)) {
            if(p == p_ball)
                continue;
            if(p.getInWorld())
                return false;
        }
        return true;
    }
    //
    public void win() {
        if(resetTimer == -1) {
            p_ball.move(0, 0);
            removeObject(p_stick);
            p_stick = null;
            p_ball = null;
            loseWinLoop = 2;
            resetTimer = 100;
        }
        resetTimer--;
        if(resetTimer == 0) {
            loseWinLoop = 0;
            resetTimer = -1;
            reset();
        }
    }
    
    public void reset() {
        for(PoolBall p : getObjects(PoolBall.class)) 
            removeObject(p);
        addBalls();
        displayMoves.removePoints(displayMoves.getScore());
        displayScore.removePoints(displayScore.getScore());
        p_stick = new PoolStick();
        addObject(p_stick, -10, -10);
    }
    //
    public void lose() {
        if(resetTimer == -1) {
            for(PoolBall p : getObjects(PoolBall.class))
                p.move(0, 0);
            removeObject(p_stick);
            p_stick = null;
            p_ball = null;
            loseWinLoop = 1;
            resetTimer = 100;
        }
        resetTimer--;
        if(resetTimer == 0) {
            loseWinLoop = 0;
            resetTimer = -1;
            reset();
        }
    }
    //Check for no rolling Balls
    public boolean noRolling() {
        List<PoolBall> l = getObjects(PoolBall.class);
        for(PoolBall p : l) {
            if(Math.abs(p.getXVel()) > 0.1 || Math.abs(p.getYVel()) > 0.1) 
                return false;
        }
        return true;
    }
    
    //Init Methods
    public void addHoles() {
        addObject(new PoolHole(), 75, 50);
        addObject(new PoolHole(), getWidth() / 2, 33);
        addObject(new PoolHole(), getWidth() / 2, getHeight() - 35);
        addObject(new PoolHole(), getWidth() - 76, 50);
        addObject(new PoolHole(), 75, getHeight() - 51);
        addObject(new PoolHole(), getWidth() - 76, getHeight() - 51);
    }
    
    public void addWalls() {
        addObject(new Wall((getWidth() - 260) / 2,10, 0), 240, 52);
        addObject(new Wall((getWidth() - 260) / 2,10, 0), getWidth() - 240, 52);
        
        addObject(new Wall((getWidth() - 260) / 2,10, 0), 240, 448);
        addObject(new Wall((getWidth() - 260) / 2,10, 0), getWidth() - 240, 448);
        
        addObject(new Wall(10, getHeight() - 160, 0), 73, getHeight() / 2);
        addObject(new Wall(10, getHeight() - 160, 0), getWidth() - 73, getHeight() / 2);
    }
    
    public void addBalls() {
        Color c = new Color(24, 23, 24);
        
        addObject(new PoolBall(c, 507, getHeight() / 2), 507, getHeight() / 2);
        
        addObject(new PoolBall(c, 525, getHeight() / 2 - 10), 525, getHeight() / 2 - 10);
        addObject(new PoolBall(c, 525, getHeight() / 2 + 10), 525, getHeight() / 2 + 10);
        
        addObject(new PoolBall(c, 543, getHeight() / 2), 543, getHeight() / 2);
        addObject(new PoolBall(c, 543, getHeight() / 2 + 20), 543, getHeight() / 2 + 20);
        addObject(new PoolBall(c, 543, getHeight() / 2 - 20), 543, getHeight() / 2 - 20);
        
        addObject(new PoolBall(c, 561, getHeight() / 2 - 10), 561, getHeight() / 2 - 10);
        addObject(new PoolBall(c, 561, getHeight() / 2 + 10), 561, getHeight() / 2 + 10);
        addObject(new PoolBall(c, 561, getHeight() / 2 - 30), 561, getHeight() / 2 - 30);
        addObject(new PoolBall(c, 561, getHeight() / 2 + 30), 561, getHeight() / 2 + 30);
        
        addObject(new PoolBall(c, 579, getHeight() / 2), 579, getHeight() / 2);
        addObject(new PoolBall(c, 579, getHeight() / 2 + 20), 579, getHeight() / 2 + 20);
        addObject(new PoolBall(c, 579, getHeight() / 2 - 20), 579, getHeight() / 2 - 20);
        addObject(new PoolBall(c, 579, getHeight() / 2 + 40), 579, getHeight() / 2 + 40);
        addObject(new PoolBall(c, 579, getHeight() / 2 - 40), 579, getHeight() / 2 - 40);
        
        Color w = new Color(240, 239, 232);
        
        p_ball = new PoolBall(w, 233, getHeight() / 2);
        addObject(p_ball, 233, getHeight() / 2);
    }
    
    //apply a friction force to all balls
    public void friction(PoolBall p) {
        double vx = p.getXVel() * 0.99;
        double vy = p.getYVel() * 0.99;
        if(Math.hypot(vx, vy) < 0.05) 
            p.move(0, 0);
        else
            p.move(vx, vy);
    }
    //Manage Collisions with Walls
    public void collideWalls(PoolBall p) {
        double movedX = p.getMovedX();
        double movedY = p.getMovedY();
        
        double vx = p.getXVel();
        double vy = p.getYVel();
        
        boolean b = false;
        List<Wall> l = getObjects(Wall.class);
        for(Wall w : l) {
            if(w.intersectsCircle((int)movedX, (int)movedY, p.getRadius())) {
                double angle = Math.toDegrees(Math.atan2(vy, vx));
                angle = 2 * w.getAngle((int)movedX, (int)movedY, p.getRadius()) - 180 - angle;
                double mag = 0.9 * Math.hypot(vx,vy);
                
                vx = Math.cos(Math.toRadians(angle)) * mag;
                vy = Math.sin(Math.toRadians(angle)) * mag;
                b = true;
                break;
            }
        }
        
        if(b) {
            p.move(vx, vy);
            p.playWallSound();
        }
    }
    //Manage collisions with other Balls
    public void collideBalls(PoolBall p) {
        if(p.getXVel() == 0 && p.getYVel() == 0)
            return;
            
        double movedX = p.getMovedX();
        double movedY = p.getMovedY();
        List<PoolBall> l = getObjects(PoolBall.class);
        for(PoolBall c : l) {
            if(c == p)
                continue;
                
            double distX = c.getMovedX() - movedX;
            double distY = c.getMovedY() - movedY;
            double dist = Math.sqrt(distX * distX + distY * distY);
            
            if(dist <= p.getRadius() + c.getRadius()) {
                distX /= dist;
                distY /= dist;
                
                double tT = distAlong(p.getXVel(), p.getYVel(), distX, distY);
                double tM = distAlong(c.getXVel(), c.getYVel(), distX, distY);
                
                double mO = distAlong(p.getXVel(), p.getYVel(), distY, -distX);
                double tO = distAlong(c.getXVel(), c.getYVel(), distY, -distX);
                
                p.move(tM * distX + mO * distY, tM * distY + mO * -distX);
                c.move(tT * distX + tO * distY, tT * distY + tO * -distX);
                c.playBallSound();
            }
        }
    }
    private double distAlong(double x, double y, double xAlong, double yAlong) {
        return (x * xAlong + y * yAlong) / Math.hypot(xAlong, yAlong);
    }
    
    //getter / setter
    
    public static PoolWorld getWorld() {
        return world;
    }
    
    public PoolBall getBall() {
        return p_ball;
    }
    
    public boolean getShootTime() {
        return shootTime;
    }
    
    public void setShootTime(boolean b) {
        shootTime = b;
    } 
    
    public Scoreboard getMovesDisplay() {
        return displayMoves;
    }
    
    public Scoreboard getScoreDisplay() {
        return displayScore;
    }
}
