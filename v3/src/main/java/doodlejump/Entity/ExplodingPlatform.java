package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        if(costume == 2)
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\brokenPlatform.png").toURI().toString(), width, height, true, true));
        else if(costume == 1)
            return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\disappearingPlatform.png").toURI().toString(), width, height, true, true));
        else
            return super.createView();
    }

    @Override
    public void collide(Player player) {
        if(player.velocity.y > 0 && !disappeared && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
                player.jump();
    }

    @Override
    public void display() {
        frames++;
        if(frames > 300 && costume != 3)
        {
            costume = 3;
            layer.getChildren().remove(this);
            disappeared = true;
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
