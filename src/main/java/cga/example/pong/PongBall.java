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
import cga.framework.shape.Arrow2d;
import cga.framework.shape.Cross2d;
import cga.framework.shape.Shape;

/**
 * Der Ball, der vom Spieler getroffen werden muss.
 * 
 * @author Robert Giacinto
 */
public class PongBall extends Shape {

    private double velocity;
    private Vector3d direction;
    private Vector3d position;
    private PongBoard board;
    private Cross2d cross;
    private Arrow2d arrow;

    public PongBall(PongBoard board) {
        this.board = board;
        velocity = .10;
        direction = getRandomDirection().times(velocity);
        System.out.println(direction);
        System.out.println(direction.length());
        position = new Vector3d(0, 0, -1);
        cross = new Cross2d(position, 5);
        arrow = new Arrow2d(position, direction, velocity * 50);
    }

    @Override
    public void update() {
        position.x += direction.x;
        position.y += direction.y;
        cross.setPosition(position);
        arrow.setPosition(position);
        checkPosition();
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

    public Vector3d getPosition() {
        return position;
    }

    public void setPosition(Vector3d position) {
        this.position = position;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        cross.render(transformation, camera, renderer);
        arrow.render(transformation, camera, renderer);
    }

    private Vector3d getRandomDirection() {
        return new Vector3d(Math.sin(Math.random() * Math.PI), Math.cos(Math.random() * Math.PI), -1).normalize();
    }

    private void checkPosition() {
        if (board.getBottom().isNearPlane(position) || board.getTop().isNearPlane(position)) {
            direction.y = -direction.y;
        } else if (board.getLeft().isNearPlane(position)) {
            direction.x = -direction.x;
        }
    }
}
