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
    private double height;
    private BresenhamLine2d[] letterLines;
    private static Line[] A = {
        new Line(new Point2d(0, 0), new Point2d(0.4, 1.0)),
        new Line(new Point2d(0.4, 1.0), new Point2d(0.6, 1.0)),
        new Line(new Point2d(0.6, 1.0), new Point2d(1.0, 0)),
        new Line(new Point2d(0.2, 0.5), new Point2d(0.8, 0.5))
    };
    private static Line[] B = {
        new Line(new Point2d(0, 0), new Point2d(0, 1)),
        new Line(new Point2d(0, 1), new Point2d(0.8, 1)),
        new Line(new Point2d(0.8, 1), new Point2d(1, 0.9)),
        new Line(new Point2d(1, 0.9), new Point2d(1, 0.7)),
        new Line(new Point2d(1, 0.7), new Point2d(0.8, 0.6)),
        new Line(new Point2d(0.8, 0.6), new Point2d(0.2, 0.6)),
        new Line(new Point2d(0.2, 0.6), new Point2d(0.2, 0.5)),
        new Line(new Point2d(0.2, 0.5), new Point2d(0.8, 0.5)),
        new Line(new Point2d(0.8, 0.5), new Point2d(1, 0.4)),
        new Line(new Point2d(1, 0.4), new Point2d(1.0, 0.2)),
        new Line(new Point2d(1.0, 0.2), new Point2d(0.8, 0)),
        new Line(new Point2d(0.8, 0), new Point2d(0, 0))
    };
    private static Line[] C = {
        new Line(new Point2d(1, 0), new Point2d(0.3, 0)),
        new Line(new Point2d(0.3, 0), new Point2d(0, 0.3)),
        new Line(new Point2d(0, 0.3), new Point2d(0, 0.8)),
        new Line(new Point2d(0, 0.8), new Point2d(0.3, 1)),
        new Line(new Point2d(0.3, 1.0), new Point2d(1.0, 1.0))
    };
    private static Line[] D = {
        new Line(new Point2d(0, 0), new Point2d(0, 1.0)),
        new Line(new Point2d(0, 1.0), new Point2d(0.7, 1.0)),
        new Line(new Point2d(0.7, 1.0), new Point2d(1.0, 0.7)),
        new Line(new Point2d(1.0, 0.7), new Point2d(1.0, 0.4)),
        new Line(new Point2d(1.0, 0.4), new Point2d(0.7, 0)),
        new Line(new Point2d(0.7, 0), new Point2d(0, 0))
    };

    public static Letter getA(double width) {
        return new Letter(width, A);
    }

    public static Letter getB(double width) {
        return new Letter(width, B);
    }

    public static Letter getC(double width) {
        return new Letter(width, C);
    }

    public static Letter getD(double width) {
        return new Letter(width, D);
    }

    private Letter() {
    }

    private Letter(double width, Line... lines) {
        this.lines = lines;
        this.width = width;
        this.height = 1.5 * width;
        letterLines = new BresenhamLine2d[lines.length];
        for (int i = 0; i < lines.length; i++) {
            letterLines[i] = lines[i].getLine(width, height);
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

        public BresenhamLine2d getLine(double width, double height) {
            return new BresenhamLine2d(start.x * width, start.y * height, end.x * width, end.y * height);
        }
    }
}
