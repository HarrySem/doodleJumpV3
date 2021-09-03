package doodlejump.Control;

import java.util.List;
import java.util.Random;

import doodlejump.Entity.*;


public class PlatformGenerator {

    private Random random;
    private int difficultyStage;
    private Layer layer;
    private boolean event, eventLeft;
    private int eventProgression;

    public PlatformGenerator(Layer layer)
    {
        this.random = new Random();
        this.difficultyStage = 0;
        this.layer = layer;
        this.event = false;
        this.eventLeft = true;
        this.eventProgression = 0;
    }

    public void setSeed(long seed)
    {
        this.random = new Random(seed);
    }

    public void increaseDifficulty()
    {
        difficultyStage++;
    }

    public Platform nextPlatform()
    {
        int randomInt = random.nextInt(100);
        int propability = Settings.SPAWNRATE_EVENT;
        Vector2D location;

        //Determining Location of next Platform
        if(event)
        {
            eventProgression++;
            location = nextLocation(true);
            if(eventProgression >= Settings.EVENT_DURATION)
            {
                event = false;
                eventProgression = 0;
                eventLeft = !eventLeft;
            }
        }
        else if(difficultyStage >= Settings.SPAWNSTART_EVENT && randomInt < propability)
        {
            event = true;
            location = nextLocation(true);
            eventProgression++;
        }
        else
            location = nextLocation(false);
        
        //Determining Platform

        //Bounce
        propability += Settings.SPAWNRATE_BOUNCE;
        if(difficultyStage >= Settings.SPAWNSTART_BOUNCE && randomInt < propability)
            return new BouncePlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Moving Platform Horizontal
        propability += Settings.SPAWNRATE_MOVING_HORIZONTAL;
        if(difficultyStage >= Settings.SPAWNSTART_MOVING_HORIZONTAL && randomInt < propability)
            return new MovingPlatformHorizontal(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Moving Platform Vertical
        propability += Settings.SPAWNRATE_MOVING_VERTIKAL;
        if(difficultyStage >= Settings.SPAWNSTART_MOVING_VERTIKAL && randomInt < propability)
            return new MovingPlatformVertical(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Propeller
        propability += Settings.SPAWNRATE_PROPELLER;
        if(difficultyStage >= Settings.SPAWNSTART_PROPELLER && randomInt < propability)
            return new PropellerPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Rocket
        propability += Settings.SPAWNRATE_ROCKET;
        if(difficultyStage >= Settings.SPAWNSTART_ROCKET && randomInt < propability)
            return new RocketPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Spring
        propability += Settings.SPAWNRATE_SPRING;
        if(difficultyStage >= Settings.SPAWNSTART_SPRING && randomInt < propability)
            return new SpringPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Disappearing
        propability += Settings.SPAWNRATE_DISAPPEARING;
        if(difficultyStage >= Settings.SPAWNSTART_DISAPPEARING && randomInt < propability)
            return new DisappearingPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        //Exploding
        propability += Settings.SPAWNRATE_EXPLODING;
        if(difficultyStage >= Settings.SPAWNSTART_EXPLODING && randomInt < propability)
            return new ExplodingPlatform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
        
        //Regular platform
        return new Platform(layer, location, Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT);
    }

    private Vector2D nextLocation(boolean event)
    {
        Random random = new Random();
        if(event)
        {
            if(eventLeft)
                return new Vector2D(random.nextInt((int)(layer.getPrefWidth()/2 - 2*Settings.PLATFORM_BUFFER))
                + Settings.PLATFORM_BUFFER, 0);
            else
                return new Vector2D(random.nextInt((int)(layer.getPrefWidth()/2 - 2*Settings.PLATFORM_BUFFER))
                + layer.getPrefWidth()/2 + Settings.PLATFORM_BUFFER, 0);
        }
        else
            return new Vector2D(random.nextInt((int)(layer.getPrefWidth() - 2*Settings.PLATFORM_BUFFER))
            + Settings.PLATFORM_BUFFER, 0);
    }

    public Player generateStartingScenario(List<Platform> platforms) {
        Player player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40, layer.getWidth());
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 150), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, 80), 
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        return player;
    }

    public Player generateTestScenario(List<Platform> platforms)
    {
        Player player = new Player(layer, new Vector2D(layer.getPrefWidth()/2, layer.getPrefHeight()/2), 20, 40, layer.getWidth());
        platforms.add(new Platform(layer, new Vector2D(player.getLocation().x, player.getLocation().y+player.getHeight()/2),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        platforms.add(new RocketPlatform(layer, new Vector2D(player.getLocation().x+350, player.getLocation().y-100),
        Settings.PLATFORM_WIDTH, Settings.PLATFORM_HIGHT));
        return player;
    }
}
