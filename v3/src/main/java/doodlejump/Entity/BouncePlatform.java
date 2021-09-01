package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class BouncePlatform extends Platform{

    public BouncePlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
    }
    
    @Override
    public Node createView() {
        Group group = new Group();
        Rectangle bounce = new Rectangle(Settings.BOUNCE_WIDTH, Settings.BOUNCE_HEIGHT);
        bounce.setFill(Paint.valueOf("blue"));
        group.getChildren().add(bounce);
        group.getChildren().add(new Rectangle(width, height));
        bounce.setLayoutY(-10);
        bounce.setLayoutX(Settings.PLATFORM_WIDTH-Settings.BOUNCE_WIDTH);
        return group;
    }

    @Override
    public void collide(Player player) {
        if(player.getLowest() > getHighest()-Settings.BOUNCE_HEIGHT && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostRight()-Settings.BOUNCE_WIDTH && player.getMostLeft() < getMostRight())
            player.bounceJump();
        else
            super.collide(player);
    }
}
