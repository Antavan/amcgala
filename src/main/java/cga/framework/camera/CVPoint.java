package cga.framework.camera;

/**
 * Diese Klasse repräsentiert einen Punkt im kanonischen View Volume.
 * Einen CVPoint erhält man als Ergebnis der Projektion.
 */
public class CVPoint {
  public double x, y;

  public CVPoint(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "CVPoint{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
