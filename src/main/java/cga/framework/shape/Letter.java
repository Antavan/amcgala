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
 *
 * @author Robert Giacinto
 */
public class Letter extends Shape {

    private Line[] lines;
    private double width;
    private BresenhamLine2d[] letterLines;
    private static Line[] A = {
        new Line(new Point2d(0, 0), new Point2d(0.4, 1.0)),
        new Line(new Point2d(0.4, 1.0), new Point2d(0.6, 1.0)),
        new Line(new Point2d(0.6, 1.0), new Point2d(1.0, 0)),
        new Line(new Point2d(0.2, 0.5), new Point2d(0.8, 0.5))
    };

    public static Letter getA(double width) {
        return new Letter(width, A);
    }

    private Letter() {
    }

    private Letter(double width, Line... lines) {
        this.lines = lines;
        this.width = width;
        letterLines = new BresenhamLine2d[lines.length];
        for (int i = 0; i < lines.length; i++) {
            letterLines[i] = lines[i].getLine(width);
        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (BresenhamLine2d bl : letterLines) {
            bl.render(transformation, camera, renderer);
        }
    }

    private static class Line {

        private Point2d start;
        private Point2d end;

        public Line(Point2d start, Point2d end) {
            this.start = start;
            this.end = end;
        }

        public BresenhamLine2d getLine(double width) {
            return new BresenhamLine2d(start.x * width, start.y * width, end.x * width, end.y * width);
        }
    }
}
