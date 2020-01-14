package Screens;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public abstract class ComboBoxDesign extends ComboBox<String> {

    public ComboBoxDesign() {

    }

    public abstract void chooseAct(ActionEvent actionEvent);

}
