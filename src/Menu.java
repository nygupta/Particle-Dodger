import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.Menu) {
            //Play Button
            if (mouseOver(mx, my, 200, 100, 200, 50)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemy();
                for (int i = 0; i < 5; i++)
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 36), r.nextInt(Game.HEIGHT - 36), ID.BasicEnemy, handler));
            }
            //Help Button
            else if (mouseOver(mx, my, 200, 200, 200, 50)) {
                Game.gameState = Game.STATE.Help;
            }
            //Quit Button
            else if (mouseOver(mx, my, 200, 300, 200, 50))
                System.exit(0);
        }
        //Back button from help
        else if (Game.gameState == Game.STATE.Help) {
            if (mouseOver(mx, my, 470, 360, 75, 25)) {
                Game.gameState = Game.STATE.Menu;
            }
        }
        //Back button from Game End
        else if (Game.gameState == Game.STATE.End) {
            if (mouseOver(mx, my, 430, 360, 100, 25)) {
                hud.setScore(0);
                hud.setLevel(1);
                Game.gameState = Game.STATE.Menu;
            }
        }
    }
//    public void mouseReleased(MouseEvent e) {
//    }
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width)
            if (my > y && my < y + height)
                return true;
            else
                return false;
        else
            return false;
    }
    public void tick() {
    }
    public void render(Graphics g) {
        Font fnt1 = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 20);
        if (Game.gameState == Game.STATE.Menu) {
            g.setFont(fnt1);
            g.setColor(Color.white);
            g.drawString("MENU", 225, 50);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(200, 100, 200, 50);
            g.setColor(Color.white);
            g.drawString("Play", 267, 135);
            g.setColor(Color.white);
            g.drawRect(200, 200, 200, 50);
            g.setColor(Color.white);
            g.drawString("Help", 267, 235);
            g.setColor(Color.white);
            g.drawRect(200, 300, 200, 50);
            g.setColor(Color.white);
            g.drawString("Quit", 267, 335);
        }
        else if (Game.gameState == Game.STATE.Help){
            g.setFont(fnt1);
            g.setColor(Color.white);
            g.drawString("HELP", 225, 50);
            g.setFont(fnt3);
            g.drawString("Use WASD keys to move the player, and", 100, 100);
            g.drawString("doge from the enemy. As the health bar", 100, 125);
            g.drawString("overs Game overs.", 100, 150);
            g.drawRect(470, 360, 75, 25);
            g.setColor(Color.white);
            g.drawString("back", 480, 380);
        }
        else if (Game.gameState == Game.STATE.End){
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 225, 50);
            g.setFont(fnt3);
            g.drawString("SCORE: " + hud.getScore(), 230, 225);
            g.drawRect(430, 360, 100, 25);
            g.setColor(Color.white);
            g.drawString("Menu", 440, 380);
        }
    }
}
