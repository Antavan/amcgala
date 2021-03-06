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
package amcgala.example.triangle;

import amcgala.framework.camera.AbstractCamera;
import amcgala.framework.camera.Camera;
import amcgala.framework.math.Matrix;
import amcgala.framework.renderer.Color;
import amcgala.framework.renderer.Renderer;
import amcgala.framework.shape.Line2d;
import amcgala.framework.shape.Point2d;
import amcgala.framework.shape.Shape;

/**
 * Eine Dreiecksklasse. Sie nimmt die Eckpunkte eines beliebigen Dreiecks entgegen.
 * @author Robert Giacinto
 */
public class Triangle extends Shape {

    private Line2d l1, l2, l3;

    public Triangle(Point2d p1, Point2d p2, Point2d p3) {
        l1 = new Line2d(p1.x, p1.y, p2.x, p2.y);
        l2 = new Line2d(p1.x, p1.y, p3.x, p3.y);
        l3 = new Line2d(p3.x, p3.y, p2.x, p2.y);

        l1.color = Color.BLACK;
        l2.color = Color.RED;
        l3.color = Color.GREEN;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        l1.render(transformation, camera, renderer);
        l2.render(transformation, camera, renderer);
        l3.render(transformation, camera, renderer);
    }
}
