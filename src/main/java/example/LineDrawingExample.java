package example;

import cga.scenegraph.animation.Animation;
import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.Translation;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;
import cga.scenegraph.shape.Line3d;

public class LineDrawingExample {

  public static void main(String[] args) {
    SceneGraph sceneGraph = new SceneGraph();
    Line2d line = new Line2d(0, 0, 40, 50);

//    line.setAnimation(new Animation<Line2d>() {
//
//      @Override
//      public void animate() {
//        if (getShape().y2 > -50) {
//          getShape().y2 -= 0.2;
//        }
//      }
//    });

    Line3d line3d = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(7, -3, -1));
    Line3d line3d2 = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(-3, -3, -4));
    Line3d line3d3 = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(-3, 7, -1));

//
//    line3d.setAnimation(new Animation<Line3d>() {
//
//      @Override
//      public void animate() {
//        if (getShape().x1 > -100) {
//          getShape().x1 -= 0.1;
//          getShape().x2 -= 0.1;
//        }
//      }
//    });
//
//    line3d2.setAnimation(new Animation<Line3d>() {
//
//      @Override
//      public void animate() {
//        if (getShape().x1 > -100) {
//          getShape().x1 -= 0.1;
//          getShape().x2 -= 0.1;
//        }
//      }
//    });
//
//    line3d3.setAnimation(new Animation<Line3d>() {
//
//      @Override
//      public void animate() {
//        if (getShape().x1 > -100) {
//          getShape().x1 -= 0.1;
//          getShape().x2 -= 0.1;
//        }
//      }
//    });

   // sceneGraph.addGeometry(line);
    sceneGraph.addGeometry(line3d);
    sceneGraph.addGeometry(line3d2);
    sceneGraph.addGeometry(line3d3);
    sceneGraph.addTransformation(new Translation(3, 0, 0));
   

    RenderVisitor visitor = new RenderVisitor();
    visitor.setRenderer(new RendererJ2d(1600, 800));

    Scene2d scene2d = new Scene2d();
    scene2d.setScenegraph(sceneGraph);
    scene2d.setRenderVisitor(visitor);

    Animator animator = new Animator(20);
    scene2d.setAnimator(animator);

    scene2d.start();
  }
}
