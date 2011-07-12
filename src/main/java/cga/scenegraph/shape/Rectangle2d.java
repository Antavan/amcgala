package cga.scenegraph.shape;

import cga.scenegraph.camera.DefaultCamera;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.renderer.Renderer;


public class Rectangle2d extends Renderable {
  public Line2d a, b, c, d;

  public Rectangle2d(Line2d a, Line2d b, Line2d c, Line2d d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  @Override
  public void render(Matrix transformation, DefaultCamera camera, Renderer renderer) {
    a.render(transformation, camera, renderer);
    b.render(transformation, camera, renderer);
    c.render(transformation, camera, renderer);
    d.render(transformation, camera, renderer);
  }
}
