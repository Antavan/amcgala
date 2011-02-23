package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.Renderer;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 3:45 PM
 * To change this template use File | Settings | File Templates.
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
