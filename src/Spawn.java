import java.awt.*;
import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random();
    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }
    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 200) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (hud.getLevel() == 2) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
                for (int i = 0; i < 2; i++)
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.FastEnemy, handler));
            }
            else if (hud.getLevel() == 3) {
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
                handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.smartEnemy, handler));
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.FastEnemy, handler));
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
                }
            }
            else if (hud.getLevel() == 4) {
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.BasicEnemy, handler));
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.FastEnemy, handler));
                }
                handler.addObject(new smartEnemy(r.nextInt(Game.WIDTH - 64), r.nextInt(Game.HEIGHT - 64), ID.smartEnemy, handler));
            }
            else if (hud.getLevel() == 5) {
                handler.clearEnemy();
                handler.addObject(new BossEnemy((Game.WIDTH - 300), 5, ID.BossEnemy, handler));
            }
        }
    }
}