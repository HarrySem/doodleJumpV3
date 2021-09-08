package doodlejump.Control;

import doodlejump.Entity.Layer;

public class DynamicSettings {
    private Layer layer;

    public DynamicSettings(Layer layer)
    {
        this.layer = layer;
    }

    public DynamicSettings()
    {
        this.layer = new Layer(1000, 500);
    }

    public void setLayer(Layer layer)
    {
        this.layer = layer;
    }

    public String HIGHSCORE_FILENAME = "v3\\src\\main\\java\\doodlejump\\highscore.txt";

    public double SCENE_WIDTH = layer.getWidth();
    public double SCENE_HEIGHT = layer.getHeight();

    public double SPRITE_MAX_SPEED_Y = -25 /500 * layer.getHeight();

    public double GRAVITY = 0.2 /500 * layer.getHeight();
    public double JUMP_VELOCITY = -8 /500 * layer.getHeight();

    public double PLATFORM_WIDTH = 70 /1000 * layer.getWidth();
    public double PLATFORM_HIGHT = 10 /500 * layer.getHeight();

    public double SHIFT_LINE = 200 /500 * layer.getHeight();
    public double BASE_LINE = layer.getHeight();

    public double RIGHT_ARROW_SPEED = 8 /1000 * layer.getWidth();
    public double LEFT_ARROW_SPEED = -RIGHT_ARROW_SPEED;

    public double DIFFICULTY_INCREASE = 1200;
    public double PLATFORM_BUFFER = 50 /1000 * layer.getWidth();
    public double PLAYER_BUFFER = 10 /1000 * layer.getWidth();
    public double MAX_SPAWNDISTANCE = 110 /500 * layer.getHeight();

    //Spring Platform
    public double SPRINGS_WIDTH = 20 /1000 * layer.getWidth();
    public double SPRING_HEIGHT = 10 /500 * layer.getHeight();
    public double SPRING_JUMP_VELOCITY = -16 /500 * layer.getHeight();

    //Bouncing Platform
    public double BOUNCE_WIDTH = 30 /1000 * layer.getWidth();
    public double BOUNCE_HEIGHT = 20 /500 * layer.getHeight();
    public int BOUNCE_SPIN_SPEED = 5;
    public double BOUNCE_JUMP_VELOCITY = -14 /500 * layer.getHeight();

    //Moving Platform
    public double MOVE_DISTANCE_PLATFORM = 150; //TODO: differentialte vertical horizontal
    public double MOVING_PLATFORM_SPEED = 2;

    //Propeller
    public int PROPELLER_DURATION = 200;
    public double PROPELLER_HEIGHT = 20 /500 * layer.getHeight();
    public double PROPELLER_WIDTH = 20 /1000 * layer.getWidth();
    public double PROPELLER_SPEED = -8 /500 * layer.getHeight();

    //Rocket
    public double ROCKET_HEIGHT = 50 /500 * layer.getHeight();
    public double ROCKET_WIDTH = 30 /1000 * layer.getWidth();
    public double ROCKET_ACCELERATION = -0.2 /500 * layer.getHeight();
    public double ROCKET_SPEED = -5 /500 * layer.getHeight();
    public int ROCKET_DURATION = 150;

    //Spawnrate per 100
    public int SPAWNRATE_SPRING = 4;
    public int SPAWNRATE_BOUNCE = 2;
    public int SPAWNRATE_MOVING_HORIZONTAL = 2;
    public int SPAWNRATE_MOVING_VERTIKAL = 1;
    public int SPAWNRATE_PROPELLER = 1;
    public int SPAWNRATE_ROCKET = 1;
    public int SPAWNRATE_EVENT = 10;
    public int SPAWNRATE_DISAPPEARING = 3;
    public int SPAWNRATE_EXPLODING = 3;

    //Difficulty stage at which things start spawning
    public int SPAWNSTART_SPRING = 1;
    public int SPAWNSTART_BOUNCE = 2;
    public int SPAWNSTART_MOVING_HORIZONTAL = 3;
    public int SPAWNSTART_MOVING_VERTIKAL = 4;
    public int SPAWNSTART_PROPELLER = 2;
    public int SPAWNSTART_ROCKET = 5;
    public int SPAWNSTART_EVENT = 5;
    public int SPAWNSTART_DISAPPEARING = 3;
    public int SPAWNSTART_EXPLODING = 3;

    public int EVENT_DURATION = 12;

    public int SHOT_DURATION = 5;

    public double SHOT_VELOCITY = -15 /500 * layer.getHeight();
    public double SHOT_WIDTH = 5 /1000 * layer.getWidth();
    public double SHOT_HEIGHT = 10 /500 * layer.getHeight();

    public int FALLING_DURATION = 100;

    public double ENEMY_WIDTH = 200 /1000 * layer.getWidth();
    public double ENEMY_HEIGHT = 100 /500 * layer.getHeight();
    public double ENEMY_SPAWN_LEFT = SCENE_WIDTH/4;
    public double ENEMY_SPAWN_RIGHT = SCENE_WIDTH*3/4;
    public double ENEMY_SPEED = 2;
    public int ENEMY_MOVEMENT_DURATION = 6;
}
