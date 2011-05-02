package cga.scenegraph.shape;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.renderer.Renderer;

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
  public void render(Camera camera, Renderer renderer) {
    //TODO rendering des kreises implementieren
  }

}
