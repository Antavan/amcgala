package cga.framework.scenegraph.visitor;

import cga.framework.camera.Camera;
import cga.framework.scenegraph.Node;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Shape;

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
        Matrix transform = node.getTransformMatrix();        
        for (Shape renderable : node.getGeometry()) {
            renderable.render(transform, getCamera(), getRenderer());
        }
    }
}
