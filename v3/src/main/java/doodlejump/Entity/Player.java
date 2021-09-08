package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Player extends Sprite{
    private boolean moveRight, moveLeft, bouncing, propeller, rocket, shoot, falling, dead;
    private double rightBorder, bounceProgression, propellerProgression, rocketProgession, shotProgression;

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
        this.rocket = false;
        this.rocketProgession = 0;
        this.shoot = false;
        this.shotProgression = 0;
        this.falling = false;
        this.dead = false;
    }

    @Override
    public Node createView() {
        if(propeller)
        {
            Group group = new Group();
            Rectangle rectangle = new Rectangle(width, height);
            Rectangle propeller = new Rectangle(Settings.PROPELLER_WIDTH, Settings.PROPELLER_HEIGHT, Paint.valueOf("green"));
            group.getChildren().add(rectangle);
            group.getChildren().add(propeller);
            propeller.setLayoutY(-Settings.PROPELLER_HEIGHT);
            return group;
        }
        else if(rocket)
        {
            Group group = new Group();
            Rectangle rectangle = new Rectangle(Settings.ROCKET_WIDTH, Settings.ROCKET_HEIGHT, Paint.valueOf("purple"));
            Circle circle = new Circle(5);
            group.getChildren().add(rectangle);
            group.getChildren().add(circle);
            circle.setLayoutX(Settings.ROCKET_WIDTH/2);
            circle.setLayoutY(Settings.ROCKET_HEIGHT/2);
            return group;
        }
        else if(shoot)
        {
            Group group = new Group();
            Rectangle rectangle = new Rectangle(width, height);
            Rectangle nose = new Rectangle(width/2, height/2);
            group.getChildren().add(rectangle);
            group.getChildren().add(nose);
            nose.setLayoutX(width/4);
            nose.setLayoutY(-height/2);
            return group;
        }
        else if(dead)
        {
            return new Rectangle(width, height);
            //TODO: make player look dead (stars etc.)
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
        if(dead)
            return;
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
        else if(rocket)
        {
            rocketProgession++;
            if(rocketProgession >= Settings.ROCKET_DURATION)
            {
                rocket = false;
                rocketProgession = 0;
                acceleration = new Vector2D(0, Settings.GRAVITY);
                updateView();
            }
        }
        if(shoot && !rocket && !propeller)
        {
            shotProgression++;
            if(shotProgression >= Settings.SHOT_DURATION)
            {
                shoot = false;
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
        if(!rocket)
        {
            propeller = true;
            acceleration = new Vector2D(0, 0);
            velocity = new Vector2D(0, Settings.PROPELLER_SPEED);
            updateView();
        }
    }

    public void rocket() {
        if(!propeller)
        {
            rocket = true;
            acceleration = new Vector2D(0, Settings.ROCKET_ACCELERATION);
            velocity = new Vector2D(0, Settings.ROCKET_SPEED);
            updateView();
        }
    }

    public void shoot()
    {
        shoot = true;
        shotProgression = 0;
        updateView();
    }

    public void setFalling(boolean falling)
    {
        this.falling = falling;
    }

    public boolean getFalling()
    {
        return falling;
    }

    public void setDead()
    {
        dead = true;
    }

    public boolean isDead()
    {
        return dead;
    }

}
