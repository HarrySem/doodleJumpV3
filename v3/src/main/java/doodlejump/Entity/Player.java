package doodlejump.Entity;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{

    public Player(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, new Vector2D(0, Settings.JUMP_VELOCITY), new Vector2D(0, Settings.GRAVITY), width, height);
        }

    @Override
    public Node createView() {
        return new Rectangle(width, height);
    }

    public void touching(List<Platform> platforms) {
        platforms.forEach(x -> {
            if(getLowest() > x.getHighest() && getLowest() < x.getLowest() && 
            getMostRight() > x.getMostLeft() && getMostLeft() < x.getMostRight())
                    velocity = new Vector2D(velocity.x, Settings.JUMP_VELOCITY);
        });

    }

}
