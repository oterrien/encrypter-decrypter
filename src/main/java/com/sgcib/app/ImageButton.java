package com.sgcib.app;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Olivier on 19/12/2015.
 */
public class ImageButton extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 0, 0, 0, 0;";
    private final String STYLE_PRESSED = "-fx-background-color: lightgrey; -fx-padding: 0, 0, 0, 0;";

    private String imageURL;

    private double fitHeight = 100, fitWidth = 100;

    public ImageButton() {

        setStyle(STYLE_NORMAL);

        setOnMousePressed(event -> setStyle(STYLE_PRESSED));
        setOnMouseReleased(event -> setStyle(STYLE_NORMAL));

        Platform.runLater(() -> {
            ImageView imageView = new ImageView(new Image(ClassLoader.getSystemResourceAsStream(imageURL)));
            imageView.setFitHeight(fitHeight);
            imageView.setFitWidth(fitWidth);
            setGraphic(imageView);
        });
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getFitHeight() {
        return fitHeight;
    }

    public void setFitHeight(double fitHeight) {
        this.fitHeight = fitHeight;
    }

    public double getFitWidth() {
        return fitWidth;
    }

    public void setFitWidth(double fitWidth) {
        this.fitWidth = fitWidth;
    }
}