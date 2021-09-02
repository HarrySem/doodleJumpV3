package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
    private boolean moveRight;
    private boolean moveLeft;
    private double rightBorder;
    private boolean bouncing;
    private int bounceProgression;
    private boolean propeller;
    private int propellerProgression;

    public Player(Layer layer, Vector2D location, double width, double height, double rightBorder) 
    {
        super(layer, location, new Vector2D(0, Settings.JUMP_VELOCITY), new Vector2D(0, Settings.GRAVITY), width, height);
        this.rightBorder = rightBorder;
        this.moveLeft = false;
        this.moveRight = false;
        this.bouncing = false;
        this.propeller = false;
        this.bounceProgression = 0;
        this.propellerProgression = 0;
    }

    @Override
    public Node createView() {
        if(propeller)
        {
            Group group = new Group();
            Rectangle rectangle = new Rectangle(width, height);
            Rectangle propeller = new Rectangle(20, 20, Paint.valueOf("green"));
            group.getChildren().add(rectangle);
            group.getChildren().add(propeller);
            propeller.setLayoutY(-Settings.PROPELLER_HEIGHT);
            return group;
        }
        else
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
        else if(propeller)
        {
            propellerProgression++;
            if(propellerProgression >= Settings.PROPELLER_DURATION)
            {
                propeller = false;
                propellerProgression = 0;
                acceleration = new Vector2D(0, Settings.GRAVITY);
                updateView();
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

    public void propeller()
    {
        propeller = true;
        acceleration = new Vector2D(0, 0);
        velocity = new Vector2D(0, Settings.PROPELLER_SPEED);
        updateView();
    }

}
