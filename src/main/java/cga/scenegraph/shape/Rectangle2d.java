package cga.scenegraph.shape;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.renderer.Renderer;


public class Rectangle2d implements Renderable {
  public Line2d a, b, c, d;

  public Rectangle2d(Line2d a, Line2d b, Line2d c, Line2d d) {
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  @Override
  public void render(Camera camera, Renderer renderer) {
    a.render(camera, renderer);
    b.render(camera, renderer);
    c.render(camera, renderer);
    d.render(camera, renderer);
  }
}
