package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;

public class OrthoCamera extends Camera {

  public OrthoCamera() {
    super(new Vector3d(0, -60, 0), new Vector3d(0, 0, 1), new Vector3d(0, 1, 0));
  }

  /**
   * Erzeugt eine neue Parallelprojektion.
   *
   * @param position Position der Kamera
   * @param eye      Blickpunkt der Kamera
   * @param up       "oben"-Vektor der Kamera
   */
  public OrthoCamera(Vector3d position, Vector3d eye, Vector3d up) {
    super(position, eye, up);
  }

  @Override
  public Pixel project(Vector3d vector3d) {
    Matrix r = getProjection().times(vector3d.toMatrix());
    Pixel pixel = new Pixel(r.get(0, 0), r.get(1, 0));
    return pixel;
  }

  @Override
  public void initProjection() {
    double[] vals = {
      1, 0, 0, 0,
      0, 1, 0, 0,
      0, 0, 0, 0,
      0, 0, 0, 1
    };

    Matrix vd = new Matrix(vals, 4);

    Matrix p = getPosition().sub(getEye()).toMatrix();

    Matrix n = p.times(-1);
    Matrix u = getUp().cross(n.toVector3d()).normalize().toMatrix();


    Matrix v = n.toVector3d().cross(u.toVector3d()).toMatrix();

    double u1 = u.get(0, 0);
    double u2 = u.get(1, 0);
    double u3 = u.get(2, 0);

    double v1 = v.get(0, 0);
    double v2 = v.get(1, 0);
    double v3 = v.get(2, 0);

    double n1 = n.get(0, 0);
    double n2 = n.get(1, 0);
    double n3 = n.get(2, 0);

    double[] ktVals = {
      u1, u2, u3, 0,
      v1, v2, v3, 0,
      n1, n2, n3, 0,
      0, 0, 0, 1
    };

    Matrix kt = new Matrix(ktVals, 4);

    Matrix projectionMatrix = vd.times(kt);

    setProjection(projectionMatrix);
  }
}
