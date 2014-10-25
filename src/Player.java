import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player{
    private double positionX;
    private double positionY;
    public static double HEIGHT = 10;
    public static double WIDTH = 60;
    private Color color = new Color(255,0,0);
    public Rectangle playerBounds;
    public static double speed = 1;

    public boolean isOnAutoPilot = false;
    private boolean headingLeft = false;
    private boolean headingRight = false;

    public Player(double newPositionX, double newPositionY){
        positionX = newPositionX;
        positionY = newPositionY;

        playerBounds = new Rectangle((int)positionX, (int)positionY, (int)WIDTH, (int)HEIGHT);
        playerBounds.setBounds((int)positionX, (int)positionY, (int)WIDTH, (int)HEIGHT);
    }

    public void tick(){
        playerBounds.setBounds((int)positionX, (int)positionY, (int)WIDTH, (int)HEIGHT);
        if(headingLeft && positionX > 0){
            positionX -= 1 * speed;
        }else if(headingRight && positionX + WIDTH < GamePanel.WIDTH){
            positionX += 1 * speed;
        }
    }


    public void turnLeft(){
        headingLeft = true;
        headingRight = false;
    }
    public void turnRight(){
        headingLeft = false;
        headingRight = true;
    }



    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getHeight() {
        return HEIGHT;
    }


    public double getWidth() {
        return WIDTH;
    }


    public Color getColor() {
        return color;
    }


}
