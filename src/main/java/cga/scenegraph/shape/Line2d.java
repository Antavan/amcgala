package cga.scenegraph.shape;


import cga.scenegraph.camera.Camera;
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

    v1 = new Vector3d(x1, y1, 1);
    v2 = new Vector3d(x2, y2, 1);
  }

  @Override
  public void render(Camera camera, Renderer renderer) {
    double dx = x2 - x1;
    double dy = y2 - y1;
    double m = dy / dx;
    double y = y1;
    for (int x = (int) x1; x <= x2; x++) {
      Vector3d vector3d = new Vector3d(x, y, 1);
      Pixel p = camera.project(vector3d);
      renderer.putPixel(p);
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
