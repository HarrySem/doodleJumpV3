package doodlejump.Entity;

import java.util.List;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
    private boolean moveRight;
    private boolean moveLeft;
    private double rightBorder;

    public Player(Layer layer, Vector2D location, double width, double height, double rightBorder) {
        super(layer, location, new Vector2D(0, Settings.JUMP_VELOCITY), new Vector2D(0, Settings.GRAVITY), width, height);
        this.rightBorder = rightBorder;
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
        if(getLocation().x < 0)
            setLocation(rightBorder-Settings.BUFFER, getLocation().y);
        else if(getLocation().x > rightBorder)
            setLocation(Settings.BUFFER, getLocation().y);
    }

}
