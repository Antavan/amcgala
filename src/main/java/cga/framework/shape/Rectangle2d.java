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

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;

/**
 * Ein 2d-Rechteck.
 *
 * @author Robert Giacinto
 */
public class Rectangle2d extends Shape {

    public double width;
    public double height;
    public BresenhamLine2d bottom, top, left, right;

    /**
     *
     * @param x die x-Koordinate der Ecke links unten.
     * @param y die y-Koordinate der Ecke links unten.
     * @param width die Breite des Rechtecks
     * @param height die Höhe des Rechtecks
     */
    public Rectangle2d(double x, double y, double width, double height) {
        bottom = new BresenhamLine2d(x, y, x + width, y);
        top = new BresenhamLine2d(x, y + height, x + width, y + height);
        left = new BresenhamLine2d(x, y, x, y + height);
        right = new BresenhamLine2d(x + width, y, x + width, y + height);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        bottom.color = color;
        top.color = color;
        left.color = color;
        right.color = color;

        bottom.render(transformation, camera, renderer);
        top.render(transformation, camera, renderer);
        left.render(transformation, camera, renderer);
        right.render(transformation, camera, renderer);
    }

    @Override
    public String toString() {
        return "Rectangle2d{" + "bottom =" + bottom + ", top =" + top + ", left =" + left + ", right =" + right + '}';
    }
}
