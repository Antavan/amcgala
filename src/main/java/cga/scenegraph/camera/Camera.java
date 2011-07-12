package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;

/**
 *
 * @author Robert Giacinto
 */
public interface Camera {

    Matrix getProjection();

    CVPoint project(Vector3d vector3d);
    
}
