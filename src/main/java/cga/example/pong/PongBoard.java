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
    private Rectangle2d boundaries;

    public PongBoard(int width, int height) {
        super("Pongboard");
        width = (width - 20) >> 1;
        height = (height - 20) >> 1;

        // Die äußeren Grenzen des Spielfeldes.
        boundaries = new Rectangle2d(
                new Line2d(-width, -height, -width, height), // links
                new Line2d(-width, -height, width, -height), // unten
                new Line2d(-width, height, width, height), // oben
                new Line2d(width, -height, width, height)); // rechts
        addShape(boundaries);
        ball = new PongBall(this);
        addShape(ball);
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
}
