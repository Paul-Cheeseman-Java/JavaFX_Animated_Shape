//https://www.mkyong.com/javafx/javafx-animated-ball-example/

package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Pane canvas = new Pane();
        Scene scene = new Scene(canvas, 300, 300, Color.ALICEBLUE);
        Ball ball1 = new Ball (Color.CADETBLUE, 20, 20);
        Ball ball2 = new Ball (Color.RED, 70, 20);
        ball2.setDiameter(4.0);
        Ball ball3 = new Ball (Color.GREEN, 110, 110);
        ball3.setDiameter(4.0);
        Ball ball4 = new Ball (Color.PURPLE, 20, 200);
        ball3.setDiameter(7.0);

        System.out.println("test 1: " + ball1.intersectsWith(ball2));
        System.out.println("test 2: " + ball3.intersectsWith(ball4));

        //get a better name for "get.circle method"
        canvas.getChildren().add(ball1.getCircle());
        canvas.getChildren().add(ball2.getCircle());
        canvas.getChildren().add(ball3.getCircle());
        canvas.getChildren().add(ball4.getCircle());

        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {



                    @Override
                    public void handle(ActionEvent t) {

                        //Keeping the bounds within event handler enable them to be re-calibrated each frame
                        //should the view pane be re-sized.

                        //"Dominance level", set on order (or more cleverly diameter/weight)
                            //Could have smaller's speed up on impact with bigger and biggers slow down?
                            //

                        Bounds bounds = canvas.getBoundsInLocal();

                        //move function ()
                        //System.out.println("X: " +ball1.getX());
                        //System.out.println("Y: " +ball1.getX());
                        ball1.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball1.moveY(bounds.getMinY(), bounds.getMaxY());

                        ball2.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball2.moveY(bounds.getMinY(), bounds.getMaxY());

                        ball3.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball3.moveY(bounds.getMinX(), bounds.getMaxX());

                        ball4.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball4.moveY(bounds.getMinX(), bounds.getMaxX());
                    }
                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setCycleCount(1);
        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}
