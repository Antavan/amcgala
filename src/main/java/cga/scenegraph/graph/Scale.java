package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Skalierung um den Faktor s = (sx, sy, sz)
 */
public class Scale implements Transformation {
  private double scaleX, scaleY, scaleZ;

  public Scale(double scaleX, double scaleY, double scaleZ) {
    this.scaleX = scaleX;
    this.scaleY = scaleY;
    this.scaleZ = scaleZ;
  }


  @Override
  public Matrix getTransformMatrix() {
    double[] values = {
        scaleX, 0, 0, 0,
        0, scaleY, 0, 0,
        0, 0, scaleZ, 0,
        0, 0, 0, 1
    };

    return new Matrix(values, 4);
  }
}
