package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;

public class OrthoCamera extends Camera {

  public OrthoCamera() {
    super(new Vector3d(0, 0, 1), new Vector3d(0, 0, 0), new Vector3d(0, 1, 0));
  }

  /**
   * Erzeugt eine neue Parallelprojektion.
   *
   * @param position Position der Kamera
   * @param eye      Blickrichtung der Kamera
   * @param up       "oben"-Vektor der Kamera
   */
  public OrthoCamera(Vector3d position, Vector3d eye, Vector3d up) {
    super(position, eye, up);
  }

  @Override
  public Pixel project(Vector3d vector3d) {
    Matrix r = getProjection().times(vector3d.toMatrix());
    return new Pixel(r.get(0, 0), r.get(1, 0));
  }

  @Override
  public void initProjection() {
    double[] vals = {
      1, 0, 0, 0,
      0, 1, 0, 0,
      0, 0, 0, 0,
      0, 0, 0, 1
    };

    Matrix p = getEye().sub(getPosition()).toMatrix();

    Matrix n = p.times(-1);
    Vector3d tmp = getUp().cross(getPosition().times(-1));


    Matrix vd = new Matrix(vals, 4);
    setProjection(new Matrix(vals, 4));
  }
}
