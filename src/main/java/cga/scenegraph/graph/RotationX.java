package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Rotation entlang einer der x-Achse.
 */
public class RotationX implements Transformation{

  private double phi;

  /**
   * Erstellt eine neue Rotation entlang der x-Achse um den Winkel phi.
   * @param phi der Winkel der Rotation
   */
  public RotationX(double phi) {
    this.phi = phi;
  }

  @Override
  public Matrix getTransformMatrix() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
