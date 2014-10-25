import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    private int FPS = 500;
    private long optimalTime = 1000 / FPS;
    private boolean running = false;
    private Graphics2D g;
    private Player player;
    private Ball ball;
    public static int actualLvl = 1;
    private static Color gameColor = Color.RED;
    private int heightScore = 1;


    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        player = new Player(20, HEIGHT - Player.HEIGHT * 4);
        ball = new Ball(player);

        addKeyListener(new myKeyListener(player));
    }


    private void playerFollowBall() {
        if (player.getPositionX() + player.getWidth() / 2 > ball.getX()) {
            player.turnLeft();
        } else {
            player.turnRight();
        }
    }


    public void run() {
        init();

        long startTime;
        long elapsedTime;
        long waitTime;
        while (running) {
            startTime = System.nanoTime();
            update();
            repaint();
            elapsedTime = System.nanoTime() - startTime;
            waitTime = optimalTime - elapsedTime / 1000000;
            try {
                if (waitTime <= 0) {
                    waitTime = 2;
                }
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        running = true;
    }

    public static void changeLvl() {
        actualLvl++;
        gameColor = createRandomColor();
    }

    private static Color createRandomColor() {

        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);
    }

    private void update() {
        ball.tick();
        player.tick();
        if (player.isOnAutoPilot) {
            playerFollowBall();
        }
        updateHeightScoreIfNeed();
    }

    private void updateHeightScoreIfNeed() {
        if (heightScore < actualLvl) {
            heightScore = actualLvl;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(player.getColor());
        g.fillOval((int) ball.getX(), (int) ball.getY(), (int) ball.getSize(), (int) ball.getSize());

        g.setColor(gameColor);
        g.fillRect((int) player.getPositionX(), (int) player.getPositionY(), (int) player.getWidth(), (int) player.getHeight());

        g.drawString("Level: " + actualLvl, WIDTH - 100, 50);
        g.drawString("Top lvl:  " + heightScore, WIDTH - 100, 70);
    }
}
