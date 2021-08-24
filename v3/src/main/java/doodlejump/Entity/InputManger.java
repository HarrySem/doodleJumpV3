package doodlejump.Entity;

import doodlejump.MainApp;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManger implements EventHandler<KeyEvent>{

    private MainApp mainApp;
    private Player player;

    public InputManger(MainApp mainApp)
    {
        this.mainApp = mainApp;
        this.player = mainApp.getPlayer();
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode input = event.getCode();
        if(input.equals(KeyCode.ESCAPE))
        {
            mainApp.close();
        }
        if(event.getEventType().equals(KeyEvent.KEY_PRESSED))
        {
            if(input.equals(KeyCode.RIGHT))
                player.setRight();
            if(input.equals(KeyCode.LEFT))
                player.setLeft();
        }
        else if(event.getEventType().equals(KeyEvent.KEY_RELEASED))
        {
            if(input.equals(KeyCode.RIGHT))
                player.releaseRight();
            if(input.equals(KeyCode.LEFT))
                player.releaseLeft();
        }

    }
    
}
