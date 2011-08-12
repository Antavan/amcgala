package example.triangle;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Line2d;
import cga.framework.shape.Point2d;
import cga.framework.shape.Shape;

/**
 *
 * @author Robert Giacinto
 */
public class Triangle extends Shape {
  
  private Point2d p1, p2, p3;
  private Line2d l1, l2, l3;
  
  public Triangle(Point2d p1, Point2d p2, Point2d p3) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    
    l1 = new Line2d(p1.x, p1.y, p2.x, p2.y);
    l2 = new Line2d(p1.x, p1.y, p3.x, p3.y);
    l3 = new Line2d(p3.x, p3.y, p2.x, p2.y);
  }
  
  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    l1.render(transformation, camera, renderer);
    l2.render(transformation, camera, renderer);
    l3.render(transformation, camera, renderer);
  }
}
