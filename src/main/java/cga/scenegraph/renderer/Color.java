package cga.scenegraph.renderer;

/**
 * Plattformunabhängige Farbklasse.
 * Die jeweilige Renderer-Implementierung ist für die Interpretation der Farben verantwortlich.
 */
public class Color {
  public double r, g, b;

  public double getR() {
    return r;
  }

  public void setR(double r) {
    this.r = r;
  }

  public double getG() {
    return g;
  }

  public void setG(double g) {
    this.g = g;
  }

  public double getB() {
    return b;
  }

  public void setB(double b) {
    this.b = b;
  }
}
