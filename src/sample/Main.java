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
        Ball ball1 = new Ball (Color.CADETBLUE, 3, 110);
        Ball ball2 = new Ball (Color.RED, 200, 30);
        ball2.setDiameter(4.0);

        //get a better name for "get.circle method"
        canvas.getChildren().add(ball1.getCircle());
        canvas.getChildren().add(ball2.getCircle());

        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20),
                new EventHandler<ActionEvent>() {



                    @Override
                    public void handle(ActionEvent t) {

                        //Keeping the bounds within event handler enable them to be re-calibrated each frame
                        //should the view pane be re-sized.

                        //start with 2 ball for the bouncing off! Could get very complex!

                        //IT HAS TO BE CALCULATED UNTIL NO OVER LAPS
                        // - Need a loop? Could get complex as change in a causes changes > b > c, then c causes a
                        // (but then change could revert original..... :-0)
                        // the final position of a preceding ball needs to be FINAL
                        //
                        //"Dominance level", set on order (or more cleverly diameter/weight)
                            //Could have smaller's speed up on impact with bigger and biggers slow down?
                            //


                        //NEED A CONTINUE FLAG?
                        Bounds bounds = canvas.getBoundsInLocal();

                        //class level array/arraylist to hold all balls
                        //iterate through them each time to find the other values each time..

                        // balls input - array? Maybe arrayList but that only needed
                        // for dynamic input, maybe thats phase 2?

                        //move function ()
                        ball1.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball1.moveY(bounds.getMinY(), bounds.getMaxY());

                        ball2.moveX(bounds.getMinX(), bounds.getMaxX());
                        ball2.moveY(bounds.getMinY(), bounds.getMaxY());
                    }
                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        //timeline.setCycleCount(5);
        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }
}
