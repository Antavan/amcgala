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
package cga.example.ships;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Rectangle2d;
import cga.framework.shape.Shape;
import cga.framework.shape.Text;

/**
 *
 * @author Robert Giacinto
 */
public class BoardCell extends Shape {

    private double x;
    private double y;
    private double width;
    private double height;
    protected Shape ship;
    protected Rectangle2d bounds;

    public BoardCell(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle2d(x, y, width, height);
        ship = new Text("A", width);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        bounds.render(transformation, camera, renderer);
        if (ship != null) {
            ship.render(transformation, camera, renderer);
        }
    }
}
