package com.company;

import javafx.application.Application;
import javafx.geometry.BoundingBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;

public class Main extends Application {

    //Main method to launch stage
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        //Creating a label to display ball speed
        Label heading = new Label("Ball Motion Controller");
        heading.setAlignment(Pos.CENTER);
        heading.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Label lblBouncingBall = new Label("Use ⬆ to increase the speed and ⬇ to decrease the speed of the ball");
        Button btnBouncingBall = new Button("Ball Motion");
        btnBouncingBall.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        Label lblBallCoordinates = new Label("Move the ball around with you mouse to see the coordinates");
        Button btnBallCoordinates  = new Button("Ball Coordinates");
        btnBallCoordinates.setFont(Font.font("Verdana", FontWeight.BOLD,12));

        HBox hBoxOptions1 = new HBox();
        hBoxOptions1.getChildren().addAll(btnBallCoordinates,lblBallCoordinates);
        hBoxOptions1.setMargin(lblBallCoordinates,new Insets(10));
        hBoxOptions1.setMargin(btnBallCoordinates,new Insets(10));

        HBox hBoxOptions2 = new HBox();
        hBoxOptions2.getChildren().addAll(btnBouncingBall,lblBouncingBall);
        hBoxOptions2.setMargin(lblBouncingBall,new Insets(10));
        hBoxOptions2.setMargin(btnBouncingBall,new Insets(10));

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBoxOptions1,hBoxOptions2);
        vBox.setMargin(hBoxOptions1, new Insets(5));
        vBox.setMargin(hBoxOptions2, new Insets(5));

        HBox hBoxHeading = new HBox(5);
        hBoxHeading.getChildren().addAll(heading);
        hBoxHeading.setAlignment(Pos.CENTER);
        hBoxHeading.setMargin(lblBouncingBall,new Insets(30));

        //Creating a BorderPane to display animation on the center and ball speed at the bottom
        BorderPane pane = new BorderPane();
        pane.setTop(hBoxHeading);
        pane.setCenter(vBox);

        //Creating a scene
        Scene scene = new Scene(pane,650,200, Color.HONEYDEW);
        //Setting the tile of the stage
        primaryStage.setTitle("Ball Motion Controller");
        //Setting the scene on the stage
        primaryStage.setScene(scene);
        //Displaying the stage
        primaryStage.show();

        btnBouncingBall.setOnAction(e -> BouncingBall.display());
        btnBallCoordinates.setOnAction(e -> {
            try {
                BallCoordinates.display();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }
}