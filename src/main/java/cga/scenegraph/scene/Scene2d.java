package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.math.Vector3d;

public class Scene2d extends Scene {

  public Scene2d() {
    //TODO die einstellung passt in 3d nicht wirklich. 
    setCamera(new Camera(new Vector3d(-2, -2, 2), new Vector3d(2.1, 2.1, 2.6), Camera.PROJECTION_TYPE_PERSPECTIVE));
  }
}
