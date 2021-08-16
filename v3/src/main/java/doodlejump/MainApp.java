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
import java.util.ArrayList;
import java.util.List;

import doodlejump.Entity.CircleTest;
import doodlejump.Entity.Layer;
import doodlejump.Entity.Platform;
import doodlejump.Entity.Player;
import doodlejump.Entity.Sprite;
import doodlejump.Entity.Settings;
import doodlejump.Entity.Vector2D;


public class MainApp extends Application {
    //private static Stage stage;
    private Stage primaryStage;
    private Layer layer;
    private Player player;
    private int difficultyStage;
    private List<Platform> platforms;
    private AnimationTimer gameloop;

    public MainApp()
    {
        this.difficultyStage = 0;
        this.platforms = new ArrayList<>();
    }

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
                player.displayWithoutRotation();
                generateEnvironment();
                System.out.println(player.getLocation().y);
                
                if(player.getLocation().y > layer.heightProperty().floatValue())
                    stop();
                

                if(player.getVelocity().y > 0)
                    player.touching(platforms);

            }
            
        };
        gameloop.start();
    }

    private void generateStartingScenario() {
        player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40);
        player.display();
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        
        //platforms.add(randomPlatform());
        //platforms.add(randomPlatform());
        //platforms.add(randomPlatform());

        platforms.forEach(Platform::display);

    }

    private Platform randomPlatform()
    {

        return null;
    }

    private void generateEnvironment()
    {

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
