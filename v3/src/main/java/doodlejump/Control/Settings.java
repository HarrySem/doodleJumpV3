package doodlejump.Control;

public class Settings {
    public static double SCENE_WIDTH = 1280;
    public static double SCENE_HEIGHT = 720;

    public static int ATTRACTOR_COUNT = 1;
    public static int VEHICLE_COUNT = 10;

    public static double SPRITE_MAX_SPEED = 2;
    public static double SPRITE_MAX_FORCE = 0.1;

    // distance at which the sprite moves slower towards the target 
    public static double SPRITE_SLOW_DOWN_DISTANCE = 100; 

    public static double GRAVITY = 0.2;
    public static double JUMP_VELOCITY = -8;

    public static double PLATFORM_WIDTH = 70;
    public static double PLATFORM_HIGHT = 10;

    public static double SHIFT_LINE = 200;
    public static double BASE_LINE = 500;

    public static double RIGHT_ARROW_SPEED = 7;
    public static double LEFT_ARROW_SPEED = -RIGHT_ARROW_SPEED;

    public static final double DIFFICULTY_INCREASE = 1200;
    public static final double BUFFER = 10;
    public static final double MAX_SPAWNDISTANCE = 110;

    public static String HIGHSCORE_FILENAME = "v3\\src\\main\\java\\doodlejump\\highscore.txt";


}
