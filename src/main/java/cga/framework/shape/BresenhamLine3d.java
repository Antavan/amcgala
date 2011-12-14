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
import cga.framework.math.Vector3d;
import cga.framework.renderer.Pixel;
import cga.framework.renderer.Renderer;

/**
 * Eine Linie im 3d Raum.
 *
 * @author Robert Giacinto
 */
public class BresenhamLine3d extends Shape {

    public double x1, y1, z1;
    public double x2, y2, z2;
    private Vector3d start, end;

    public BresenhamLine3d(Vector3d start, Vector3d end) {
        this.x1 = start.x;
        this.y1 = start.y;
        this.z1 = start.z;
        this.x2 = end.x;
        this.y2 = end.y;
        this.z2 = end.z;
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        // Einbeziehen der Transformationsgruppen. Um Animationen zu beruecksichtigen, die auf die einzelnen Felder zugegriffen
        // haben, werden die start und end Vektoren aktualisiert, bevor sie mit der Transformationsmatrix multipliziert werden.
        start = new Vector3d(x1, y1, z1).transform(transformation);
        end = new Vector3d(x2, y2, z2).transform(transformation);


        // Start- und Endpunkt der Linie in Pixeln, mit denen die Linienalgorithmen durchgefuehrt werden.
        Pixel startPixel = camera.getImageSpaceCoordinates(start);
        Pixel endPixel = camera.getImageSpaceCoordinates(end);

        // Wir zeichnen von links nach rechts. Sollte der Startpixel rechts vom Endpixel liegen, dann tauschen wir die Pixel.
        if (startPixel.x > endPixel.x) {
            Pixel tmp = startPixel;
            startPixel = endPixel;
            endPixel = tmp;
        }

        /*
         * Beginn des Bresenham Algorithmus
         */
        double dx = endPixel.x - startPixel.x;
        double dy = endPixel.y - startPixel.y;
        double dx2 = 2 * dx;
        double dy2 = 2 * dy;

        double e;
        double y = startPixel.y;
        double x = startPixel.x;
        int i = 1;
        renderer.putPixel(new Pixel(x, y), color);

        //1.+8. Oktant
        if (dy <= dx && -dy <= dx) {
            e = Math.abs(dy2) - dx;
            while (i <= dx) {
                if (e >= 0) { /*
                     * Diagonalschritt
                     */
                    if (dy <= 0) {
                        y--;
                    } else {
                        y++;
                    }
                    e -= dx2;
                }
                x++;
                i++;
                e += Math.abs(dy2);
                renderer.putPixel(new Pixel(x, y), color);
            }
        }

        //2.+7. Oktant
        if (Math.abs(dy) > dx) {
            e = dx2 - Math.abs(dy);
            while (i <= Math.abs(dy)) {
                if (e >= 0) {
                    x++;
                    e -= Math.abs(dy2);
                }
                if (dy > 0) {
                    y++;
                } else {
                    y--;
                }
                i++;
                e += dx2;
                renderer.putPixel(new Pixel(x, y), color);
            }
        }

        /*
         * Ende Bresenham Algorithmus
         */


    }
}
