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
import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Renderer;
import java.util.logging.Logger;

/**
 * Ein Kreuz, das einen Punkt markiert.
 *
 * @author Robert Giacinto
 */
public class Cross2d extends Shape {

    private Vector3d position;
    private int size;
    private BresenhamLine2d l1, l2;

    public Cross2d(Vector3d position, int size) {
        this.position = position;
        this.size = size;
        init();
    }

    public Cross2d(double x, double y, int size) {
        this.position = new Vector3d(x, y, -1);
        this.size = size;
        init();
    }

    public Vector3d getPosition() {
        return position;
    }

    public void setPosition(Vector3d position) {
        this.position = position;
        init();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        init();
    }

    private void init() {
        l1 = new BresenhamLine2d(position.x - size, position.y - size, position.x + size, position.y + size);
        l2 = new BresenhamLine2d(position.x - size, position.y + size, position.x + size, position.y - size);
    }

    @Override
    public void render(Matrix transformation, AbstractCamera camera, Renderer renderer) {
        l1.color = color;
        l2.color = color;
        l1.render(transformation, camera, renderer);
        l2.render(transformation, camera, renderer);
    }
    private static final Logger LOG = Logger.getLogger(Cross2d.class.getName());
}
