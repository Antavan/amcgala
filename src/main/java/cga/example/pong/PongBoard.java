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
import cga.framework.shape.Line2d;
import cga.framework.shape.Rectangle2d;

/**
 * Das Board, in dem das Spiel stattfindet. Es definiert die Grenzen, in denen der Ball und der Spieler sich bewegen können.
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

    public PongBoard(double x, double y, int width, int height) {
        super("Pongboard");
        this.width = width;
        this.height = height;

        top = new Plane(Vector3d.UNIT_Y, y - height / 2);
        bottom = new Plane(Vector3d.UNIT_Y, y + height / 2);
        left = new Plane(Vector3d.UNIT_X, x - width / 2);

        width = width >> 1;
        height = height >> 1;
        // Die äußeren Grenzen des Spielfeldes.
        boundaries = new Rectangle2d(
                new Line2d(x - width, y - height, x - width, y + height), // links
                new Line2d(x - width, y - height, x + width, y - height), // unten
                new Line2d(x - width, y + height, x + width, y + height), // oben
                new Line2d(x + width, y - height, x + width, y + height)); // rechts

        addShape(boundaries);
        ball = new PongBall(this);
        addShape(ball);
        paddle = new PongPaddle(5, 20, new Vector3d((width / 4) * 3, 0, -1), ball, this);
        addShape(paddle);
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
}
