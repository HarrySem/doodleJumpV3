package doodlejump.Entity;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
    private boolean moveRight;
    private boolean moveLeft;

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

    public void setRight()
    {
        moveRight = true;
        moveLeft = false;
    }

    public void setLeft()
    {
        moveRight = false;
        moveLeft = true;
    }

    public void releaseRight()
    {
        moveRight = false;
    }

    public void releaseLeft()
    {
        moveLeft = false;
    }

    public void move()
    {
        super.move();
        if(moveRight)
            setLocationOffset(Settings.RIGHT_ARROW_SPEED, 0);
        else if(moveLeft)
            setLocationOffset(Settings.LEFT_ARROW_SPEED, 0);
    }

}
