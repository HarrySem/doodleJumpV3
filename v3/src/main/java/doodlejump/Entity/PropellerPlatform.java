package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class PropellerPlatform extends Platform{

    private boolean propellerUsed;
    public PropellerPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.propellerUsed = false;
    }

    @Override
    public Node createView() {
        if(propellerUsed)
            return super.createView();
        else
        {
            Group group = new Group();
            Rectangle rectangle = (Rectangle) super.createView();
            Rectangle propeller = new Rectangle(20, 20, Paint.valueOf("green"));
            group.getChildren().add(rectangle);
            group.getChildren().add(propeller);
            propeller.setLayoutY(-Settings.PROPELLER_HEIGHT);
            propeller.setLayoutX(Settings.PROPELLER_WIDTH);
            return group;
        }
    }

    @Override
    public void collide(Player player) {
        if(player.getLowest() > getHighest()-Settings.PROPELLER_HEIGHT && player.getHighest() < getHighest() && 
            player.getMostRight() > getMostLeft()+Settings.PROPELLER_WIDTH && player.getMostLeft() < getMostLeft()+2*Settings.PROPELLER_WIDTH)
        {
            player.propeller();
            propellerUsed = true;
            updateView();
        }
        else
            super.collide(player);
    }
    
}
