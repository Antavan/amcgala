package example;

import cga.scenegraph.camera.OrthoCamera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderJ2dVisitor;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;

/**
 * Simple example on how to use the scene graph for rendering 2d shape objects.
 */
public class LineDrawingExample {
    public static void main(String[] args) {
        RenderVisitor visitor = new RenderJ2dVisitor(new OrthoCamera(), new RendererJ2d(800, 600));

        SceneGraph sg = new SceneGraph();
        sg.addGeometry(new Line2d(5, 500, 500, 2));
        sg.addGeometry(new Line2d(5, 5, 500, 200));

        Scene2d scene2d = new Scene2d(visitor, sg);
        scene2d.update();
        scene2d.show();
    }
}
