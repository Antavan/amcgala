package cga.scenegraph.shape;

import cga.scenegraph.camera.CVPoint;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;
import cga.scenegraph.renderer.Renderer;

/**
 * Eine Linie im 3d Raum.
 * @author Robert Giacinto
 */
public class Line3d extends Renderable {

  public double x1, y1, z1;
  public double x2, y2, z2;
  private Vector3d start, end;

  public Line3d(Vector3d start, Vector3d end) {
    if (x1 > x2) {
      this.x1 = start.x;
      this.y1 = start.y;
      this.z1 = start.z;
      this.x2 = end.x;
      this.y2 = end.y;
      this.z2 = end.z;
    } else {
      this.x1 = start.x;
      this.y1 = start.y;
      this.z1 = start.z;
      this.x2 = end.x;
      this.y2 = end.y;
      this.z2 = end.z;
    }
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    start = new Vector3d(x1, y1, z1).transform(transformation);
    end = new Vector3d(x2, y2, z2).transform(transformation);
    System.out.println(end);

    CVPoint startPoint = camera.project(start);
    CVPoint endPoint = camera.project(end);

    Pixel startPixel = renderer.toPixel(startPoint);
    Pixel endPixel = renderer.toPixel(endPoint);

//    System.out.println(startPixel);
//    System.out.println(endPixel);

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
    } else {
      for (int yi = startPixel.y; yi < endPixel.y; yi++) {
        Pixel pixel = new Pixel(startPixel.x, yi);
        renderer.putPixel(pixel);
      }
    }
  }
}
