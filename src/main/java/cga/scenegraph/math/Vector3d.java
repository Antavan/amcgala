package cga.scenegraph.math;

/**
 * Repräsentation von 3D-Vektoren.
 */
public class Vector3d {
  private double x, y, z;

  /**
   * Erzeugt einen neuen 3D-Vektor.
   *
   * @param x x-Komponente
   * @param y y-Komponente
   * @param z z-Komponente
   */
  public Vector3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;

  }

  /**
   * Skalarprodukt mit einem anderen Vektor
   *
   * @param that der andere Vektor
   * @return Skalarprodukt
   */
  public double dot(Vector3d that) {
    return this.x * that.x + this.y * that.y + this.z * that.z;
  }

  /**
   * Kreuzprodukt zweier Vektoren
   *
   * @param that der andere Vektor
   * @return Ergebnisvektor
   */
  public Vector3d cross(Vector3d that) {
    return new Vector3d(
      this.y * that.z - this.z * that.y,
      this.z * that.x - this.x * that.z,
      this.x * that.y - this.y * that.x);
  }

  /**
   * Subtrahiert einen Vektor von diesem Vektor.
   *
   * @param that der andere Vektor
   * @return Ergebnisvektor
   */
  public Vector3d sub(Vector3d that) {
    return new Vector3d(
      this.x - that.x,
      this.y - that.y,
      this.z - that.z
    );
  }

  /**
   * Addiert einen Vektor zu diesem Vektor
   *
   * @param that der andere Vektor
   * @return Ergebnisvektor
   */
  public Vector3d add(Vector3d that) {
    return new Vector3d(
      this.x + that.x,
      this.y + that.y,
      this.z + that.z
    );
  }

  /**
   * Die Länge des Vektors
   *
   * @return die Länge
   */
  public double length() {
    return Math.sqrt(dot(this));
  }

  /**
   * Die quadrierte Länge des Vektors
   *
   * @return quadrierte Länge
   */
  public double lengthSquared() {
    return dot(this);
  }

  /**
   * Multipliziert diesen Vektor mit einem Skalar
   *
   * @param s die skalare Größe
   * @return Ergebnisvektor
   */
  public Vector3d times(double s) {
    return new Vector3d(
      s * this.x,
      s * this.y,
      s * this.z);
  }


  /**
   * Gibt den Vektor normalisiert zurück.
   *
   * @return der normalisierte Vektor
   */
  public Vector3d normalize() {
    return this.times(1 / length());
  }

  public Matrix toMatrix() {
    double[] vals = {x, y, z, 1};
    return new Matrix(vals, 4);
  }
}
