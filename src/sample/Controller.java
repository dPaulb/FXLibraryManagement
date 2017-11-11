package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button joinButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        joinButton.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("joinPage.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Join Page");
                stage.setScene(new Scene(root, 450, 450));
                stage.show();
                // Hide this current window (if this is what you want)
//                ((Node)(event.getSource())).getScene().getWindow().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
