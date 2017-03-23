package ru.sql.injection.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.sql.injection.main;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by bigtows on 22/03/2017.
 */
public class ControllerMenu {

    @FXML
    private TextField testLogin;
    @FXML
    private TextField testPassword;
    @FXML
    private TextArea testDebug;
    @FXML
    private Label testLabel;
    @FXML
    private TextField textPolis;

    @FXML
    private void onTestLoggin(MouseEvent event) throws SQLException{
        String sql = "SELECT Count(id) FROM SQLInjection.users WHERE name='"+testLogin.getText()+"' AND password = Password('"+testPassword.getText()+"')";

                testDebug.setText(sql);
        ResultSet rsCount = null;
                try {
                    rsCount = main.db.sendQuery(sql);
                }catch (SQLException e){
                    testDebug.setOpacity(1);
                    testDebug.setText(e.getMessage());
                }
        while(rsCount.next()) {
            if (rsCount.getInt(1)==1){
                ResultSet rsGetInfo = main.db.sendQuery("SELECT mobile FROM SQLInjection.users WHERE name='"+testLogin.getText()+"'");
                while (rsGetInfo.next()) testLabel.setText("Hello, "+testLogin.getText()+". Your phone: "+rsGetInfo.getString(1));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Do not correct data entry");
                alert.setContentText("Try for a new record to your data");
                alert.show();
            }

        }

    }
    @FXML
    private void onChange(KeyEvent event){
        System.out.println(textPolis.getText());
    }

}
