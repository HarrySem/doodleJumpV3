package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MovingPlatform extends Platform{

    private boolean movingRight;
    private double a, b;
    private double traversingDistance;

    public MovingPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.movingRight = true;
        this.traversingDistance = Settings.MOVE_DISTANCE_PLATFORM;
        if(location.x - traversingDistance/2 > 0 && location.x + traversingDistance/2 < layer.getPrefWidth())
        {
            this.a = location.x - traversingDistance/2;
            this.b = location.x + traversingDistance/2;
        }
        //else if(location.x) TODO: contrinue here, maybe make more efficent lulz
    }

    @Override
    public Node createView() {
        Rectangle rectangle = (Rectangle)super.createView();
        rectangle.setFill(Paint.valueOf("turquois"));
        return rectangle;
    }

    @Override
    public void display() {
        if()
        super.display();
    }
    
}
