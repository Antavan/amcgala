package example;

import cga.scenegraph.Renderer.RendererJ2d;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.scene.Scene2d;

/**
 * Simple example on how to use the scene graph for rendering 2d shape objects.
 */
public class LineDrawingExample {
    public static void main(String[] args) {
        SceneGraph sg = new SceneGraph();
        Scene2d s2d = new Scene2d(new RendererJ2d(), sg);
    }
}
