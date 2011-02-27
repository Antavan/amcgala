package example;

import cga.scenegraph.camera.OrthoCamera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderJ2dVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;

/**
 * Simple example on how to use the scene graph for rendering 2d shape objects.
 */
public class LineDrawingExample {
    public static void main(String[] args) {
        SceneGraph sg = new SceneGraph();
        sg.addGeometry(new Line2d());

        Scene2d s2d = new Scene2d(new RenderJ2dVisitor(new OrthoCamera(), new RendererJ2d(800, 600)), sg);
        s2d.update();
        s2d.show();
    }
}
