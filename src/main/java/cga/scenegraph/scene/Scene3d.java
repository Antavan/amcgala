package cga.scenegraph.scene;

import cga.scenegraph.camera.DefaultCamera;
import cga.scenegraph.math.Vector3d;

/**
 */
public class Scene3d extends Scene {
  public Scene3d() {
    //TODO sollte bei 3d nicht near und far, sondern fov, position und blickrichtung der kamera sein
    setCamera(new DefaultCamera(new Vector3d(0, 0, 0), new Vector3d(50, 50, 0), DefaultCamera.PROJECTION_TYPE_PERSPECTIVE));
  }
}
