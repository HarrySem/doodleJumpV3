package doodlejump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

import doodlejump.Boundary.InputManger;
import doodlejump.Boundary.MainMenuController;
import doodlejump.Control.Settings;
import doodlejump.Control.Vector2D;
import doodlejump.Entity.Layer;
import doodlejump.Entity.Platform;
import doodlejump.Entity.Player;



public class MainApp extends Application {
    //private static Stage stage;
    private Stage primaryStage;
    private Layer layer;
    private Player player;
    private int difficultyStage;
    private double spwanDistance;
    private List<Platform> platforms;
    private AnimationTimer gameloop;
    private double shiftLine;
    private double baseLine;
    private InputManger inputManger;
    private double score, highscore;
    private Label scoreLabel, highscoreLabel;
    private MainMenuController mainMenuController;

    public MainApp()
    {
        this.difficultyStage = 0;
        this.platforms = new ArrayList<>();
        this.shiftLine = Settings.SHIFT_LINE;
        this.baseLine = Settings.BASE_LINE;
        this.inputManger = new InputManger(this);
        this.score = 0;
        //this.highscore = loadHighscore(); TODO: implement loadHighscore()
        this.highscore = 0;
        this.scoreLabel = new Label("Score: " + score);
        this.highscoreLabel = new Label("Highscore " + highscore);
        highscoreLabel.setLayoutY(50);
        this.mainMenuController = new MainMenuController(this);
        this.difficultyStage = 1;
        this.spwanDistance = 10;
    }

    private void increaseDifficulty()
    {
        difficultyStage++;
        spwanDistance = difficultyStage*10;
    }

    private static int loadHighscore()
    {
        int i;
        File file = new File(Settings.HIGHSCORE_FILENAME);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            i = Integer.parseInt(scanner.next());
            scanner.close();
        } catch (FileNotFoundException e) {
            i = 0;
        }
        return i;
    }

    private static void writeHighscore(int i) throws IOException
    {
        File oldFile = new File(Settings.HIGHSCORE_FILENAME);
        oldFile.delete();
        File newFile = new File(Settings.HIGHSCORE_FILENAME);
        newFile.createNewFile();
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(i);
        fileWriter.close();
    }

    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        this.primaryStage = primaryStage;
        AnchorPane page = (AnchorPane) loadFXML("mainMenu", mainMenuController);
        Scene mainMenu = new Scene(page);
        mainMenu.addEventHandler(KeyEvent.KEY_PRESSED, inputManger);
        primaryStage.setScene(mainMenu);
        primaryStage.show();

    }

    public void startGame()
    {
        layer = new Layer(1000, baseLine);
        layer.getChildren().add(scoreLabel);
        layer.getChildren().add(highscoreLabel);
        Scene scene = new Scene(layer);
        scene.addEventHandler(KeyEvent.ANY, inputManger);
        primaryStage.setScene(scene);
        generateStartingScenario();
        primaryStage.show();
        startGameLoop();
    }

    private void startGameLoop() {



        gameloop = new AnimationTimer(){

            @Override
            public void handle(long now) {
                player.move();
                generateEnvironment();
                shiftEnvironment();
                player.displayWithoutRotation();
                platforms.forEach(Platform::display);

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
        //generateEnvironmentLinear();
        if(platforms.get(platforms.size()-1).getLocation().y > spwanDistance)
        {
            platforms.add(new Platform(layer, Vector2D.randomVector(layer.getPrefWidth()-Settings.PLATFORM_WIDTH, 0),
             Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        }
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
            //adjust score
            score -= player.getVelocity().y;
            layer.getChildren().remove(scoreLabel);
            scoreLabel = new Label("Highscore: " + (int)score);
            layer.getChildren().add(scoreLabel);
            //adjust difficulty
            if(score/difficultyStage > 1000)
                increaseDifficulty();
        }
        //remove platform out of frame
        if(platforms.get(0).getLocation().y > baseLine)
        {
            layer.getChildren().remove(platforms.get(0));
            platforms.remove(platforms.get(0));
        }
    }

    private static Parent loadFXML(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
        }

	public void close() {
        //if(score > 0)//TODO: implement loadHighscore()
                //writeHighscore((int)score);   //TODO: implement writeHighscore()
        primaryStage.close();
	}

    public Player getPlayer(){
        return this.player;
    }

}
