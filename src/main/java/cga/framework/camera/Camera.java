package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;

/**
 *
 * @author Robert Giacinto
 */
public interface Camera {

    Matrix getProjection();

    CVPoint project(Vector3d vector3d);
    
}
