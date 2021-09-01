package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region{

    //
    Vector2D location;
    Vector2D velocity;
    Vector2D acceleration;

    double maxForce = Settings.SPRITE_MAX_FORCE;
    double maxSpeed = Settings.SPRITE_MAX_SPEED;

    Node view;

    // view dimensions
    double width;
    double height;
    double centerX;
    double centerY;
    double radius;

    double angle;

    Layer layer = null;

    private double mostLeftCache, mostRightCache, highestCache, lowestCache;

    public Sprite( Layer layer, Vector2D location, Vector2D velocity, Vector2D acceleration, double width, double height) {

        this.layer = layer; 

        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.width = width;
        this.height = height;
        this.centerX = width / 2;
        this.centerY = height / 2;

        this.view = createView();

        setPrefSize(width, height);

        // add view to this node
        getChildren().add( view);

        // add this node to layer
        layer.getChildren().add( this);

        this.mostLeftCache = 0;
        this.mostRightCache = 0;
        this.highestCache = 0;
        this.lowestCache = 0;
    }

    public abstract Node createView();

    public void applyForce(Vector2D force) {
        acceleration.add(force);
    }

    public void move() {

        // set velocity depending on acceleration
        velocity.add(acceleration);

        // limit velocity to max speed
        //velocity.limit(maxSpeed);

        // change location depending on velocity
        location.add(velocity);

        // angle: towards velocity (ie target)
        //angle = velocity.heading2D();

        // clear acceleration
        //acceleration.multiply(0);

        clearCache();
    }


    private void clearCache() {
        highestCache = 0;
        lowestCache = 0;
        mostLeftCache = 0;
        mostRightCache = 0;
    }

    /**
     * Update node position
     */
    public void display() {

        relocate(location.x - centerX, location.y - centerY);

        setRotate(angle);
    }

    public void displayWithoutRotation()
    {
        relocate(location.x - centerX, location.y - centerY);
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getLocation() {
        return location;
    }

    public void setLocation( double x, double y) {
        location.x = x;
        location.y = y;
        clearCache();
    }

    public void setLocationOffset( double x, double y) {
        location.x += x;
        location.y += y;
        clearCache();
    }

    public double getHighest()
    {
        if(highestCache == 0)
            highestCache = location.y - height/2;
        return highestCache;
    }

    public double getLowest()
    {
        if(lowestCache == 0)
            lowestCache = location.y + height/2;
            return lowestCache;
    }

    public double getMostLeft()
    {
        if(mostLeftCache == 0)
            mostLeftCache = location.x - width/2;
        return mostLeftCache;
    }

    public double getMostRight()
    {
        if(mostRightCache == 0)
            mostRightCache = location.x + width/2;
        return mostRightCache;
    }
}
