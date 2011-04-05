package example;

import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;
import cga.scenegraph.shape.Triangle2d;


public class LineDrawingExample {
  public static void main(String[] args) {
    SceneGraph sg = new SceneGraph();
    sg.addGeometry(new Line2d(400, 400, 450, 400));
    sg.addGeometry(new Triangle2d(50, 200, 200, 200, 125, 50));

    RenderVisitor visitor = new RenderVisitor();
    visitor.setRenderer(new RendererJ2d(800, 600));

    Scene2d scene2d = new Scene2d();
    scene2d.setScenegraph(sg);
    scene2d.setRenderVisitor(visitor);

    for (int i = 0; i < 300; i++) {
      ((Line2d) sg.findNode("root").getGeometry().toArray()[0]).y1--;
      scene2d.update();
      scene2d.show();
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
      }
    }
  }
}
