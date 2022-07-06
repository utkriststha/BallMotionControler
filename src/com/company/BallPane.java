package com.company;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BallPane extends BorderPane implements Runnable{
    //Initiating variable for the circle
    public final double radius = 20;
    private double x = radius, y = radius;
    private double dx = 1, dy = 1;
    //Creating a circle object
    private final Circle circle = new Circle(x, y, radius);
    //Creating timeline object as animation
    private final Timeline animation;

    //Initiating thread
    private Thread thread = null;
    private boolean suspend = false;

    //Creating BallPane with animation
    public BallPane() {
        circle.setFill(Color.DARKSLATEGREY);
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        getChildren().add(circle);
    }

    //Function to increase the speed of the ball
    public void increaseSpeed() {
        animation.setRate(animation.getRate() + 0.5);
    }

    //Function to decrease the speed of the ball.
    public void decreaseSpeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.5 : 0);
    }

    //Function to get the speed of the ball
    public float getSpeed() {
        return (float) animation.getRate();
    }

    //Function to move the ball around the scene
    private void moveBall() {
        if (x < radius || x > getWidth() - radius) {
            dx *= -1;
        }
        if (y < radius || y > getHeight() - radius) {
            dy *= -1;
        }
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    //Function to start the thread
    public void start(){
        if(thread==null){
            thread = new Thread(this);
            //starts the thread
            thread.start();
        }
    }

    //Function to pause the ball
    public void pause(){
        animation.pause();
        suspend = true;
    }

    //Function to resume the ball again
    public synchronized void play(){
        animation.play();
        suspend = false;
        notify();
    }

    //Implementing thread
    @Override
    public void run(){
        //Defining a new runnable method to move the ball
        Runnable runner = () -> animation.play();

            try {
                Thread.sleep(50);
                //Synchronises the current thread with new one
                synchronized(this){
                    while(suspend){
                        wait();
                    }
                }
                //starts runner after wait function
                Platform.runLater(runner);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }
}