package cga.scenegraph.graph.visitor;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.renderer.Renderer;

public abstract class RenderVisitor implements Visitor {
    private Renderer renderer;
    private Camera camera;

    protected RenderVisitor(Camera camera, Renderer renderer) {
        this.camera = camera;
        this.renderer = renderer;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Camera getCamera() {
        return camera;
    }
}
