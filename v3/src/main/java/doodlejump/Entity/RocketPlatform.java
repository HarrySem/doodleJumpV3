package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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
            Rectangle propeller = new Rectangle(Settings.ROCKET_WIDTH, Settings.ROCKET_HEIGHT, Paint.valueOf("purple"));
            group.getChildren().add(super.createView());
            group.getChildren().add(propeller);
            propeller.setLayoutY(-Settings.ROCKET_HEIGHT);
            propeller.setLayoutX(Settings.ROCKET_WIDTH);
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
