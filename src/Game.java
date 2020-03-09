import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = -240840600533728354L;
    public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;
    public static boolean paused = false;
    private Random r;
    private Handler handler;
    private HUD hud;
    private Thread thread;
    private Spawn spawn;
    private boolean running = false;
    private Menu menu;
    public enum STATE {
        Menu,
        Help,
        End,
        Game
    };
    public static STATE gameState = STATE.Menu;
    public Game() {
        hud = new HUD();
        handler = new Handler();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        spawn = new Spawn(handler, hud);
        new Window(WIDTH, HEIGHT, "WAVE", this);
        r = new Random();
        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            for (int i = 0; i < 5; i++)
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 36), r.nextInt(Game.HEIGHT - 36), ID.BasicEnemy, handler));
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help) {
            for (int i = 0; i < 40; i++)
                handler.addObject(new MenuParticle(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, handler));
        }
    }
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] str) {
        new Game();
    }
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 40.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta --;
            }
            if (running)
                render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }
    private void tick() {
        if (gameState == STATE.Game) {
            if (!paused) {
                hud.tick();
                spawn.tick();
                handler.tick();
                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemy();
                    for (int i = 0; i < 40; i++)
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH - 50), r.nextInt(HEIGHT - 50), ID.MenuParticle, handler));
                }
            }
        }
        else  if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help){
            menu.tick();
            handler.tick();
        }
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if (paused) {
            g.setColor(Color.white);
            g.drawString("PAUSED", 250, 200);
        }
        if (gameState == STATE.Game) {
            hud.render(g);
        }
        else  if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End){
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }
    public static float clamp(float var, float min, float max) {
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else
            return var;

    }
}