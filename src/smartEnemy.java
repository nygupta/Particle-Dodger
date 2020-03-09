import java.awt.*;

public class smartEnemy extends GameObject{
    private Handler handler;
    private GameObject player;
    public smartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for (int i =0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player)
                player = handler.object.get(i);
        }
    }
    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(((x - player.getX()) * (x - player.getX())) + (((y - player.getY()) * (y - player.getY()))));
        velocityX = (float) ((-3.0 / distance) * diffX);
        velocityY = (float) ((-3.0 / distance) * diffY);
        if (y <= 0 || y >= Game.HEIGHT - 40)
            velocityY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 20)
            velocityX *= -1;
        handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.magenta,8, 8, handler, 0.1f));
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int)x, (int)y, 10, 10);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 10, 10);
    }
}
