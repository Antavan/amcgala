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
package amcgala.framework.shape;

/**
 * Eine Linienhilfsklasse.
 *
 * @author Robert Giacinto
 */
public class Line {

    private Point2d start;
    private Point2d end;

    public Line(Point2d start, Point2d end) {
        this.start = start;
        this.end = end;
    }

    public BresenhamLine2d getLine(double width, double height) {
        return new BresenhamLine2d(start.x * width, start.y * height, end.x * width, end.y * height);
    }

    public BresenhamLine2d getLine(double x, double y, double width, double height) {
        double xs = start.x * width + x;
        double ys = start.y * height + y;
        double xe = end.x * width + x;
        double ye = end.y * height + y;

        return new BresenhamLine2d(xs, ys, xe, ye);
    }

    @Override
    public String toString() {
        return "Line{" + "start=" + start + ", end=" + end + '}';
    }
}
