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
import cga.framework.shape.Line2d;
import cga.framework.shape.Rectangle2d;
import cga.framework.shape.Shape;

/**
 * Das Paddle, mit dem man den Ball treffen muss.
 * 
 * @author Robert Giacinto
 */
public class PongPaddle extends Shape {
    
    private Rectangle2d paddle;
    private Vector3d position;
    private Plane top;
    private Plane bottom;
    private Plane paddleCol;
    private PongBall ball;
    private PongBoard board;
    private Cross2d c1, c2;
    private double lengthH, lengthV;

    /**
     * Erstellt ein neues Paddle, mit dem ein Ball gespielt werden kann.
     * @param lengthH horizontale Länge des Paddles
     * @param lengthV vertikale Länge des Paddles
     * @param position die Startposition des Paddles
     * @param ball der Ball, der von dem Paddle gespielt werden kann
     */
    public PongPaddle(double lengthH, double lengthV, Vector3d position, PongBall ball, PongBoard board) {
        this.ball = ball;
        this.lengthH = lengthH;
        this.lengthV = lengthV;
        this.board = board;
        this.position = position;
        
        paddle = new Rectangle2d(
                new Line2d(position.x - lengthH, position.y - lengthV, position.x + lengthH, position.y - lengthV),
                new Line2d(position.x - lengthH, position.y - lengthV, position.x - lengthH, position.y + lengthV),
                new Line2d(position.x + lengthH, position.y - lengthV, position.x + lengthH, position.y + lengthV),
                new Line2d(position.x - lengthH, position.y + lengthV, position.x + lengthH, position.y + lengthV));
        
        top = new Plane(Vector3d.UNIT_Y, position.y + lengthV);
        bottom = new Plane(Vector3d.UNIT_Y, position.y - lengthV);
        paddleCol = new Plane(Vector3d.UNIT_X, position.x - lengthH);
        
        c1 = new Cross2d(position.x, position.y + lengthV, 10);
        c2 = new Cross2d(position.x, position.y - lengthV, 10);
        
    }
    
    public Vector3d getPosition() {
        return position;
    }
    
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
        c1.render(transformation, camera, renderer);
//        c2.render(transformation, camera, renderer);
    }
    
    private void updateCollisionPlanes() {
        top.setConstant(position.y + lengthV);
        bottom.setConstant(position.y - lengthV);
        paddle = new Rectangle2d(
                new Line2d(position.x - lengthH, position.y - lengthV, position.x + lengthH, position.y - lengthV),
                new Line2d(position.x - lengthH, position.y - lengthV, position.x - lengthH, position.y + lengthV),
                new Line2d(position.x + lengthH, position.y - lengthV, position.x + lengthH, position.y + lengthV),
                new Line2d(position.x - lengthH, position.y + lengthV, position.x + lengthH, position.y + lengthV));
        
    }
    
    public void moveUp() {
        Vector3d tmp = new Vector3d(position.x, position.y + lengthV + 4, -1);
        if (!board.getTop().isNearPlane(tmp)) {
            position.y += 4;
            updateCollisionPlanes();
        }
    }
    
    public void moveDown() {
        Vector3d tmp = new Vector3d(position.x, position.y - lengthV - 4, -1);
        if (!board.getBottom().isNearPlane(tmp)) {
            position.y -= 4;
            updateCollisionPlanes();
        }
    }
}
