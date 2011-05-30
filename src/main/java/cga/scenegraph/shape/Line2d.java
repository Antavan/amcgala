package cga.scenegraph.shape;


import cga.scenegraph.camera.CVPoint;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;
import cga.scenegraph.renderer.Renderer;

/**
 * Simple 2d line implementation, using a Bresenham midpoint algorithm for rasterisation.
 *
 * @author Robert Giacinto<
 */
public class Line2d extends Renderable {
  public double x1, y1;
  public double x2, y2;

  private Vector3d v1, v2;

  public Line2d(double x1, double y1, double x2, double y2) {
    if (x1 > x2) {
      this.x1 = x2;
      this.y1 = y1;
      this.x2 = x1;
      this.y2 = y2;
    } else {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }

    v1 = new Vector3d(x1, y1, -1);
    v2 = new Vector3d(x2, y2, -1);
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    v1 = new Vector3d(x1, y1, -1);
    v2 = new Vector3d(x2, y2, -1);
    
    CVPoint cvStart = camera.project(v1);
    CVPoint cvEnd = camera.project(v2);

    Pixel start = renderer.toPixel(cvStart);
    Pixel end = renderer.toPixel(cvEnd);

    double dx = end.x - start.x;
    double dy = end.y - start.y;
    double m = dy / dx;
    double y = start.y;

    for (int x = start.x; x <= end.x; x++) {
      Pixel pixel = new Pixel(x, (int) y);
      renderer.putPixel(pixel);
      y += m;
    }


  }

  @Override
  public String toString() {
    return "Line2d{" +
        "x1=" + x1 +
        ", y1=" + y1 +
        ", x2=" + x2 +
        ", y2=" + y2 +
        '}';
  }
}
