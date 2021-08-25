package doodlejump.Boundary;

import java.io.IOException;
import java.net.URL;

import doodlejump.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;


public class MainMenuController extends AnchorPane{

    private MainApp mainApp;

    public MainMenuController(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML
    private Button startGame;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    }

    @FXML
    public void startGame(ActionEvent event) throws IOException
    {
        //do stuff
        System.out.println("Game start COntroller");
        mainApp.startGame();
    }
}
