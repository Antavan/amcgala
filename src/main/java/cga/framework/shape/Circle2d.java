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
 * Ein zweidimensionaler Kreis.
 */
public class Circle2d extends Shape {

    private int segments;
    private double radius;

    public Circle2d(int segments, double radius) {
        this.segments = segments;
        this.radius = radius;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        //TODO rendering des kreises implementieren
    }
}
