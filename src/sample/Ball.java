

/*
 Overload constructor so can have default settings and also set diameter etc through it
 Maybe is variables are set to default values, if they are set at instantiation time all good, if not defaults used?

 */


/* reverseDirection needs to be implemented in all methods (ie move)*/

package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ball {

    //class level ArrayList to track balls
    private static ArrayList<Ball> ballList = new ArrayList<Ball>();

    private Circle ball;

    //defaults
    private double xDistance = 3;
    private double yDistance = 5;
    private double diameter = 20;

    //Only want to compare if its the same object in set (rather than value based equality) so no need to
    //override the default equals method
    Set<Ball> collisions = new HashSet<>();


    public Ball(Color colour, double x, double y){

        //create ball
        ball = new Circle(diameter, colour);

        //Starting position
        ball.setCenterX(x);
        ball.setCenterY(y);

        //Add ball to class level array
        ballList.add(this);
        //System.out.println("Ball list Constructor: " +ballList);
    }

    public Circle getCircle(){

        return ball;
    }

    //https://stackoverflow.com/questions/15690846/java-collision-detection-between-two-shape-objects
    //https://www.tutorialspoint.com/javafx/2dshapes_intersection_operation.htm
    public boolean intersectsWith(Ball other) {
        //System.out.println("Val1: " + this.getCircle().getBoundsInLocal());
        //System.out.println("Val2: " + other.getCircle().getBoundsInLocal());
        return this.getCircle().getBoundsInLocal().intersects(other.getCircle().getBoundsInLocal());
    }


    private void hittingAnotherBallX(){

        for (Ball otherBall : ballList) {
            //avoid self
            //System.out.println("-----------------");
            if (!otherBall.equals(this)) {

                //USE INTERSECT AS DETECTION?

                // compares the two specified double values
                double thisPlusRad = (this.getX() + this.getBallRadius());
                double thisMinusRad = (this.getX() - this.getBallRadius());
                double otherBallMinusRad = otherBall.getX() - otherBall.getBallRadius();
                double otherBallPlusRad = otherBall.getX() + otherBall.getBallRadius();

                int hit1 = Double.compare(thisPlusRad, otherBallMinusRad);
                int hit2 = Double.compare(thisPlusRad, otherBallPlusRad);
                //Ball going from right to left
                int hit3 = Double.compare(thisMinusRad, otherBallPlusRad);
                int hit4 = Double.compare(thisMinusRad, otherBallMinusRad);


                //intersects
                //double thisY = this.getY();
                //double otherY = otherBall.getY();
                //int onSameYPlane = Double.compare(thisY, otherY);
                boolean intersects = this.intersectsWith(otherBall);

                if (hit1 < 0 && hit2 > 0 && intersects) {
                    //System.out.println("Hit on X for Ball: " + ball);
                    this.reverseDirectionX();
                    otherBall.reverseDirectionX();
                }
                else if (hit3 < 0 && hit4 > 0 && intersects) {
                     //System.out.println("Hit on X for Ball: " + ball);
                     this.reverseDirectionX();
                     otherBall.reverseDirectionX();
                }
            }
        }
    }


    private void hittingAnotherBallY(){

        for (Ball otherBall : ballList) {
            //avoid self
            //System.out.println("-----------------");
            if (!otherBall.equals(this)) {

                //USE INTERSECT AS DETECTION?

                // compares the two specified double values
                double thisPlusRad = (this.getY() + this.getBallRadius());
                double thisMinusRad = (this.getY() - this.getBallRadius());
                double otherBallMinusRad = otherBall.getY() - otherBall.getBallRadius();
                double otherBallPlusRad = otherBall.getY() + otherBall.getBallRadius();

                //Ball going from down to up
                int hit1 = Double.compare(thisPlusRad, otherBallMinusRad);
                int hit2 = Double.compare(thisPlusRad, otherBallPlusRad);
                //Ball going from up to down
                int hit3 = Double.compare(thisMinusRad, otherBallPlusRad);
                int hit4 = Double.compare(thisMinusRad, otherBallMinusRad);

                //double thisX = this.getX();
                //double otherX = otherBall.getX();
                //int onSameYPlane = Double.compare(thisX, otherX);
                boolean intersects = this.intersectsWith(otherBall);

                if (hit1 < 0 && hit2 > 0 && intersects == true) {
                    //System.out.println("Hit on Y for Ball: " + ball);
                    this.reverseDirectionY();
                    otherBall.reverseDirectionY();
                }
                else if (hit3 < 0 && hit4 > 0 && intersects == true) {
                    //System.out.println("Hit on Y for Ball: " + ball);
                    this.reverseDirectionY();
                    otherBall.reverseDirectionY();
                }
            }
        }
    }


    private boolean atXBoundary(double boundaryMin, double boundaryMax){
        return (this.getX() <= (boundaryMin + this.getBallRadius()) ||
                this.getX() >= (boundaryMax - this.getBallRadius()));
    }

    private boolean atYBoundary(double boundaryMin, double boundaryMax){
        /*
        System.out.println("At Y boundary");
        System.out.println("Y boundary Min: " +boundaryMin);
        System.out.println("Y boundary Max: " +boundaryMax);
        System.out.println("Y boundary Min + rad: " +boundaryMin + this.getBallRadius());
        System.out.println("Y boundary Max - rad: " + (boundaryMax - this.getBallRadius()));
         */
        return (this.getY() <= (boundaryMin + this.getBallRadius()) ||
                this.getY() >= (boundaryMax - this.getBallRadius()));
    }

    public void moveX(double boundaryMin, double boundaryMax){

        //move into a hitting boundary method
        //set next move
        this.setX(this.getX() + this.getMoveDistanceX());

        //check for boundary(X) breach and reverse original move if required
        if (this.atXBoundary(boundaryMin, boundaryMax)){
            this.setMoveDistanceX(-this.getMoveDistanceX());
        }
        else {
            this.setMoveDistanceX(this.getMoveDistanceX());
        }

        this.hittingAnotherBallX();
    }

    public void moveY(double boundaryMin, double boundaryMax){
        //set next move
        this.setY(this.getY() + this.getMoveDistanceY());
        //check for boundary(Y) breach and reverse original move if required
        if (this.atYBoundary(boundaryMin, boundaryMax)){
            this.setMoveDistanceY(-this.getMoveDistanceY());
        } else {
            this.setMoveDistanceY(this.getMoveDistanceY());
        }

        this.hittingAnotherBallY();
    }



    public boolean movingLeftToRight(){
        return (this.getMoveDistanceX() > 0);
    }

    public boolean movingDownToUp(){
        return (this.getMoveDistanceY() > 0);
    }


    public void reverseDirectionX(){
        this.setMoveDistanceX(-this.getMoveDistanceX());
    }

    public void reverseDirectionY(){
        this.setMoveDistanceY(-this.getMoveDistanceY());
    }

    public void setDiameter(double newDiameter)  {
        this.getCircle().setRadius(newDiameter);
    }


    public double getX(){
        return ball.getCenterX();
    }

    public double getY(){
        return ball.getCenterY();
    }

    public void setY(double newY){
        ball.setCenterY(newY);
    }

    public void setX(double newX){
        ball.setCenterX(newX);
    }

    public double getBallRadius(){
        return ball.getRadius();
    }

    private double getMoveDistanceX(){
        return this.xDistance;
    }

    private double getMoveDistanceY(){
        return this.yDistance;
    }

    private void setMoveDistanceX(double distanceX){
        this.xDistance = distanceX;
    }

    private void setMoveDistanceY(double distanceY){
        this.yDistance = distanceY;
    }

}
