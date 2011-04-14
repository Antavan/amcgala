package example;

import cga.scenegraph.animation.Animation;
import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;
import cga.scenegraph.shape.Triangle2d;


public class LineDrawingExample {
  public static void main(String[] args) {
    SceneGraph sceneGraph = new SceneGraph();
    Line2d line = new Line2d(400, 400, 450, 400);

    line.setAnimation(new Animation<Line2d>() {
      private int x;

      @Override
      public void animate() {
        if (x++ < 100) {
          getShape().y2--;
        }
      }
    });

    sceneGraph.addGeometry(line);
    sceneGraph.addGeometry(new Triangle2d(50, 200, 200, 200, 125, 50));

    RenderVisitor visitor = new RenderVisitor();
    visitor.setRenderer(new RendererJ2d(800, 600));

    Scene2d scene2d = new Scene2d();
    scene2d.setScenegraph(sceneGraph);
    scene2d.setRenderVisitor(visitor);

    Animator animator = new Animator(10);
    scene2d.setAnimator(animator);

    scene2d.start();
  }
}
