package doodlejump.Entity;

import java.io.File;

import doodlejump.MainApp;
import doodlejump.Boundary.SoundManager;
import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Sprite{
    private boolean moveRight, moveLeft, bouncing, propeller, rocket, shoot, falling, dead;
    private double rightBorder, bounceProgression, propellerProgression, rocketProgession, shotProgression;
    private MainApp mainApp;
    private SoundManager soundaManager;

    public Player(Layer layer, Vector2D location, MainApp mainApp, SoundManager soundaManager) 
    {
        super(layer, location, new Vector2D(0, Settings.JUMP_VELOCITY), new Vector2D(0, Settings.GRAVITY), Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        this.rightBorder = Settings.SCENE_WIDTH;
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
        this.mainApp = mainApp;
        this.soundaManager = soundaManager;
    }

    @Override
    public Node createView() {
        if(propeller)
        {
            Group group = new Group();
            ImageView propeller = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\propellerHat.png").toURI().toString(), 
            Settings.PROPELLER_WIDTH, Settings.PROPELLER_HEIGHT, true, true));
            group.getChildren().add(new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\penguin.png").toURI().toString(), width, height, true, true)));
            group.getChildren().add(propeller);
            propeller.setLayoutY(-Settings.PROPELLER_HEIGHT);
            propeller.setLayoutX(10);
            return group;
        }
        else if(rocket)
        {
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\rocketFlying.png").toURI().toString(), 
            Settings.ROCKET_WIDTH, Settings.ROCKET_HEIGHT, true, true));
        }
        else if(shoot)
        {
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\penguinShoot.png").toURI().toString(), width, height, true, true));
        }
        else if(dead || falling)
        {
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\penguinDead.png").toURI().toString(), width, height, true, true));
        }
        else
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\penguin.png").toURI().toString(), width, height, true, true));
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
        soundaManager.playJump();
    }

    public void springJump() {
        velocity = new Vector2D(velocity.x, Settings.SPRING_JUMP_VELOCITY);
        soundaManager.playSpring();
    }

    public void bounceJump() {
        bouncing = true;
        velocity = new Vector2D(velocity.x, Settings.BOUNCE_JUMP_VELOCITY);
        soundaManager.playBounce();
    }

    public void propeller()
    {
        if(!rocket)
        {
            propeller = true;
            acceleration = new Vector2D(0, 0);
            velocity = new Vector2D(0, Settings.PROPELLER_SPEED);
            updateView();
            soundaManager.playPropeller();
        }
    }

    public void rocket() {
        if(!propeller)
        {
            rocket = true;
            acceleration = new Vector2D(0, Settings.ROCKET_ACCELERATION);
            velocity = new Vector2D(0, Settings.ROCKET_SPEED);
            updateView();
            soundaManager.playRocket();
        }
    }

    public void shoot()
    {
        if(!propeller && !bouncing)
        {
            shoot = true;
            mainApp.generateProjectile();
            shotProgression = 0;
            updateView();
            soundaManager.playShot();
        }
    }

    public void setFalling()
    {
        this.falling = true;
        updateView();
        soundaManager.playFalling();
    }

    public boolean getFalling()
    {
        return falling;
    }

    public void setDead()
    {
        velocity = new Vector2D(0, 0);
        dead = true;
    }

    public boolean isDead()
    {
        return dead;
    }

    public boolean getRocket() {
        return rocket;
    }

    public boolean getPropeller() {
        return propeller;
    }

}
