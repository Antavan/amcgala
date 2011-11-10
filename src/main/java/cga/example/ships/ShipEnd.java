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
import cga.framework.shape.Line;
import cga.framework.shape.Point2d;

/**
 * Ein Schiffsendstück, das zum Zusammensetzen eines Schiffs benutzt werden
 * kann. 
 *
 * @author Robert Giacinto
 */
public class ShipEnd extends Ship {

    private static final Line[] bottom = {
        new Line(new Point2d(0, 1), new Point2d(0, 0.4)),
        new Line(new Point2d(0, 0.4), new Point2d(0.4, 0)),
        new Line(new Point2d(0.4, 0), new Point2d(0.6, 0)),
        new Line(new Point2d(0.6, 0), new Point2d(1, 0.4)),
        new Line(new Point2d(1, 0.4), new Point2d(1, 1))
    };
    private static final Line[] left = {
        new Line(new Point2d(1, 0), new Point2d(0.4, 0.0)),
        new Line(new Point2d(0.4, 0), new Point2d(0, 0.4)),
        new Line(new Point2d(0, 0.4), new Point2d(0, 0.6)),
        new Line(new Point2d(0, 0.6), new Point2d(0.4, 1)),
        new Line(new Point2d(0.4, 1), new Point2d(1, 1))
    };
    private static final Line[] right = {
        new Line(new Point2d(1 - 1, 0), new Point2d(1 - 0.4, 0.0)),
        new Line(new Point2d(1 - 0.4, 0), new Point2d(1 - 0, 0.4)),
        new Line(new Point2d(1 - 0, 0.4), new Point2d(1 - 0, 0.6)),
        new Line(new Point2d(1 - 0, 0.6), new Point2d(1 - 0.4, 1)),
        new Line(new Point2d(1 - 0.4, 1), new Point2d(1 - 1, 1))
    };
    private static final Line[] top = {
        new Line(new Point2d(0, 1 - 1), new Point2d(0, 1 - 0.4)),
        new Line(new Point2d(0, 1 - 0.4), new Point2d(0.4, 1 - 0)),
        new Line(new Point2d(0.4, 1 - 0), new Point2d(0.6, 1 - 0)),
        new Line(new Point2d(0.6, 1 - 0), new Point2d(1, 1 - 0.4)),
        new Line(new Point2d(1, 1 - 0.4), new Point2d(1, 1 - 1))
    };

    /**
     * Erstellt eine neue
     * <code>ShipEnd</code> Instanz.
     *
     * @param width die Breite
     * @param height die Höhe
     * @param x die x-Koordinate der Position
     * @param y die y-Komponente der Position
     * @param heading die Ausrichtung des Schiffs
     */
    public ShipEnd(double width, double height, double x, double y, Heading heading) {
        super(width, height, x, y, heading);
    }

    public ShipEnd(double width, double height, Heading heading) {
        super(width, height, 0, 0, heading);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        if (Heading.BOTTOM.equals(heading)) {
            for (Line line : bottom) {
                line.getLine(x, y, width, height).render(transformation, camera, renderer);
            }
        } else if (Heading.LEFT.equals(heading)) {
            for (Line line : left) {
                line.getLine(x, y, width, height).render(transformation, camera, renderer);
            }
        } else if (Heading.RIGHT.equals(heading)) {
            for (Line line : right) {
                line.getLine(x, y, width, height).render(transformation, camera, renderer);
            }

        } else if (Heading.TOP.equals(heading)) {
            for (Line line : top) {
                line.getLine(x, y, width, height).render(transformation, camera, renderer);
            }
        }
    }
}
