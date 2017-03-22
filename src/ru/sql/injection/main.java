package ru.sql.injection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by BigTows on 21.03.17.
 */
public class main extends Application {


    public static DataBase db;

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setMaxHeight(400);
        stage.show();
        main.stage=stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
