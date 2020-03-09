import java.awt.*;
import java.util.Random;

public class BossEnemy extends GameObject{
    private Handler handler;
    private Random r = new Random();
    private int timer1 = 5;
    private int timer2 = 10;
    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velocityX = 0;
        velocityY = 5;
    }
    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        if (timer1 <= 0)
            velocityY = 0;
        else
            timer1-- ;
        if (timer1 <= 0)
            timer2--;
        if (timer2 <= 0) {
            if (velocityX == 0)
                velocityX = 3;
            if (velocityX >= 0)
                velocityX += 0.01f;
            else if (velocityX <= 0)
                velocityX -= 0.01f;
            velocityX = Game.clamp(velocityX, -10, 10);
            int spawn = r.nextInt(2);
            if (spawn == 0)
                handler.addObject(new BossBullet((int) x + 30, (int) y + 30, ID.BasicEnemy, handler));
        }
        if (y <= 0 || y >= Game.HEIGHT - 40)
            velocityY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 30)
            velocityX *= -1;
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 36, 36, handler, 0.09f));
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 36, 36);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 36, 36);
    }
}