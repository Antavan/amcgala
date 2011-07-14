package cga.scenegraph.scene;

import cga.scenegraph.camera.PerspectiveCamera;
import cga.scenegraph.math.Vector3d;

public class Scene2d extends Scene {
    
    public Scene2d() {
        //up, position, direction
        setCamera(new PerspectiveCamera(new Vector3d(0, 1, 0), new Vector3d(0, 0, 1), new Vector3d(0, 0, 0)));
    }
}
