import java.awt.*;
import java.util.ArrayList;
public class Handler {
    public ArrayList<GameObject> object = new ArrayList<GameObject>();
    public void tick() {
        //this.addObject(new MenuParticle(0, 0, ID.MenuParticle, this));
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }
    public void clearEnemy() {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            if (temp.getId() == ID.Player) {
                object.clear();
                if (Game.gameState != Game.STATE.End)
                    addObject(new Player((int) temp.getX(), (int) temp.getY(), ID.Player, this));
            }
        }
    }
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.render(g);
        }
    }
    public void addObject(GameObject object) {
        this.object.add(object);
    }
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}