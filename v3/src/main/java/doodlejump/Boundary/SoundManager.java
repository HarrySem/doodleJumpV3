package doodlejump.Boundary;

import java.io.File;

import javafx.scene.media.AudioClip;

public class SoundManager {
    private AudioClip bounce;
    private AudioClip jump;
    private AudioClip monster1;
    private AudioClip monster2;
    private AudioClip monster3;
    private AudioClip propeller;
    private AudioClip rocket;
    private AudioClip spring;
    private AudioClip falling;
    private AudioClip shot;
    private AudioClip hit;

    public SoundManager()
    {
       this.bounce = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\bounce.mp3").toURI().toString());
       this.jump = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\jump.mp3").toURI().toString());
       this.monster1 = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\monster1.mp3").toURI().toString());
       this.monster2 = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\monster2.mp3").toURI().toString());
       this.monster3 = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\monster3.mp3").toURI().toString());
       this.propeller = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\propeller.mp3").toURI().toString());
       this.rocket = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\rocket.mp3").toURI().toString());
       this.spring = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\spring.mp3").toURI().toString());
       this.falling = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\falling.mp3").toURI().toString());
       this.shot = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\shot.mp3").toURI().toString());
       this.hit = new AudioClip(new File("v3\\src\\main\\resources\\sounds\\hit.mp3").toURI().toString());
    }

    public void playBounce()
    {
        bounce.play();
    }

    public void playJump()
    {
        jump.play();
    }

    public void playMonster1()
    {
        monster1.play();
    }

    public void playMonster2()
    {
        monster2.play();
    }

    public void playMonster3()
    {
        monster3.play();
    }

    public void playPropeller()
    {
        propeller.play();
    }

    public void playRocket()
    {
        rocket.play();
    }

    public void playSpring()
    {
        spring.play();
    }

    public void playFalling()
    {
        falling.play();
    }

    public void playShot()
    {
        shot.play();
    }

    public void playHit()
    {
        hit.play();
    }
}
