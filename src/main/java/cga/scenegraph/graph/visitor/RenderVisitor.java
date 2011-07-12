package cga.scenegraph.graph.visitor;

import cga.scenegraph.camera.DefaultCamera;
import cga.scenegraph.graph.Node;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.renderer.Renderer;
import cga.scenegraph.shape.Renderable;

public class RenderVisitor implements Visitor {

    private Renderer renderer;
    private DefaultCamera camera;

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setCamera(DefaultCamera camera) {
        this.camera = camera;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public DefaultCamera getCamera() {
        return camera;
    }

    @Override
    public void visit(Node node) {
        // System.out.println("Visiting: " + node);
        Matrix transform = node.getTransformMatrix();
        for (Renderable renderable : node.getGeometry()) {
            renderable.render(transform, getCamera(), getRenderer());
        }
    }
}
