import greenfoot.*;

//Slot of the slotmachine

public class SlotIcon extends Actor
{
    private GreenfootImage[] images = new GreenfootImage[8];    //images for the slots
    private int[] animateOrder;     //the order of the roll animation (order of the images)
    private int slotnr;             //number of the slot
    private boolean startAnimate = false;   //start the Animation
    private double timer;          //timer to swap the images in the 
    private int imageCounter;      //counter wich image of the order to take
    
    public SlotIcon(int startIcon, int nr) {
        images[0] = new GreenfootImage("textures/games/slot/A.png");
        images[1] = new GreenfootImage("textures/games/slot/B.png");
        images[2] = new GreenfootImage("textures/games/slot/C.png");
        images[3] = new GreenfootImage("textures/games/slot/D.png");
        images[4] = new GreenfootImage("textures/games/slot/E.png");
        images[5] = new GreenfootImage("textures/games/slot/F.png");
        images[6] = new GreenfootImage("textures/games/slot/G.png");
        images[7] = new GreenfootImage("textures/games/slot/H.png");
        setImage(images[startIcon]);
        
        slotnr = nr;
        timer = 0;
        imageCounter = 0;
    }
    
    public void act() 
    {
        if(startAnimate)
            animate();
    } 
    
    public void animate() {
        //update the Image every 10 ticks
        if(timer % 10 == 0) {
            setImage(images[animateOrder[imageCounter]]);
            //System.out.println("["+slotnr+"] " +"Timer: " + timer + "   Counter: " + imageCounter + "   " + images[animateOrder[imageCounter]]);
            imageCounter++;  
        }
        //animation finsihed
        if(timer == (animateOrder.length - 1) * 10) {
            startAnimate = false;
            timer = 0;
            imageCounter = 0;
            return;
        }
        timer++;
    }
    //start the animation in the orde animateOrder[]
    public void startAnimation(int[] animateOrder) {
        startAnimate = true;
        this.animateOrder = animateOrder;
    }
    //generate a random result for the slot with the given probability
    public int generateResult() {
        int[] propability = SlotWorld.getWorld().getPropability();
        int ran = Greenfoot.getRandomNumber(100);
        
        int tempSum = 0;
        
        for(int i = 0; i < propability.length; i++) {
            tempSum += propability[i];
            if(tempSum > ran) 
                return i;
        }
        //unreachable code
        return 3;
    }
    
    //getter
    public GreenfootImage[] getImageArray() {
        return images;
    }
    
    public boolean Running() {
        return startAnimate;
    }
}
