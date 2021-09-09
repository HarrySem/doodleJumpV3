package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;

public class MovingPlatformVertical extends Platform{

    private boolean movingDown;
    private double a, b;
    private double traversingDistance;
    private double staticYLocation;

    public MovingPlatformVertical(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.traversingDistance = Settings.MOVE_DISTANCE_PLATFORM;
        this.staticYLocation = location.y;
        this.a = staticYLocation - traversingDistance;
        this.b = staticYLocation + traversingDistance;
    }

    @Override
    public Node createView() {
        return super.createView();
    }

    @Override
    public void display() {
        if(movingDown)
        {
            setLocationOffset(0, Settings.MOVING_PLATFORM_SPEED);
            staticYLocation += Settings.MOVING_PLATFORM_SPEED;
            if(staticYLocation > b)
                movingDown = false;
        }
        else
        {
            setLocationOffset(0, -Settings.MOVING_PLATFORM_SPEED);
            staticYLocation -= Settings.MOVING_PLATFORM_SPEED;
            if(staticYLocation < a)
                movingDown = true;
        }
        super.display();
    }
    
}
