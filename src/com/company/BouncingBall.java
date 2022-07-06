package com.company;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BouncingBall {
    public static void display(){

        Stage primaryStage = new Stage();

        //Creating a label to display ball speed
        Label ballSpeed = new Label();

        //Creating object of BallPane
        BallPane ball = new BallPane();

        //Creating event driven program to play an pause the ball.
        ball.setOnMouseClicked(e -> ball.play());
        ball.setOnMousePressed(e -> ball.pause());

        //Creating a BorderPane to display animation on the center and ball speed at the bottom
        BorderPane pane = new BorderPane();
        pane.setBottom(ballSpeed);
        pane.setCenter(ball);

        //Setting the text of the label with ball speed
        ballSpeed.setText("Speed: " + (ball.getSpeed()));

        //Creating event driven program for speeding up and slowing down
        ball.setOnKeyPressed(e-> {
            //For speeding up with up key
            if (e.getCode() == KeyCode.UP){
                ball.increaseSpeed();
                ballSpeed.setText("Speed: "+ (ball.getSpeed()));
            }
            //For speeding down with down key
            else if (e.getCode() == KeyCode.DOWN){
                ball.decreaseSpeed();
                ballSpeed.setText("Speed: "+ (ball.getSpeed()));
            }
        });

        //Creating a scene
        Scene scene = new Scene(pane,700,500, Color.HONEYDEW);
        //Setting the tile of the stage
        primaryStage.setTitle("Ball Bounce");
        //Setting the scene on the stage
        primaryStage.setScene(scene);
        //Displaying the stage
        primaryStage.show();
        //Starting the thread
        ball.start();
        //To keep the ball on the scene
        ball.requestFocus();
    }
}
