package ru.sql.injection.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import ru.sql.injection.DataBase;
import ru.sql.injection.main;

import java.sql.SQLException;


/**
 * Created by bigtows on 21/03/2017.
 */
public class ControllerLogin {
    @FXML
    private TextField hostField;
    @FXML
    private TextField dbnameField;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logginButton;
    @FXML
    private CheckBox reCreate;


    @FXML
    private void onClick(MouseEvent event) throws Exception {

        main.db = new DataBase(hostField.getText(), "", loginField.getText(), passwordField.getText());

        if (main.db.getStatus()) {

                try {
                    main.db.init();
                    main.stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../menu.fxml"))));
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error sql execute");
                    alert.setContentText(e.getMessage());
                    alert.show();
                }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error connect");
            alert.setContentText("Your data is wrong, try again..");
            alert.show();
        }

    }
}
