package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class MusicViewController {
    @FXML private Label result;
    @FXML private CustomClass custom;

    public void handleSubmitButtonAction(ActionEvent actionEvent) {
        System.out.println("Fired a button!");
        result.setText("Button Clicked");
        custom.setText("New Text");
    }

}
