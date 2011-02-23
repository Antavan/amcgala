package cga.scenegraph.graph.visitor;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.Node;
import cga.scenegraph.renderer.RendererJ2d;

public class RenderJ2dVisitor extends RenderVisitor {

    public RenderJ2dVisitor(Camera camera, RendererJ2d renderer) {
        super(camera, renderer);
    }

    @Override
    public void visit(Node node) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
