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
    private AnimationTimer gameloop;
    private double shiftLine, baseLine, score, highscore;
    private InputManger inputManger;
    private Label scoreLabel, highscoreLabel;
    private MainMenuController mainMenuController;
    private PlatformGenerator platformGenerator;
    private boolean gameOver;
    private int fallingProgression;
    private List<Platform> platforms;
    private List<Enemy> enemies;
    private List<Projectile> projectiles;
    

    public MainApp()
    {
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
        this.spwanDistance = 15;
        this.difficultyStage = 0;
        this.projectiles = new ArrayList<>();
        this.gameOver = false;
        this.fallingProgression = 0;
        this.enemies = new ArrayList<>();
    }

    private void increaseDifficulty()
    {
        difficultyStage++;
        platformGenerator.increaseDifficulty();
        spwanDistance += 10;
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
        layer = new Layer(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        layer.getChildren().add(scoreLabel);
        layer.getChildren().add(highscoreLabel);
        Scene scene = new Scene(layer);
        scene.addEventHandler(KeyEvent.ANY, inputManger);
        primaryStage.setScene(scene);
        this.platformGenerator = new PlatformGenerator(this);

        //generate starting scenario
        player = platformGenerator.generateStartingScenario(platforms);
        //player = platformGenerator.generateTestScenario(platforms);

        inputManger.setPlayer(player);
        primaryStage.show();
        startGameLoop();
    }

    private void startGameLoop() {

        gameloop = new AnimationTimer(){

            @Override
            public void handle(long now) {
                player.move();
                player.display();

                generateEnvironment();
                shiftEnvironment();

                platforms.forEach(x -> {
                    x.display();
                    if(!player.isDead())
                        x.collide(player);
                });

                projectiles.forEach(x -> {
                    x.move();
                    x.display();
                    x.hit(enemies);
                });

                enemies.forEach(x -> {
                    x.move();
                    x.display();
                    if(!player.isDead())
                        x.collide(player);
                });

                if(player.getLocation().y > layer.heightProperty().floatValue())
                    player.setFalling();
                if(gameOver)
                {
                    if(score > loadHighscore())
                        try {
                            writeHighscore((int)score);
                        } catch (IOException e) {
                            System.out.println("Couldnt save highscore");
                        }
                    stop();
                }
            }
            
        };
        gameloop.start();
    }

    private void generateEnvironment()
    {
        //generateEnvironmentLinear();
        if(platforms.get(platforms.size()-1).getLocation().y > spwanDistance)
            platforms.add(platformGenerator.nextPlatform());
    }

    private void shiftEnvironment()
    {
        if(player.getFalling())
        {
            fallingProgression++;
            platforms.forEach(x -> x.setLocationOffset(0, -player.getVelocity().y));
            enemies.forEach(x -> x.setLocationOffset(0, -player.getVelocity().y));
            player.setLocationOffset(0, -20);
            if(fallingProgression >= Settings.FALLING_DURATION)
                gameOver = true;

        }
        else if(player.getLocation().y < shiftLine)
        {
            //shift entire environment
            platforms.forEach(x -> x.setLocationOffset(0, -player.getVelocity().y));
            enemies.forEach(x -> x.setLocationOffset(0, -player.getVelocity().y));
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
        cleanUp();
    }

    private void cleanUp()
    {
        if(platforms.get(0).getLocation().y > baseLine)
        {
            layer.getChildren().remove(platforms.get(0));
            platforms.remove(0);
        }
        if(!projectiles.isEmpty() && projectiles.get(0).getLocation().y < 0)
        {
            layer.getChildren().remove(projectiles.get(0));
            projectiles.remove(0);
        }
        if(!enemies.isEmpty() && enemies.get(0).getLocation().y > Settings.SCENE_HEIGHT + Settings.ENEMY_HEIGHT)
        {
            layer.getChildren().remove(enemies.get(0));
            enemies.remove(0);
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
        gameOver = false;
        fallingProgression = 0;
        platforms.clear();
        startGame();
	}

    public void generateProjectile() {
        projectiles.add(new Projectile(layer, player.getLocation()));
    }

    public Layer getLayer()
    {
        return this.layer;
    }

    public void addEnemy(Enemy enemy)
    {
        enemies.add(enemy);
    }

}
