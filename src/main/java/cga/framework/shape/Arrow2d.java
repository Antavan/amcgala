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
package cga.framework.shape;

import cga.framework.camera.AbstractCamera;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Color;
import cga.framework.renderer.Renderer;

/**
 * Ein 2d Pfeil.
 * 
 * @author Robert Giacinto
 */
public class Arrow2d extends Shape {

    private Vector3d position;
    private Vector3d direction;
    private double length;
    private Line2d l1;

    public Arrow2d(Vector3d position, Vector3d direction, double length) {
        this.position = position;
        this.length = length;
        this.direction = direction.normalize().times(length);
        init();
    }

    public Vector3d getDirection() {
        return direction;
    }

    public void setDirection(Vector3d direction) {
        this.direction = direction.normalize().times(length);
        init();
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
        this.direction = direction.normalize().times(length);
        init();
    }

    public Vector3d getPosition() {
        return position;
    }

    public void setPosition(Vector3d position) {
        this.position = position;
        init();
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        l1.render(transformation, camera, renderer);
    }

    private void init() {
        l1 = new Line2d(position.x, position.y, position.x + direction.x, position.y + direction.y);
        l1.color = Color.RED;
    }
}
