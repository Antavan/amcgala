/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package cga.example.pong;

import cga.framework.math.Plane;
import cga.framework.math.Vector3d;
import cga.framework.scenegraph.Node;
import cga.framework.shape.Rectangle2d;

/**
 * Das Board, in dem das Spiel stattfindet. Es definiert die Grenzen, in denen
 * der Ball und der Spieler sich bewegen können.
 *
 * @author Robert Giacinto
 */
public class PongBoard extends Node {

    private PongBall ball;
    private PongPaddle paddle;
    private Rectangle2d boundaries;
    private Plane top;
    private Plane bottom;
    private Plane left;
    private int width;
    private int height;
    private int xmin, xmax, ymin, ymax;
    private double ballStart;
    private double paddlePosition;

    public PongBoard(int x, int y, int width, int height, double ballStart, double paddlePosition) {
        super("Pongboard");
        this.width = width;
        this.height = height;
        this.ballStart = ballStart;
        this.paddlePosition = paddlePosition;

        xmin = x;
        xmax = x + width;
        ymin = y;
        ymax = y + height;



        top = new Plane(Vector3d.UNIT_Y, y + height);
        bottom = new Plane(Vector3d.UNIT_Y, y);
        left = new Plane(Vector3d.UNIT_X, x);

        // Die äußeren Grenzen des Spielfeldes.
        boundaries = new Rectangle2d(x, y, width, height); // rechts

        addShape(boundaries);
        ball = new PongBall(this);
        addShape(ball);
        // TODO scheiße
        paddle = new PongPaddle(5, 20, ball, this);
        addShape(paddle);
    }

    public double getBallStart() {
        return ballStart;
    }

    public void setBallStart(double ballStart) {
        this.ballStart = ballStart;
    }

    public double getPaddlePosition() {
        return paddlePosition;
    }

    public void setPaddlePosition(double paddlePosition) {
        this.paddlePosition = paddlePosition;
    }

    public int getXmax() {
        return xmax;
    }

    public void setXmax(int xmax) {
        this.xmax = xmax;
    }

    public int getXmin() {
        return xmin;
    }

    public void setXmin(int xmin) {
        this.xmin = xmin;
    }

    public int getYmax() {
        return ymax;
    }

    public void setYmax(int ymax) {
        this.ymax = ymax;
    }

    public int getYmin() {
        return ymin;
    }

    public void setYmin(int ymin) {
        this.ymin = ymin;
    }

    public PongBall getBall() {
        return ball;
    }

    public void setBall(PongBall ball) {
        this.ball = ball;
    }

    public Rectangle2d getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(Rectangle2d boundaries) {
        this.boundaries = boundaries;
    }

    public Plane getBottom() {
        return bottom;
    }

    public void setBottom(Plane bottom) {
        this.bottom = bottom;
    }

    public Plane getTop() {
        return top;
    }

    public void setTop(Plane top) {
        this.top = top;
    }

    public PongPaddle getPaddle() {
        return paddle;
    }

    public void setPaddle(PongPaddle paddle) {
        this.paddle = paddle;
    }

    public Plane getLeft() {
        return left;
    }

    public void setLeft(Plane left) {
        this.left = left;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
