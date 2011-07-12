package cga.scenegraph.scene;

import cga.scenegraph.camera.DefaultCamera;
import cga.scenegraph.math.Vector3d;

public class Scene2d extends Scene {

  public Scene2d() {
    //TODO die einstellung passt in 3d nicht wirklich. 
    setCamera(new DefaultCamera(new Vector3d(-2, -2, 10), new Vector3d(2.1, 2.1, 11.00001), DefaultCamera.PROJECTION_TYPE_PERSPECTIVE));
  }
}
