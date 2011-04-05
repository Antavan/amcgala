package cga.scenegraph.scene;

import cga.scenegraph.camera.OrthoCamera;
import cga.scenegraph.graph.SceneGraph;

public class Scene2d extends Scene {
  private static final OrthoCamera CAMERA = new OrthoCamera();

  public Scene2d(SceneGraph scenegraph) {
    super(scenegraph);
    setCamera(CAMERA);
  }
}
