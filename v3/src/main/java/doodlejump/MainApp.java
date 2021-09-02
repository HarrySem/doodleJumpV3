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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import doodlejump.Boundary.*;
import doodlejump.Control.*;
import doodlejump.Entity.*;



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
        this.highscore = loadHighscore();
        this.scoreLabel = new Label("Score: " + score);
        this.highscoreLabel = new Label("Highscore " + (int)highscore);
        highscoreLabel.setLayoutY(50);
        this.mainMenuController = new MainMenuController(this);
        this.difficultyStage = 1;
        this.spwanDistance = 15;
    }

    private void increaseDifficulty()
    {
        difficultyStage++;
        spwanDistance = difficultyStage*10;
        if(spwanDistance > Settings.MAX_SPAWNDISTANCE)
            spwanDistance = Settings.MAX_SPAWNDISTANCE;
    }

    private static int loadHighscore()
    {
        int i;
        File file = new File(Settings.HIGHSCORE_FILENAME);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            i = scanner.nextInt();
            
            scanner.close();
        } catch (FileNotFoundException e) {
            i = 0;
        }
        return i;
    }

    private static void writeHighscore(int i) throws IOException
    {
        PrintWriter writer = new PrintWriter(Settings.HIGHSCORE_FILENAME);
        writer.print(i);
        writer.close();
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
        //generateStartingScenario();       //commment out for test purposes
        generateTestScenario();
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
                player.display();
                platforms.forEach(Platform::display);

                if(player.getLocation().y > layer.heightProperty().floatValue())
                {
                    if(score > loadHighscore())
                        try {
                            writeHighscore((int)score);
                        } catch (IOException e) {
                            System.out.println("Couldnt save highscore");
                        }
                    stop();
                }

                if(player.getVelocity().y > 0)
                    platforms.forEach(x -> x.collide(player));
            }
            
        };
        gameloop.start();
    }

    private void generateStartingScenario() {
        player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40, layer.getWidth());
        inputManger.setPlayer(player);
        player.display();
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 150), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 80), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
    }

    private void generateTestScenario()
    {
        player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40, layer.getWidth());
        inputManger.setPlayer(player);
        player.display();
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new PropellerPlatform(layer, new Vector2D(player.getLocation().x+350, player.getLocation().y-100),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
    }

    private void generateEnvironment()
    {
        //generateEnvironmentLinear();
        if(platforms.get(platforms.size()-1).getLocation().y > spwanDistance)
        {
            platforms.add(nextPlatform());
        }
    }

    private Platform nextPlatform()
    {            //TODO: add different platform options (disappearing/ moving/ etc) -> spawnrate dependent on difficultyStage
        Random random = new Random();
        Vector2D location = new Vector2D(random.nextInt((int)(layer.getPrefWidth() - 2*Settings.PLATFORM_BUFFER)) + Settings.PLATFORM_BUFFER, 0);
        switch(difficultyStage)
        {
            case 1:

                return new Platform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);


            default:
            int i = random.nextInt(20);
            if(i == 0)
                return new SpringPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 1)
                return new BouncePlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 2)
                return new DisappearingPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 3)
                return new ExplodingPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 4)
                return new MovingPlatformHorizontal(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 5)
                return new MovingPlatformVertical(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else if(i == 6)
                return new PropellerPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
            else
                return new Platform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        }
    }

    //test method
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
            scoreLabel = new Label("Score: " + (int)score);
            layer.getChildren().add(scoreLabel);
            //adjust difficulty
            if(score/difficultyStage > Settings.DIFFICULTY_INCREASE)
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
        primaryStage.close();
	}

    public Player getPlayer(){
        return this.player;
    }

	public void restart() {
        gameloop.stop();
        score = 0;
        highscore = loadHighscore();
        highscoreLabel = new Label("Highscore " + (int)highscore);
        highscoreLabel.setLayoutY(50);
        difficultyStage = 1;
        spwanDistance = 15;
        platforms.clear();
        startGame();
	}

}
