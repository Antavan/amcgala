package cga.framework.shape;

import cga.framework.camera.CVPoint;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Pixel;
import cga.framework.renderer.Renderer;

/**
 * 2d Linie.
 *
 * <p/>
 * @author Robert Giacinto
 */
public class Line2d extends Shape {

    public double x1, y1;
    public double x2, y2;
    private Vector3d start, end;

    public Line2d(double x1, double y1, double x2, double y2) {
        if (x1 > x2) {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        start = new Vector3d(x1, y1, -1);
        end = new Vector3d(x2, y2, -1);
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        // Einbeziehen der Transformationsgruppen.
        start = new Vector3d(x1, y1, -1).transform(transformation);
        end = new Vector3d(x2, y2, -1).transform(transformation);


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
         * Simpler Linienalgorithmus, der vom Startpixel mit einer konstanten
         * Steigung zum Endpixel geht. TODO Eine Verbesserung hier wäre der
         * Bresenhamalgorithmus.
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

    @Override
    public String toString() {
        return "Line2d{"
                + "x1=" + x1
                + ", y1=" + y1
                + ", x2=" + x2
                + ", y2=" + y2
                + '}';
    }
}
