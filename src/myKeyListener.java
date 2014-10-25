import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myKeyListener implements KeyListener {

    Player player;

    public myKeyListener(Player player){
        this.player = player;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_LEFT:
                if(!Ball.moving){
                    Ball.moving = true;
                }
                player.turnLeft();
                break;
            case KeyEvent.VK_RIGHT :
                if(!Ball.moving){
                    Ball.moving = true;
                }
                player.turnRight();
                break;
            case KeyEvent.VK_A:
                if(player.isOnAutoPilot){
                    player.isOnAutoPilot = false;
                }else{
                    player.isOnAutoPilot = true;
                }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
