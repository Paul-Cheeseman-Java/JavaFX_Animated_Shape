

/*
 Overload constructor so can have default settings and also set diameter etc through it
 Maybe is variables are set to default values, if they are set at instantiation time all good, if not defaults used?

 */


package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {

    private Circle ball;
    //defaults
    private double xDistance = 3;
    private double yDistance = 5;
    private double diameter = 20;

    public Ball(Color colour, double x, double y){

        //create ball
        ball = new Circle(diameter, colour);

        //Starting position
        ball.setLayoutX(x);
        ball.setLayoutY(y);

    }

    public Circle getCircle(){
        return ball;
    }



    private boolean atXBoundary(double boundaryMin, double boundaryMax){
        return (this.getX() <= (boundaryMin + this.getBallRadius()) ||
                this.getX() >= (boundaryMax - this.getBallRadius()));
    }

    private boolean atYBoundary(double boundaryMin, double boundaryMax){
        return (this.getY() <= (boundaryMin + this.getBallRadius()) ||
                this.getY() >= (boundaryMax - this.getBallRadius()));
    }

    public void moveX(double boundaryMin, double boundaryMax){
        //set next move
        this.setX(this.getX() + this.getMoveDistanceX());

        //check for boundary(X) breach and reverse original move if required
        if (this.atXBoundary(boundaryMin, boundaryMax)){
            this.setMoveDistanceX(-this.getMoveDistanceX());
        }
        else {
            this.setMoveDistanceX(this.getMoveDistanceX());
        }

        //check for breeching another balls boundary
            //if so, reverse direction <<<<<<<<---- NOPE
            //not so simple as you could end up with two balls chasing each other
            //the ball AND BAAL HIT ball hit need updating
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

        //check for breeching another balls boundary
        //if so, reverse direction <<<<<<<<---- NOPE
        //not so simple as you could end up with two balls chasing each other
        //the ball AND BALL HIT ball hit need updating
    }


    public void setDiameter(double newDiameter)  {
        this.getCircle().setRadius(newDiameter);
    }


    private double getX(){
        return ball.getLayoutX();
    }

    private double getY(){
        return ball.getLayoutY();
    }

    public void setY(double newY){
        ball.setLayoutY(newY);
    }

    public void setX(double newX){
        ball.setLayoutX(newX);
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
