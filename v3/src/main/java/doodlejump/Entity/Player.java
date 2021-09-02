package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
    private boolean moveRight;
    private boolean moveLeft;
    private double rightBorder;
    private boolean bouncing;
    private int bounceProgression;

    public Player(Layer layer, Vector2D location, double width, double height, double rightBorder) 
    {
        super(layer, location, new Vector2D(0, Settings.JUMP_VELOCITY), new Vector2D(0, Settings.GRAVITY), width, height);
        this.rightBorder = rightBorder;
        this.moveLeft = false;
        this.moveRight = false;
        this.bouncing = false;
        this.bounceProgression = 0;
    }

    @Override
    public Node createView() {
        return new Rectangle(width, height);
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
            setLocation(rightBorder-Settings.PLAYER_BUFFER, getLocation().y);
        else if(getLocation().x > rightBorder)
            setLocation(Settings.PLAYER_BUFFER, getLocation().y);

        if(bouncing)
        {
            angle += Settings.BOUNCE_SPIN_SPEED;
            bounceProgression++;
            if(bounceProgression >= 360/Settings.BOUNCE_SPIN_SPEED)
            {
                bouncing = false;
                angle = 0;
                bounceProgression = 0;
            }
        }
    }

    public void jump() {
        velocity = new Vector2D(velocity.x, Settings.JUMP_VELOCITY);
    }

    public void springJump() {
        velocity = new Vector2D(velocity.x, Settings.SPRING_JUMP_VELOCITY);
    }

    public void bounceJump() {
        bouncing = true;
        velocity = new Vector2D(velocity.x, Settings.BOUNCE_JUMP_VELOCITY);
    }

}
