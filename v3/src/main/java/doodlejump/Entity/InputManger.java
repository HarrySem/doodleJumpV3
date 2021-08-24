package doodlejump.Entity;

import doodlejump.MainApp;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManger implements EventHandler<KeyEvent>{

    private MainApp mainApp;

    public InputManger(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ESCAPE))
        {
            mainApp.close();
        }
    }
    
}
