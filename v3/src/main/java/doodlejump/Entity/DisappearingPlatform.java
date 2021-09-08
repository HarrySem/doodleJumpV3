package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class DisappearingPlatform extends Platform{

    private boolean disappeared;

    public DisappearingPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.disappeared = false;
    }
    @Override
    public Node createView() {
        return new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\disappearingPlatform.png").toURI().toString(), width, height, true, true));
    }

    @Override
    public void collide(Player player) {
        if(player.velocity.y > 0 && !disappeared && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
            {
                player.jump();
                disappeared = true;
                layer.getChildren().remove(this);
            }
    }

}
