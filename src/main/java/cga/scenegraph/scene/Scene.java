package cga.scenegraph.scene;

import cga.scenegraph.Renderer.Renderer;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;

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

    protected Scene(Camera camera, Renderer renderer, SceneGraph scenegraph) {
        this.camera = camera;
        this.renderer = renderer;
        this.scenegraph = scenegraph;
    }

    public abstract void show();
}
