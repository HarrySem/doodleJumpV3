package doodlejump.Entity;

import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class CircleTest extends Sprite{

    public CircleTest(Layer layer, Vector2D location, double width)
    {
        super(layer, location, new Vector2D(0, 0), new Vector2D(0, 0), width, width);
    }

    @Override
    public Node createView() {
        return new Circle(centerX, centerY, width/2);
    }
    
}
