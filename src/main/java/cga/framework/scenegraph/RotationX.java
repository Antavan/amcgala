package cga.framework.scenegraph;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.math.Matrix;

/**
 * Eine Rotation entlang der x-Achse.
 */
public class RotationX implements Transformation {

  private double phi;
  private Matrix transformMatrix;
  private Interpolation interpolationPhi;

  /**
     * Erstellt eine neues Rotationsobjekt.
     */
  public RotationX() {
    this(0);
  }

  /**
     * Erstellt ein Rotationsobjekt, das eine Rotation, um den Winkel phi beschreibt.
     * 
     * @param phi der Winkel phi der Rotation
     */
  public RotationX(double phi) {
    this.phi = phi;
    updateMatrix();
  }

  /**
     * Aktualisiert die Transformationsmatrix.
     */
  private void updateMatrix() {
    double[][] values = {
      {1, 0, 0, 0},
      {0, Math.cos(phi), -Math.sin(phi), 0},
      {0, Math.sin(phi), Math.cos(phi), 0},
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
