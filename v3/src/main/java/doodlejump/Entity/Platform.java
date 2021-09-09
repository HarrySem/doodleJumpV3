package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Platform extends Sprite{

    public Platform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, new Vector2D(0, 0), new Vector2D(0, 0), width, height);
        }

    @Override
    public Node createView() {
        //return new Rectangle(width, height);
        return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\platform.png").toURI().toString(), width, height, true, true));
    }

    public void collide(Player player)
    {
        if(player.velocity.y > 0 && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
            player.jump();
    }
    
}
