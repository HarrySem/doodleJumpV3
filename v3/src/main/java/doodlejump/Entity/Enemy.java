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

    public Enemy(Layer layer, Vector2D location) {
        super(layer, location, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        this.random = new Random();
        this.direction = random.nextInt(3);
        this.movementProgression = 0;
    }

    @Override
    public Node createView() {
        return new Rectangle(width, height, Paint.valueOf("purple"));
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
        if(player.velocity.y > 0 && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
            {
                acceleration = new Vector2D(0, Settings.GRAVITY*2);
                player.jump();
            }
        else if(player.velocity.y < 0 && player.getHighest() < getLowest() && player.getHighest() > getHighest() &&
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
                player.setFalling(true);
    }
    
}
