import greenfoot.*;


public class Ball extends Actor
{
   private int radius;  //radius of the ball
   private double x, y; //exact (double) coordinate of the ball for smoother movement
   
   public Ball(int radius, Color c, double x, double y) {
       this.radius = radius;
       this.x = x;
       this.y = y;
       GreenfootImage img = new GreenfootImage(radius * 2 + 1, radius * 2 + 1);
       img.setColor(c);
       img.fillOval(0, 0, 2* radius, 2 * radius);
       setImage(img);
   }
   
   //getter / setter
   
   public int getRadius() {
       return radius;
   }
   
   public double getExactX() {
       return x;
   }
   
   public double getExactY() {
       return y;
   }
   
   public void setLocation(double x, double y) {
       this.x = x;
       this.y = y;
       super.setLocation((int)x, (int)y);
   }
}
