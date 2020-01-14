package Dienynas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Screens.MainWindow;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainWindow root = new MainWindow();
        Scene scene = new Scene(root);
        primaryStage.setTitle("School diary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
