package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BouncePlatform extends Platform{

    public BouncePlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
    }
    
    @Override
    public Node createView() {
        Group group = new Group();
        ImageView bounce = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\bounce.png").toURI().toString(), 
        Settings.BOUNCE_WIDTH, Settings.BOUNCE_HEIGHT, true, true));
        group.getChildren().add(bounce);
        group.getChildren().add(super.createView());
        bounce.setLayoutY(-Settings.BOUNCE_HEIGHT);
        bounce.setLayoutX(Settings.PLATFORM_WIDTH-Settings.BOUNCE_WIDTH);
        return group;
    }

    @Override
    public void collide(Player player) {
        if(player.velocity.y > 0 && player.getLowest() > getHighest()-Settings.BOUNCE_HEIGHT && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostRight()-Settings.BOUNCE_WIDTH && player.getMostLeft() < getMostRight())
            player.bounceJump();
        else
            super.collide(player);
    }
}
