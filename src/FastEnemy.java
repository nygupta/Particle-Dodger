import java.awt.*;
import java.util.Random;

public class FastEnemy extends GameObject{
    private Handler handler;
    private Random r = new Random();
    public FastEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        int LeftRight = 0;
        LeftRight = r.nextInt(2);
        if (LeftRight == 0) {
            velocityY = 5;
            velocityX = 10;
        }
        else if (LeftRight == 1) {
            velocityY = 10;
            velocityX = 5;
        }
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        if (y <= 0 || y >= Game.HEIGHT - 40)
            velocityY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 20)
            velocityX *= -1;
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.orange, 9, 9, handler, 0.1f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect((int)x, (int)y, 9, 9);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8, 8);
    }
}
