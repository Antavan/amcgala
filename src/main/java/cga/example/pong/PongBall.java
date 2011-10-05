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
import cga.framework.math.Vector3d;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Line2d;
import cga.framework.shape.Rectangle2d;
import cga.framework.shape.Shape;

/**
 * Der Ball, der vom Spieler getroffen werden muss.
 * 
 * @author Robert Giacinto
 */
public class PongBall extends Shape {

    private Rectangle2d ball;
    private double velocity;
    private Vector3d direction;

    public PongBall() {
        ball = new Rectangle2d(
                new Line2d(0, 0, 0, 10),
                new Line2d(0, 0, 10, 0),
                new Line2d(10, 0, 10, 10),
                new Line2d(0, 10, 10, 10));
        velocity = 2;
        direction = new Vector3d(1, 1, -1);
    }

    @Override
    public void update() {
        
    }
    
    

    public Rectangle2d getBall() {
        return ball;
    }

    public void setBall(Rectangle2d ball) {
        this.ball = ball;
    }

    public Vector3d getDirection() {
        return direction;
    }

    public void setDirection(Vector3d direction) {
        this.direction = direction;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        ball.render(transformation, camera, renderer);
    }
}
