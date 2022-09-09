package javaFX.physicsfx;

import javaFX.gui.MainScreen;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class PhysicsFX extends Application {

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) {
        MainScreen root = new MainScreen(this);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setTitle("PhysX");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


        primaryStage.setOnCloseRequest(e -> root.stopGameLoop());
    }

}