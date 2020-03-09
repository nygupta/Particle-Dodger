import java.awt.*;
import java.util.Random;

public class BossBullet extends GameObject{
    private Handler handler;
    Random r = new Random();
    public BossBullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velocityX = (r.nextInt(7 - -7) + -7);
        if (velocityX == 0)
            velocityX = 7;
        velocityY = 7;
    }
    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        if (y >= Game.HEIGHT)
            handler.removeObject(this);
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 8, 8, handler, 0.1f));
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 8, 8);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8, 8);
    }
}