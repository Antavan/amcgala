package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Translation entlang der x-, y- und z-Achse.
 */
public class Translation implements Transformation{
  private double translateX;
  private double translateY;
  private double translateZ;

  /**
   * Erstellt eine neue Translation.
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
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}
