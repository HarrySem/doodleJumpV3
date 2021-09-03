package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Projectile extends Sprite{

    public Projectile(Layer layer, Vector2D location) {
        super(layer, location, new Vector2D(0, Settings.SHOT_VELOCITY), new Vector2D(0, 0), Settings.SHOT_WIDTH, Settings.SHOT_HEIGHT);
    }

    @Override
    public Node createView() {
        return new Rectangle(width, height, Paint.valueOf("red"));
    }

    public void hit()
    {
        //TODO: check for hits on enemies test
    }
    
}
