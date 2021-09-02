package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PropellerPlatform extends Platform{

    public PropellerPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
    }

    @Override
    public Node createView() {
        Group group = new Group();
        Rectangle rectangle = (Rectangle) super.createView();
        Rectangle propeller = new Rectangle(20, 20, Paint.valueOf("green"));
        group.getChildren().add(rectangle);
        group.getChildren().add(propeller);
        propeller.setLayoutY(-Settings.PROPELLER_HEIGHT);
        propeller.setLayoutX(Settings.PROPELLER_WIDTH);
        return group;
    }

    @Override
    public void collide(Player player) {
        if(player.getLowest() > getHighest()-Settings.PROPELLER_HEIGHT && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft()+Settings.PROPELLER_WIDTH && player.getMostLeft() < getMostLeft()+2*Settings.PROPELLER_WIDTH)
            player.propeller();
        else
            super.collide(player);
    }
    
}
