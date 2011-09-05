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

import cga.framework.camera.CVPoint;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Pixel;
import cga.framework.renderer.Renderer;

/**
 * Eine Linie im 3d Raum.
 * @author Robert Giacinto
 */
public class Line3d extends Shape {

    public double x1, y1, z1;
    public double x2, y2, z2;
    private Vector3d start, end;

    public Line3d(Vector3d start, Vector3d end) {
        this.x1 = start.x;
        this.y1 = start.y;
        this.z1 = start.z;
        this.x2 = end.x;
        this.y2 = end.y;
        this.z2 = end.z;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        // Einbeziehen der Transformationsgruppen.
        start = new Vector3d(x1, y1, z1).transform(transformation);
        end = new Vector3d(x2, y2, z2).transform(transformation);

        

        // Start- und Endpunkt der Linie in Pixeln, mit denen die Linienalgorithmen durchgeführt werden.
        Pixel startPixel = camera.getImageSpaceCoordinates(start);
        Pixel endPixel = camera.getImageSpaceCoordinates(end);

        // Wir zeichnen von links nach rechts. Sollte der Startpixel rechts vom Endpixel liegen, dann tauschen wir die Pixel.
        if (startPixel.x > endPixel.x) {
            Pixel tmp = startPixel;
            startPixel = endPixel;
            endPixel = tmp;
        }

        /*
         * Simpler Linienalgorithmus, der vom Startpixel mit einer konstanten Steigung zum Endpixel geht.
         * TODO Eine Verbesserung hier wäre der Bresenhamalgorithmus.
         */
        double dx = endPixel.x - startPixel.x;
        double dy = endPixel.y - startPixel.y;
        double m = dy / dx;
        double y = startPixel.y;

        if (dx > 0) {
            for (int x = startPixel.x; x <= endPixel.x; x++) {
                Pixel pixel = new Pixel(x, (int) y);
                renderer.putPixel(pixel);
                y += m;
            }
        } else if (dx == 0) {
            for (int yi = startPixel.y; yi < endPixel.y; yi++) {
                Pixel pixel = new Pixel(startPixel.x, yi);
                renderer.putPixel(pixel);
            }
        }
    }
}
