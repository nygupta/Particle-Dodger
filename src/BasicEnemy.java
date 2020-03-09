import java.awt.*;

public class BasicEnemy extends GameObject{
    private Handler handler;
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velocityX = 5;
        velocityY = 5;
    }

    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        if (y <= 0 || y >= Game.HEIGHT - 40)
            velocityY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 20)
            velocityX *= -1;
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.cyan, 8, 8, handler, 0.1f));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int)x, (int)y, 8, 8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 8, 8);
    }
}