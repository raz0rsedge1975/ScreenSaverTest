package javaFX.physicsfx;

import javaFX.gui.MainScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PhysicsFX extends Application {

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) {
        MainScreen root = new MainScreen(this);

        Scene scene = new Scene(root, 2560, 1440);

        primaryStage.setTitle("PhysX");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


        primaryStage.setOnCloseRequest(e -> root.stopGameLoop());
    }

}
