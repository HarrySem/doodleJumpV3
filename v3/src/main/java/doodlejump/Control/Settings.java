package doodlejump.Control;

public class Settings {
    public static final String HIGHSCORE_FILENAME = "v3\\src\\main\\java\\doodlejump\\highscore.txt";

    public static final double SCENE_WIDTH = 1000;
    public static final double SCENE_HEIGHT = 500;

    public static final double SPRITE_MAX_SPEED = -25;
    public static final double SPRITE_MAX_FORCE = 0.1;

    public static final double GRAVITY = 0.2;
    public static final double JUMP_VELOCITY = -8;

    public static final double PLATFORM_WIDTH = 70;
    public static final double PLATFORM_HIGHT = 10;

    public static final double SHIFT_LINE = 200;
    public static final double BASE_LINE = SCENE_HEIGHT;

    public static final double RIGHT_ARROW_SPEED = 8;
    public static final double LEFT_ARROW_SPEED = -RIGHT_ARROW_SPEED;

    public static final double DIFFICULTY_INCREASE = 1200;
    public static final double PLATFORM_BUFFER = 50;
    public static final double PLAYER_BUFFER = 10;
    public static final double MAX_SPAWNDISTANCE = 110;

    //Spring Platform
    public static final double SPRINGS_WIDTH = 20;
    public static final double SPRING_HEIGHT = 10;
    public static final double SPRING_JUMP_VELOCITY = -16;

    //Bouncing Platform
    public static final double BOUNCE_WIDTH = 30;
    public static final double BOUNCE_HEIGHT = 20;
    public static final int BOUNCE_SPIN_SPEED = 5;
    public static final double BOUNCE_JUMP_VELOCITY = -14;

    //Moving Platform
    public static final double MOVE_DISTANCE_PLATFORM = 150;
    public static final double MOVING_PLATFORM_SPEED = 2;

    //Propeller
    public static final int PROPELLER_DURATION = 200;
    public static final double PROPELLER_HEIGHT = 20;
    public static final double PROPELLER_WIDTH = 20;
    public static final double PROPELLER_SPEED = -8;

    //Rocket
    public static final double ROCKET_HEIGHT = 50;
    public static final double ROCKET_WIDTH = 30;
    public static final double ROCKET_ACCELERATION = -0.2;
    public static final double ROCKET_SPEED = -5;
    public static final int ROCKET_DURATION = 150;

    //Spawnrate per 100
    public static final int SPAWNRATE_SPRING = 4;
    public static final int SPAWNRATE_BOUNCE = 2;
    public static final int SPAWNRATE_MOVING_HORIZONTAL = 2;
    public static final int SPAWNRATE_MOVING_VERTIKAL = 1;
    public static final int SPAWNRATE_PROPELLER = 1;
    public static final int SPAWNRATE_ROCKET = 1;
    public static final int SPAWNRATE_EVENT = 10;
    public static final int SPAWNRATE_DISAPPEARING = 3;
    public static final int SPAWNRATE_EXPLODING = 3;

    //Difficulty stage at which things start spawning
    public static final int SPAWNSTART_SPRING = 1;
    public static final int SPAWNSTART_BOUNCE = 2;
    public static final int SPAWNSTART_MOVING_HORIZONTAL = 3;
    public static final int SPAWNSTART_MOVING_VERTIKAL = 4;
    public static final int SPAWNSTART_PROPELLER = 2;
    public static final int SPAWNSTART_ROCKET = 5;
    public static final int SPAWNSTART_EVENT = 5;
    public static final int SPAWNSTART_DISAPPEARING = 3;
    public static final int SPAWNSTART_EXPLODING = 3;

    public static final int EVENT_DURATION = 12;

    public static final int SHOT_DURATION = 5;

    public static final double SHOT_VELOCITY = -10;
    public static final double SHOT_WIDTH = 5;
    public static final double SHOT_HEIGHT = 10;

    public static final int FALLING_DURATION = 100;

    public static final double ENEMY_WIDTH = 200;
    public static final double ENEMY_HEIGHT = 100;
    public static final double ENEMY_SPAWN_LEFT = SCENE_WIDTH/4;
    public static final double ENEMY_SPAWN_RIGHT = SCENE_WIDTH*3/4;
    public static final double ENEMY_SPEED = 5;
    public static final int ENEMY_MOVEMENT_DURATION = 6;

}
