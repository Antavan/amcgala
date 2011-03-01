package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.Renderer;

/**
 * @author Robert Giacinto
 */
abstract class Scene {
    private Camera camera;
    private Renderer renderer;
    private SceneGraph scenegraph;
    private RenderVisitor renderVisitor;

    protected Scene(Camera camera, RenderVisitor renderVisitor, SceneGraph scenegraph) {
        this.camera = camera;
        this.renderVisitor = renderVisitor;
        this.renderer = renderVisitor.getRenderer();
        this.scenegraph = scenegraph;
    }

    public void update() {
        scenegraph.accept(renderVisitor);
    }

    public void show() {
        renderer.show();
    }
}
