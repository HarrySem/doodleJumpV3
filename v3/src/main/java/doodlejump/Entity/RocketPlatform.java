package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RocketPlatform extends Platform{

    private boolean rocketUsed;

    public RocketPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.rocketUsed = false;
    }

    @Override
    public Node createView() {
        if(rocketUsed)
            return super.createView();
        else
        {
            Group group = new Group();
            ImageView rocket = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\rocketStanding.png").toURI().toString(), 
            Settings.ROCKET_WIDTH, Settings.ROCKET_HEIGHT, true, true));
            group.getChildren().add(super.createView());
            group.getChildren().add(rocket);
            rocket.setLayoutY(-Settings.ROCKET_HEIGHT);
            rocket.setLayoutX(Settings.ROCKET_WIDTH);
            return group;
        }
    }

    @Override
    public void collide(Player player) {
        if(player.getLowest() > getHighest()-Settings.ROCKET_HEIGHT && player.getHighest() < getHighest() && 
            player.getMostRight() > getMostLeft()+Settings.ROCKET_WIDTH && player.getMostLeft() < getMostLeft()+2*Settings.ROCKET_WIDTH)
        {
            player.rocket();
            rocketUsed = true;
            updateView();
        }
        else
            super.collide(player);
    }
    
}
