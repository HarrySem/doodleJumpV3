package doodlejump.Entity;

import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ExplodingPlatform extends Platform{

    private boolean disappeared;
    private int frames;
    private int costume;

    public ExplodingPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.disappeared = false;
        this.frames = 0;
        this.costume = 0;
    }

    @Override
    public Node createView() {
        Rectangle rectangle = (Rectangle)super.createView();
        if(costume == 3)
        {
            rectangle.setOpacity(0);
            disappeared = true;
            layer.getChildren().remove(this);
        }
        else if(costume == 2)
            rectangle.setFill(Paint.valueOf("purple"));
        else if(costume == 1)
            rectangle.setFill(Paint.valueOf("red"));
        else
            rectangle.setFill(Paint.valueOf("orange"));
        return rectangle;
    }

    @Override
    public void collide(Player player) {
        if(!disappeared && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
                player.jump();
    }

    @Override
    public void display() {
        frames++;
        if(frames > 300 && costume != 3)
        {
            costume = 3;
            updateView();
        }
        else if(frames > 200 && costume != 2)
        {
            costume = 2;
            updateView();
        }
        else if(frames > 100 && costume != 1)
        {
            costume = 1;
            updateView();
        }
        super.display();
    }
}
