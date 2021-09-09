package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
            ImageView propeller = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\propellerHat.png").toURI().toString(), 
            Settings.PROPELLER_WIDTH, Settings.PROPELLER_HEIGHT, true, true));
            group.getChildren().add(super.createView());
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
