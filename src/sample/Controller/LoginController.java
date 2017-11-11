package sample.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Member.MemberDAO;
import sample.Member.MemberDTO;
import sample.Util.PasswordHash;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button joinButton;

    @FXML
    private Button findButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField passwordField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        joinButton.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("../fxml/joinPage.fxml"));
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


        findButton.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("fxml/joinPage.fxml"));
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

        loginButton.setOnAction(event -> {

            MemberDAO memberDAO = new MemberDAO();
            MemberDTO memberDTO = new MemberDTO();

            if (idField.getText().equals("") || passwordField.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Check Your ID or Password");
                alert.setContentText("Try Again Please.");

                alert.showAndWait();
            }
            else{
                String memberID = idField.getText();
                String memberPassword = new PasswordHash().LockPassword(passwordField.getText());

                memberDTO.setMemberID(memberID);
                memberDTO.setMemberPassword(memberPassword);

                int result = memberDAO.login(memberDTO);
                if(result == -2){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("DB Error");
                    alert.setContentText("Try Again Please.");

                    alert.showAndWait();
                }
                else if(result == -1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("We don't have such ID.");
                    alert.setContentText("Try Again Please.");

                    alert.showAndWait();
                }
                else if(result == 0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("Incorrect Password");
                    alert.setContentText("Try Again Please.");

                    alert.showAndWait();
                }
                else if(result == 1){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Success");
                    alert.setContentText("Enjoy.");

                    alert.showAndWait();
                }
            }

        });

    }


}
