package doodlejump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

import doodlejump.Entity.CircleTest;
import doodlejump.Entity.Layer;
import doodlejump.Entity.Player;
import doodlejump.Entity.Vector2D;


public class MainApp extends Application {
    //private static Stage stage;
    private Stage primaryStage;
    private Layer layer;
    private Player player;
    private AnimationTimer gameloop;

    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        layer = new Layer(1000, 500);
        Scene scene = new Scene(layer);
        primaryStage.setScene(scene);
        
        generateStartingScenario();

        startGame();

        primaryStage.show();
    }

    private void startGame() {
        gameloop = new AnimationTimer(){

            @Override
            public void handle(long now) {
                player.move();
                player.display();                
            }
            
        };
        gameloop.start();
    }

    private void generateStartingScenario() {
        player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40);
        player.display();
    }

    private void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
        }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
