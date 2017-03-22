package ru.sql.injection.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private void onTestLoggin(MouseEvent event) throws SQLException{
        String sql = "SELECT Count(id) FROM SQLInjection.users WHERE name='"+testLogin.getText()+"' AND password = '"+testPassword.getText()+"'";
                testDebug.setText(sql);
        ResultSet rs = main.db.sendQuery(sql);
        while(rs.next())
        System.out.println(rs.getInt(1));
    }

}
