package doodlejump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import doodlejump.Entity.InputManger;
import doodlejump.Entity.Layer;
import doodlejump.Entity.Platform;
import doodlejump.Entity.Player;
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
    private double shiftLine;
    private double baseLine;
    private InputManger inputManger;
    private double highscore;
    private Label highscoreLabel;

    public MainApp()
    {
        this.difficultyStage = 0;
        this.platforms = new ArrayList<>();
        this.shiftLine = Settings.SHIFT_LINE;
        this.baseLine = Settings.BASE_LINE;
        this.inputManger = new InputManger(this);
        this.highscore = 0;
        this.highscoreLabel = new Label("Highscore: 0");
    }

    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        this.primaryStage = primaryStage;
        layer = new Layer(1000, baseLine);
        layer.getChildren().add(highscoreLabel);
        Scene scene = new Scene(layer);
        scene.addEventHandler(KeyEvent.ANY, inputManger);
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
                generateEnvironment();
                shiftEnvironment();
                player.displayWithoutRotation();
                platforms.forEach(Platform::display);
                //System.out.println(player.getLocation().y);
                System.out.println(highscore);

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
        inputManger.setPlayer(player);
        player.display();
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 150), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 80), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        
        //platforms.add(randomPlatform());
        //platforms.add(randomPlatform());
        //platforms.add(randomPlatform());

        platforms.forEach(Platform::display);

    }

    private void generateEnvironment()
    {
        generateEnvironmentLinear();
    }

    private void generateEnvironmentLinear()
    {
        double spwanDistance = 100;
        if(platforms.get(platforms.size()-1).getLocation().y > spwanDistance)
        {
            platforms.add(new Platform(layer, new Vector2D(layer.getPrefWidth()/2, 0),
             Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        }
    }

    private void shiftEnvironment()
    {
        if(player.getLocation().y < shiftLine)
        {
            //shift entire environment
            platforms.forEach(x -> x.setLocationOffset(0, player.getVelocity().y * (-1)));
            player.setLocationOffset(0, player.getVelocity().y * (-1));
            //adjust highscore
            highscore -= player.getVelocity().y;
            layer.getChildren().remove(highscoreLabel);
            highscoreLabel = new Label("Highscore: " + (int)highscore);
            layer.getChildren().add(highscoreLabel);
        }
        //remove platform out of frame
        if(platforms.get(0).getLocation().y > baseLine)
        {
            layer.getChildren().remove(platforms.get(0));
            platforms.remove(platforms.get(0));
        }
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

	public void close() {
        primaryStage.close();
        //System.out.println(primaryStage);
	}

    public Player getPlayer(){
        return this.player;
    }

}
