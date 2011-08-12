package cga.framework.scenegraph.transform;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.math.Matrix;
import cga.framework.scenegraph.Transformation;

/**
 * Skalierung um den Faktor s = (sx, sy, sz)
 */
public class Scale implements Transformation {

  private double scaleX, scaleY, scaleZ;
  private Matrix transformMatrix;
  private Interpolation interpolationX;
  private Interpolation interpolationY;
  private Interpolation interpolationZ;

  public Scale(double scaleX, double scaleY, double scaleZ) {
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.scaleZ = scaleZ;
  }

  private void updateMatrix() {
    double[][] values = {
      {scaleX, 0, 0, 0},
      {0, scaleY, 0, 0},
      {0, 0, scaleZ, 0},
      {0, 0, 0, 1}
    };

    transformMatrix = Matrix.constructWithCopy(values);
  }

  public Interpolation getInterpolationX() {
    return interpolationX;
  }

  public void setInterpolationX(Interpolation interpolationX) {
    this.interpolationX = interpolationX;
  }

  public Interpolation getInterpolationY() {
    return interpolationY;
  }

  public void setInterpolationY(Interpolation interpolationY) {
    this.interpolationY = interpolationY;
  }

  public Interpolation getInterpolationZ() {
    return interpolationZ;
  }

  public void setInterpolationZ(Interpolation interpolationZ) {
    this.interpolationZ = interpolationZ;
  }

  @Override
  public Matrix getTransformMatrix() {
    return transformMatrix;
  }

  @Override
  public void update() {
    if (interpolationX != null) {
      scaleX = interpolationX.nextValue();
    }
    if (interpolationY != null) {
      scaleY = interpolationY.nextValue();
    }
    if (interpolationZ != null) {
      scaleZ = interpolationZ.nextValue();
    }
    updateMatrix();
  }
}
