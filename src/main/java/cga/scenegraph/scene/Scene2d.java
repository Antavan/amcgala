package cga.scenegraph.scene;

import cga.scenegraph.camera.OrthoCamera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;

public class Scene2d extends Scene {
    private static final OrthoCamera CAMERA = new OrthoCamera();

    public Scene2d(RenderVisitor renderVisitor, SceneGraph scenegraph) {
        super(CAMERA, renderVisitor, scenegraph);
    }
}
