package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MovingPlatformHorizontal extends Platform{

    private boolean movingRight;
    private double a, b;
    private double traversingDistance;

    public MovingPlatformHorizontal(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.movingRight = true;
        this.traversingDistance = Settings.MOVE_DISTANCE_PLATFORM;
        if(location.x - traversingDistance > Settings.PLATFORM_BUFFER)
        {
            if(location.x + traversingDistance < layer.getPrefWidth()-Settings.PLATFORM_BUFFER)
            {
                this.a = location.x - traversingDistance;
                this.b = location.x + traversingDistance;
            }
            else
            {
                this.b = layer.getPrefWidth() - Settings.PLATFORM_BUFFER;
                this.a = b - 2*traversingDistance;
            }
        }
        else
        {
            if(location.x + traversingDistance < layer.getPrefWidth()-Settings.PLATFORM_BUFFER)
            {
                this.a = Settings.PLATFORM_BUFFER;
                this.b = a + 2*traversingDistance;
            }
            else
            {
                this.a = Settings.PLATFORM_BUFFER;
                this.b = layer.getWidth() - Settings.PLATFORM_BUFFER;
                traversingDistance = b - a;
            }
        }
    }

    @Override
    public Node createView() {
        Rectangle rectangle = (Rectangle)super.createView();
        rectangle.setFill(Paint.valueOf("blue"));
        return rectangle;
    }

    @Override
    public void display() {
        if(movingRight)
        {
            setLocationOffset(Settings.MOVING_PLATFORM_SPEED, 0);
            if(location.x > b)
                movingRight = false;
        }
        else
        {
            setLocationOffset(-Settings.MOVING_PLATFORM_SPEED, 0);
            if(location.x < a)
                movingRight = true;
        }
        super.display();
    }
    
}
