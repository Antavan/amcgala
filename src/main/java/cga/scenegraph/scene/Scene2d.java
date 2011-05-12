package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.math.Vector3d;

public class Scene2d extends Scene {

  public Scene2d() {
    setCamera(new Camera(new Vector3d(-30, 0, 2), new Vector3d(45, 50, 1), Camera.PROJECTION_TYPE_PERSPECTIVE));
  }
}
