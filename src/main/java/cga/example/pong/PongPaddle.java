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

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.math.Plane;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Cross2d;
import cga.framework.shape.Rectangle2d;
import cga.framework.shape.Shape;

/**
 * Das Paddle, mit dem man den Ball treffen muss.
 *
 * @author Robert Giacinto
 */
public class PongPaddle extends Shape {

    private int moveStep;
    private Rectangle2d paddle;
    private Vector3d position;
    private Plane top;
    private Plane bottom;
    private Plane paddleCol;
    private PongBall ball;
    private PongBoard board;
    private Cross2d c1, c2;
    private int lengthH, lengthV;
    private boolean stopDown, stopUp;

    /**
     * Erstellt ein neues Paddle, mit dem ein Ball gespielt werden kann.
     *
     * @param lengthH horizontale Länge des Paddles
     * @param lengthV vertikale Länge des Paddles
     * @param ball der Ball, der von dem Paddle gespielt werden kann
     * @param board das Spielfeld
     */
    public PongPaddle(int lengthH, int lengthV, PongBall ball, PongBoard board) {
        moveStep = 15;
        this.ball = ball;
        this.lengthH = lengthH;
        this.lengthV = lengthV;
        this.board = board;
        this.position = new Vector3d(board.getXmin() + board.getWidth() * board.getPaddlePosition(), board.getYmin() + board.getHeight() / 2, -1);

        paddle = new Rectangle2d(position.x - lengthH, position.y - lengthV, lengthH, lengthV);

        top = new Plane(Vector3d.UNIT_Y, position.y + lengthV);
        bottom = new Plane(Vector3d.UNIT_Y, position.y - lengthV);
        paddleCol = new Plane(Vector3d.UNIT_X, position.x - lengthH);

        c1 = new Cross2d(position.x, position.y + lengthV, 10);
        c2 = new Cross2d(position.x, position.y - lengthV, 10);

    }

    /**
     * Gibt die Position des Paddles zurück.
     *
     * @return die Position des Paddles
     */
    public Vector3d getPosition() {
        return position;
    }

    /**
     * Setzt das Paddle auf eine neue Position.
     *
     * @param position die neue Position
     */
    public void setPosition(Vector3d position) {
        this.position = position;
        updateCollisionPlanes();
    }

    @Override
    public void update() {
        if (bottom.whichSide(ball.getPosition()) == Plane.Side.Positive
                && top.whichSide(ball.getPosition()) == Plane.Side.Negative
                && paddleCol.isNearPlane(ball.getPosition())) {
            ball.getDirection().x = -ball.getDirection().x;
        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        paddle.render(transformation, camera, renderer);
//        c1.render(transformation, camera, renderer);
//        c2.render(transformation, camera, renderer);
    }

    private void updateCollisionPlanes() {
        top.setConstant(position.y + lengthV);
        bottom.setConstant(position.y - lengthV);
        paddle = new Rectangle2d(position.x - lengthH, position.y - lengthV, lengthH, lengthV);
    }

    /**
     * Bewegt das Paddle ein Stück nach oben.
     */
    public void moveUp() {
        double ny = Math.round(position.y + lengthV + moveStep);
        if (ny < board.getYmax() && !stopUp) {
            position.y += moveStep;
            stopDown = false;
            updateCollisionPlanes();
        } else {
            stopUp = true; // hintert das Paddle daran, sich weiter nach oben zu bewegen als es darf.  Kann wahrscheinlich weg...
            position.y = board.getYmax();
            updateCollisionPlanes();
        }
    }

    /**
     * Bewegt das Paddle ein Stück nach unten.
     */
    public void moveDown() {
        double ny = position.y - lengthV - moveStep;
        if (ny > board.getYmin() && !stopDown) {
            position.y -= moveStep;
            stopUp = false;
            updateCollisionPlanes();
        } else {
            stopDown = true; // hindert das Paddle daran, sich weiter nach unten zu bewegen als es darf
            position.y = board.getYmin() + lengthV;
            updateCollisionPlanes();
        }
    }
}
