package doodlejump.Entity;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Platform extends Sprite{

    public Platform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, new Vector2D(0, 0), new Vector2D(0, 0), width, height);
        }

    @Override
    public Node createView() {
        return new Rectangle(width, height);
    }
    
}
