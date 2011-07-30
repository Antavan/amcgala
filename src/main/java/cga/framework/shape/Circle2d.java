package cga.framework.shape;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;

/**
 * Ein zweidimensionaler Kreis.
 */
public class Circle2d extends Renderable {

  private int segments;
  private double radius;

  public Circle2d(int segments, double radius) {
    this.segments = segments;
    this.radius = radius;
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    //TODO rendering des kreises implementieren
  }

}
