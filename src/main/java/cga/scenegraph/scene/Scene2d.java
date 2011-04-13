package cga.scenegraph.scene;

import cga.scenegraph.camera.OrthoCamera;

public class Scene2d extends Scene {
  private static final OrthoCamera CAMERA = new OrthoCamera();

  public Scene2d() {
    setCamera(CAMERA);
  }
}
