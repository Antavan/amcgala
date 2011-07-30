package cga.framework.scenegraph;

import cga.framework.animation.Alpha;
import cga.framework.math.Matrix;

/**
 * Skalierung um den Faktor s = (sx, sy, sz)
 */
public class Scale implements Transformation {
  private double scaleX, scaleY, scaleZ;
  private Alpha alpha;

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

    @Override
    public void setAlpha(Alpha alpha) {
       this.alpha = alpha;
    }

    @Override
    public void update() {
        //TODO Aktualisierung über Alphaobjekt muss implementiert werden. Frage ist auch, wie man die einzelnen Achseln separat aktualisieren kann.
    }
}
