package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Translation entlang der x-, y- und z-Achse.
 */
public class Translation implements Transformation {
  private double translateX;
  private double translateY;
  private double translateZ;

  /**
   * Erstellt eine neue Translation.
   *
   * @param translateX Translation entlang der x-Achse
   * @param translateY Translation entlang der y-Achse
   * @param translateZ Translation entlang der z-Achse
   */
  public Translation(double translateX, double translateY, double translateZ) {
    this.translateX = translateX;
    this.translateY = translateY;
    this.translateZ = translateZ;
  }

  @Override
  public Matrix getTransformMatrix() {
    double[] values = {
        1, 0, 0, translateX,
        0, 1, 0, translateY,
        0, 0, 1, translateZ,
        0, 0, 0, 1
    };
    return new Matrix(values, 4);
  }
}
