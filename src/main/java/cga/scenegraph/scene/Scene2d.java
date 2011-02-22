package cga.scenegraph.scene;

import cga.scenegraph.Renderer.Renderer;
import cga.scenegraph.camera.OrthoCamera;
import cga.scenegraph.graph.SceneGraph;

public class Scene2d extends Scene {
    private static final OrthoCamera CAMERA = new OrthoCamera();

    public Scene2d(Renderer renderer, SceneGraph scenegraph) {
        super(CAMERA, renderer, scenegraph);
    }
}
