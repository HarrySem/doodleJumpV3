package doodlejump.Entity;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{

    public Player(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, new Vector2D(0, 0), new Vector2D(0, 1), width, height);
        }

    @Override
    public Node createView() {
        return new Rectangle(width, height);
    }

}
