package cga.scenegraph.graph.visitor;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.Node;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.shape.Renderable;

public class RenderJ2dVisitor extends RenderVisitor {

    public RenderJ2dVisitor(Camera camera, RendererJ2d renderer) {
        super(camera, renderer);
    }

    @Override
    public void visit(Node node) {
        System.out.println("Visiting: " + node);
        for (Renderable renderable : node.getGeometry()) {
            System.out.println("Rendering: " + renderable);
            renderable.render(getCamera(), getRenderer());
        }
    }
}
