package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;

/**
 * @author Robert Giacinto
 */
public abstract class Camera {
  private Vector3d position;
  private Vector3d eye;
  private Vector3d up;
  private Matrix projection;

  /**
   * Konstruktor erzeugt eine neue Kamera.
   * @param position die Position der Kamera
   * @param eye die Blickrictung der Kamera
   * @param up der "oben"-Vektor der Kamera
   */
  protected Camera(Vector3d position, Vector3d eye, Vector3d up) {
    this.position = position;
    this.eye = eye;
    this.up = up;
    initProjection();
  }

  public abstract Pixel project(Vector3d vector3d);

  public abstract void initProjection();

  public Vector3d getPosition() {
    return position;
  }

  public void setPosition(Vector3d position) {
    this.position = position;
  }

  public Vector3d getEye() {
    return eye;
  }

  public void setEye(Vector3d eye) {
    this.eye = eye;
  }

  public Vector3d getUp() {
    return up;
  }

  public void setUp(Vector3d up) {
    this.up = up;
  }

  public Matrix getProjection() {
    return projection;
  }

  public void setProjection(Matrix projection) {
    this.projection = projection;
  }
}
