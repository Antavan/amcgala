package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;

/**
 *
 * @author Robert Giacinto
 */
public interface Camera {

  /**
   * Aktualisiert die Projektionsmatrix des Kameraobjekts. Diese Methode sollte immer
   * dann aufgerufen werden, wenn etwas an der Kamera verändert wurde.
   */
  void update();

  Matrix getProjection();

  CVPoint project(Vector3d vector3d);
}
