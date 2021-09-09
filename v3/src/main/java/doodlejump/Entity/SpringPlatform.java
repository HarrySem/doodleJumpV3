package doodlejump.Entity;

import java.io.File;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpringPlatform extends Platform{

    private boolean sprung;

    public SpringPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
        this.sprung = false;
    }

    @Override
    public Node createView() {
        if(sprung)
        {
            Group group = new Group();
            ImageView spring = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\springOpen.png").toURI().toString(), 
            Settings.SPRING_WIDTH_OPEN, Settings.SPRING_HEIGHT_OPEN, true, true));
            group.getChildren().add(spring);
            group.getChildren().add(super.createView());
            spring.setLayoutY(-Settings.SPRING_HEIGHT_OPEN);
            return group;
        }
        else
        {
            Group group = new Group();
            ImageView spring = new ImageView(new Image(new File("v3\\src\\main\\resources\\img\\springClosed.png").toURI().toString(), 
            Settings.SPRING_WIDTH, Settings.SPRING_HEIGHT, true, true));
            group.getChildren().add(spring);
            group.getChildren().add(super.createView());
            spring.setLayoutY(-Settings.SPRING_HEIGHT);
            return group;
        }
    }

    @Override
    public void collide(Player player) {
        if(player.velocity.y > 0 && player.getLowest() > getHighest()-Settings.SPRING_HEIGHT && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostLeft()+Settings.SPRING_WIDTH)
        {
            player.springJump();
            sprung = true;
            updateView();
        }
        else
            super.collide(player);
    }
    
}
