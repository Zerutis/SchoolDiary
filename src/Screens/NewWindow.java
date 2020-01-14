package Screens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewWindow {

    public NewWindow(String filename, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(filename));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e){
            System.out.println("Couldn't open the file");
        }
    }
}
