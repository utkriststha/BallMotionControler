package com.company;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class BallCoordinates {
    //Declaring pane.
    public static StackPane stackPane = new StackPane();
    public static BorderPane pane = new BorderPane();
    //Declaring and initiating label, so it can be used all over the program
    public static Label label = new Label("x= 0 " + " y= 0 ");

    public static void display() throws Exception {
        Stage primaryStage = new Stage();
        //Making an object for file
        File file = new File("points.txt");
        //Making an scanner object to read the file.
        Scanner fileReader = new Scanner(file);
        //Using of delimiter to get rid of strings others than int.
        fileReader.useDelimiter("\\D+");
        //Getting all x and y coordinates and calling the point object to create circle points
        while (fileReader.hasNextInt()) {
            float x = fileReader.nextInt();
            float y = fileReader.nextInt();
            pointObject(x, y);
        }

        //Creating a scene and declaring its height, weight and color
        Scene scene = new Scene(pane, 900, 800, Color.BEIGE);
        //Setting the font of the label
        label.setFont(new Font("Arial", 12));
        //Setting the label in the bottom of the scene
        stackPane.getChildren().add(label);
        stackPane.setAlignment(Pos.BOTTOM_CENTER);
        pane.setBottom(stackPane);

        //Setting the title of the stage and setting the scene on the stage
        primaryStage.setTitle("Show Points");
        primaryStage.setScene(scene);
        //Displaying the stage
        primaryStage.show();
    }

    public static void pointObject(double x, double y) {
        //Creating an circle object with point x,y and radius 5
        //Setting the color and adding it to the pane
        Circle circle = new Circle(x, y, 5);
        circle.setFill(Color.DARKCYAN);
        pane.getChildren().add(circle);

        //Creating an action for mouse dragged event by changing the x and y coordinate of the circle
        circle.setOnMouseDragged((MouseEvent event) -> {
            //Changing the x and y coordinate of the circle object
            circle.setCenterX(event.getX());
            circle.setCenterY(event.getY());
            //Displaying it on the label
            label.setText("x = " + (int) event.getX() + " y = " + (int) event.getY());
        });

        //Setting an action to display the label with the point when the mouse is pressed for hovered over the circle object.
        circle.setOnMousePressed((MouseEvent event) -> label.setText("x = " + (int) circle.getCenterX() + " y = " + (int) circle.getCenterY()));
        circle.setOnMouseEntered((MouseEvent event) -> label.setText("x = " + (int) circle.getCenterX() + " y = " + (int) circle.getCenterY()));
        //Setting an action to show 0,0 for x anf y coordinate when the mouse doesn't hovers over the circle object.
        circle.setOnMouseExited((MouseEvent event) -> label.setText("x = 0 " + " y = 0 "));
    }

}
