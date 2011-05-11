package example;

import cga.scenegraph.animation.Animation;
import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;


public class LineDrawingExample {
  public static void main(String[] args) {
    SceneGraph sceneGraph = new SceneGraph();
    Line2d line = new Line2d(0, 0, 40, 50);

    line.setAnimation(new Animation<Line2d>() {


      @Override
      public void animate() {
        if (getShape().y2 > -50) {
          getShape().y2 -= 0.2;
        }
      }
    });

    sceneGraph.addGeometry(line);
    //sceneGraph.addGeometry(new Triangle2d(50, 200, 200, 200, 125, 50));

    RenderVisitor visitor = new RenderVisitor();
    visitor.setRenderer(new RendererJ2d(1600, 800));

    Scene2d scene2d = new Scene2d();
    scene2d.setScenegraph(sceneGraph);
    scene2d.setRenderVisitor(visitor);

    Animator animator = new Animator(10);
    scene2d.setAnimator(animator);

    scene2d.start();
  }
}
