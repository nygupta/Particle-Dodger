import java.awt.*;

public class Player extends GameObject {
    //Random r = new Random();
    Handler handler;
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }
    @Override
    public void tick() {
        x += velocityX;
        y += velocityY;
        x = Game.clamp((int)x, 0, Game.WIDTH - 16);
        y = Game.clamp((int)y, 40, Game.HEIGHT - 48);
        Collision();
    }
    private void Collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.BasicEnemy || temp.getId() == ID.FastEnemy || temp.getId() == ID.smartEnemy || temp.getId() == ID.BossEnemy)  //temp is basic enemy;
                if (getBounds().intersects(temp.getBounds()))
                    HUD.HEALTH -= 2;
            if (temp.getId() == ID.BossEnemy)
                if (getBounds().intersects(temp.getBounds()))
                    HUD.HEALTH -= 10;
        }
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect((int)x, (int)y, 16, 16);
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
}