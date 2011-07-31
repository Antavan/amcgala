package cga.framework.scenegraph;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.math.Matrix;

/**
 * Eine Rotation entlang der y-Achse.
 */
public class RotationY implements Transformation {

  private double phi;
  private Interpolation interpolationPhi;
  private Matrix transformMatrix;

  /**
     * Erzeugt eine neues Rotationsobjekt.
     */
  public RotationY() {
    this(0);
  }

  /**
   * Erzeugt eine neue Rotation entlang der y-Achse.
   *
   * @param phi der Winkel der Rotation
   */
  public RotationY(double phi) {
    this.phi = phi;
    updateMatrix();
  }

  /**
     * Aktualisiert die Transformationsmatrix.
     */
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

  public Interpolation getInterpolationPhi() {
    return interpolationPhi;
  }

  public void setInterpolationPhi(Interpolation interpolationPhi) {
    this.interpolationPhi = interpolationPhi;
  }

  public double getPhi() {
    return phi;
  }

  public void setPhi(double phi) {
    this.phi = phi;
    updateMatrix();
  }

  @Override
  public void update() {
    if (interpolationPhi != null) {
      phi = interpolationPhi.nextValue();
      updateMatrix();
    }
  }
}
