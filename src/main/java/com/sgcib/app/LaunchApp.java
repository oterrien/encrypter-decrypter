package com.sgcib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Olivier on 17/12/2015.
 */
public class LaunchApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("main.fxml"));
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("Encrypter/Decrypter");
        stage.setWidth(400);
        stage.setHeight(400);
        stage.setResizable(false);
        stage.setIconified(false);
        stage.show();
    }
}