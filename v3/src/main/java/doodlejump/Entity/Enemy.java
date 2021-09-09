package doodlejump.Entity;

import java.util.Random;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Enemy extends Platform{

    private int movementProgression;
    private int direction;
    private Random random;
    private boolean shot;

    public Enemy(Layer layer, Vector2D location) {
        super(layer, location, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        this.random = new Random();
        this.direction = random.nextInt(3);
        this.movementProgression = 0;
        this.shot = false;
    }

    @Override
    public Node createView() {
        Rectangle rectangle = new Rectangle(width, height, Paint.valueOf("purple"));
        if(shot)
            rectangle.setOpacity(0);
        return rectangle;
    }

    @Override
    public void display() {
        movementProgression++;
        switch(direction)
        {
            case 0:
                setLocationOffset(0, -Settings.ENEMY_SPEED);
                break;
            case 1:
                setLocationOffset(Settings.ENEMY_SPEED, 0);
                break;
            case 2:
                setLocationOffset(0, Settings.ENEMY_SPEED);
                break;
            case 3:
                setLocationOffset(-Settings.ENEMY_SPEED, 0);
                break;
        }
        if(movementProgression == Settings.ENEMY_MOVEMENT_DURATION/2)
        {
            direction += 2;
            if(direction == 4)
                direction = 0;
            else if(direction == 5)
                direction = 1;
        }
        else if(movementProgression >= Settings.ENEMY_MOVEMENT_DURATION)
        {
            movementProgression = 0;
            direction = random.nextInt(4);
        }
        super.display();
    }

    @Override
    public void collide(Player player) {
        
        if(player.getLowest() < getHighest() || shot)
            return;
        else if(player.getHighest() < getLowest() && player.getLowest() > getHighest() + Settings.PLATFORM_HIGHT &&
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
        {
            if(player.getRocket() || player.getPropeller())
                acceleration = new Vector2D(0, Settings.GRAVITY*2);
            else
            {
                player.setDead();
                player.updateView();
            }
        }
        else if(player.velocity.y > 0 && player.getLowest() > getHighest() && player.getLowest() < getHighest()
         + Settings.PLATFORM_HIGHT &&
        player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
        {
            acceleration = new Vector2D(0, Settings.GRAVITY*2);
            player.jump();
        }

    }

    public void shot()
    {
        shot = true;
        updateView();
    }
    
}
