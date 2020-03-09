import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean keyDown[] = new boolean[4];
    Game game;
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_W){
                    temp.setVelocityY(-5);
                    temp.setVelocityX(0);
                }
                if (key == KeyEvent.VK_S) {
                    temp.setVelocityY(5);
                    temp.setVelocityX(0);
                }
                if (key == KeyEvent.VK_D) {
                    temp.setVelocityX(5);
                    temp.setVelocityY(0);
                }
                if (key == KeyEvent.VK_A) {
                    temp.setVelocityX(-5);
                    temp.setVelocityY(0);
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE)
            System.exit(0);
        if (Game.gameState == Game.STATE.Game) {
            if (key == KeyEvent.VK_P){
                if (Game.paused)
                    Game.paused = false;
                else
                    Game.paused = true;
            }
        }
    }
    // for going diagonal uncomment keyReleased
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_W)
                    temp.setVelocityY(0);
                if (key == KeyEvent.VK_S)
                    temp.setVelocityY(0);
                if (key == KeyEvent.VK_D)
                    temp.setVelocityX(0);
                if (key == KeyEvent.VK_A)
                    temp.setVelocityX(0);

            }
        }
    }
}