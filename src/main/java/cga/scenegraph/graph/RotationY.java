package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Rotation entlang der y-Achse.
 */
public class RotationY implements Transformation {
  private double phi;

  /**
   * Erzeugt eine neue Rotation entlang der y-Achse.
   *
   * @param phi der Winkel der Rotation
   */
  public RotationY(double phi) {
    this.phi = phi;
  }

  @Override
  public Matrix getTransformMatrix() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
