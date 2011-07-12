package cga.scenegraph.shape;

import cga.scenegraph.camera.DefaultCamera;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.renderer.Renderer;

public class Triangle2d extends Renderable {

    public Line2d a, b, c;

    public Triangle2d(Line2d a, Line2d b, Line2d c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Triangle2d(double ax, double ay, double bx, double by, double cx, double cy) {
        a = new Line2d(cx, cy, bx, by);
        b = new Line2d(ax, ay, cx, cy);
        c = new Line2d(ax, ay, bx, by);
    }

    @Override
    public void render(Matrix transformation, DefaultCamera camera, Renderer renderer) {
        a.render(transformation, camera, renderer);
        b.render(transformation, camera, renderer);
        c.render(transformation, camera, renderer);
    }
}
