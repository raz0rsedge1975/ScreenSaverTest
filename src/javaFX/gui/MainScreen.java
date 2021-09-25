package javaFX.gui;
import javaFX.physicsfx.PhysicsFX;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MainScreen extends BorderPane {
    /* ToDo get Monitor Resolution */

    //gameloop variables
    private Timeline gameLoop;
    private final DrawScreen center;

    public MainScreen(PhysicsFX main) {
        //main variables
        this.center = new DrawScreen();
        setCenter();
        runGameLoop();
    }

    private void setCenter() {
        this.setCenter(center);
    }

    private void runGameLoop() {
        EventHandler<ActionEvent> gameUpdate = event
                -> {
            center.update();
            center.draw();
        };
        gameLoop = new Timeline(new KeyFrame(Duration.millis(25.0), gameUpdate));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    public void stopGameLoop() {
        gameLoop.stop();
    }

}