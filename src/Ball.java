import java.awt.*;

public class Ball {
    public double x, y;
    private double speed = 0.5;
    private double startX, startY;
    public double speedX, speedY;
    private double size = 15;
    public Rectangle ballBounds;
    private Player player;
    static boolean moving = false;

    public Ball(Player player) {
        startX = GamePanel.WIDTH/2;
        startY = GamePanel.HEIGHT/2;

        x = startX;
        y = startY;

        this.player = player;
        speedX = speed;
        speedY = speed;

        ballBounds = new Rectangle((int)x, (int)y, (int)size, (int)size);
        ballBounds.setBounds((int)x, (int)y, (int)size, (int)size);
    }

    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void tick(){

        ballBounds.setBounds((int)x, (int)y, (int)size, (int)size);

        if(moving){
            if(x <= 0){
                speedX = speed;
            }else if(x + size > GamePanel.WIDTH){
                speedX = -speedX;
            }

            if(y <= 0){
                speedY = speed;
            }else if(y+size > GamePanel.HEIGHT){
                speedY = -speedY;
            }
            playerCollision();

            if(y + size >= GamePanel.HEIGHT){
                reset();
            }

            x += speedX;
            y += speedY;
        }

    }

    private void reset(){
        speed = 0.5;
        Player.speed = 1;
        moving = false;
        setPosition(startX, startY);
        GamePanel.actualLvl = 1;
    }
    public void playerCollision(){
            if(ballBounds.intersects(player.playerBounds)){
                speedY = -speed;
                speed = speed*1.02;
                Player.speed = Player.speed *1.02;
                GamePanel.changeLvl();
            }
    }

    public double getX() {
        return x;
    }

    public double getSize() {
        return size;
    }




    public double getY() {
        return y;
    }

}
