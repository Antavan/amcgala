package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Rotation entlang der x-Achse.
 */
public class RotationX implements Transformation {

  private double phi;
  private Matrix transformMatrix;

  /**
   * Erstellt eine neue Rotation entlang der x-Achse um den Winkel phi.
   *
   * @param phi der Winkel der Rotation
   */
  public RotationX(double phi) {
    this.phi = phi;
    updateMatrix();
  }
  
   private void updateMatrix() {
    double[][] values = {
      {1, 0, 0, 0},
      {0, 1, 0, 0},
      {0, 0, 1, 0},
      {0, 0, 0, 1}
    };
    transformMatrix = Matrix.constructWithCopy(values);
  }

  @Override
  public Matrix getTransformMatrix() {
    return transformMatrix;
  }
}
