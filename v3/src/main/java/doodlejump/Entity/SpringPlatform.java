package doodlejump.Entity;

import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SpringPlatform extends Platform{

    public SpringPlatform(Layer layer, Vector2D location, double width, double height) {
        super(layer, location, width, height);
    }

    @Override
    public Node createView() {
        Group group = new Group();
        Rectangle spring = new Rectangle(Settings.SPRINGS_WIDTH, Settings.SPRING_HEIGHT);
        spring.setFill(Paint.valueOf("red"));
        group.getChildren().add(spring);
        group.getChildren().add(new Rectangle(width, height));
        spring.setLayoutY(-10);
        return group;
    }

    @Override
    public void collide(Player player) {
        if(player.velocity.y > 0 && player.getLowest() > getHighest()-Settings.SPRING_HEIGHT && player.getLowest() < getLowest() && 
            player.getMostRight() > getMostLeft() && player.getMostLeft() < getMostLeft()+Settings.SPRINGS_WIDTH)
            player.springJump();
        else
            super.collide(player);
    }
    
}
