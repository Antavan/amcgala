package cga.scenegraph.graph.visitor;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.Node;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.renderer.Renderer;
import cga.scenegraph.shape.Renderable;

public class RenderVisitor implements Visitor {

    private Renderer renderer;
    private Camera camera;

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Camera getCamera() {
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
