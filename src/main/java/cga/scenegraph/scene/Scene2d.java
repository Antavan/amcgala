package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.math.Vector3d;

public class Scene2d extends Scene {

  public Scene2d() {
    setCamera(new Camera(new Vector3d(-50, 0, 0), new Vector3d(100, 100, 80), Camera.PROJECTION_TYPE_ORTHOGRAPHIC));
  }
}
