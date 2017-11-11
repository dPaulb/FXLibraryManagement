package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import sample.Member.MemberDAO;
import sample.Member.MemberDTO;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class JoinController implements Initializable {


    @FXML
    private Button joinActionButton;

    @FXML
    private ComboBox ageCombo;

    @FXML
    private TextField idField, emailField;
    @FXML
    private PasswordField passwordField, checkPassField;
    @FXML
    private RadioButton manButton, womanButton;


    private ObservableList<String> ageList = FXCollections.observableArrayList();
    private ToggleGroup genderGroup = new ToggleGroup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 5; i <= 99; i++) {
            ageList.add(i + "");
        }
        ageCombo.setItems(ageList);


        manButton.setToggleGroup(genderGroup);
        womanButton.setToggleGroup(genderGroup);

        joinActionButton.setOnAction(event -> {
            MemberDAO memberDAO = new MemberDAO();
            MemberDTO memberDTO = new MemberDTO();

            int redundantCheckResult = memberDAO.checkRedundantID(idField.getText());

            if (!passwordField.getText().equals(checkPassField.getText()) || redundantCheckResult == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Check Your Password or ID");
                alert.setContentText("Try Again Please.");

                alert.showAndWait();

            } else {
                memberDTO.setMemberID(idField.getText());
                memberDTO.setMemberPassword(passwordField.getText());
                memberDTO.setMemberAge(Integer.parseInt(ageCombo.getSelectionModel().getSelectedItem().toString()));
                memberDTO.setMemberGender(genderGroup.getSelectedToggle().toString().split("'")[1].split("'")[0]);
                memberDTO.setMemberEmail(emailField.getText());

                int result = memberDAO.join(memberDTO);

                if (result == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Success");
                    alert.setContentText("Thank you.");

                    alert.showAndWait();
                    ((Node)(event.getSource())).getScene().getWindow().hide();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText("DB Error!");
                    alert.setContentText("Try Again Please.");

                    alert.showAndWait();
                }
            }
        });

    }


}
