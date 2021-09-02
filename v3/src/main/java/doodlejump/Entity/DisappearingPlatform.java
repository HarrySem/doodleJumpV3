package doodlejump.Entity;

import doodlejump.Control.Vector2D;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class DisappearingPlatform extends Platform{

    private boolean disappeared;

    public DisappearingPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.disappeared = false;
    }
    @Override
    public Node createView() {
        Rectangle rectangle = (Rectangle)super.createView();
        rectangle.setOpacity(0.5);
        return rectangle;
    }

    @Override
    public void collide(Player player) {
        if(!disappeared && player.getLowest() > getHighest() && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostRight())
            {
                player.jump();
                disappeared = true;
                layer.getChildren().remove(this);
            }
    }

}
