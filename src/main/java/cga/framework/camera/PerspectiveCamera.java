package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.scenegraph.Translation;

/**
 * Diese Klasse implementiert eine Kamera mit perspektivischer Projektion. Aus
 * den Vektoren für Position, Blickrichtung und "oben" wird die Projektionsmatrix 
 * bestimmt. <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/>
 * <p/> <p/> <p/> <p/>
 * <p/>
 * @author Robert Giacinto
 */
public final class PerspectiveCamera implements Camera {

  /**
   * "oben" Vektor
   */
  private Vector3d vup;
  /**
   * Position der Kamera
   */
  private Vector3d eye;
  /**
   * Punkt, zu dem die Kamera blickt
   */
  private Vector3d lookAt;
  private Vector3d u;
  private Vector3d v;
  private Vector3d n;
  private double d;
  private double aspectRatio;
  private double fov;
  private double near;
  private double far;
  private Matrix projection;
  private Matrix view;
  private Matrix combined;

  /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten Blickpunkt.
     * 
     * @param vup Das Oben der Kamera
     * @param eye Die Position der Kamera
     * @param lookAt Der Punkt, zu dem die Kamera blickt
     * @param fov Der Öffnungswinkel der Kamera
     * @param aspectRatio Das Seitenverhältnis des Sichtvolumens
     */
  public PerspectiveCamera(Vector3d eye, Vector3d lookAt, Vector3d vup, double fov, double aspectRatio, double near, double far) {
    this.eye = eye;
    this.lookAt = lookAt;
    this.vup = vup;
    this.fov = fov;
    this.near = near;
    this.far = far;
    this.aspectRatio = aspectRatio;
    update();
  }

  @Override
  public Matrix getProjection() {
    return projection;
  }

  @Override
  public CVPoint project(Vector3d vector3d) {
    Matrix point = combined.times(vector3d.toMatrix());
    CVPoint cvPoint = new CVPoint(point.get(0, 0)/point.get(3, 0), point.get(1, 0)/point.get(3, 0));
    return cvPoint;
  }

  @Override
  public void update() {
    double l_fd = (1.0 / Math.tan((fov * (Math.PI / 180)) / 2.0));
    double l_a1 = (far + near) / (near - far);
    double l_a2 = (2 * far * near) / (near - far);
    double[][] mValues = {
      {l_fd / aspectRatio, 0, 0, 0},
      {0, l_fd, 0, 0},
      {0, 0, l_a1, -1},
      {0, 0, l_a2, 0}
    };
    projection = Matrix.constructWithCopy(mValues);

    n = eye.sub(lookAt).times(-1).normalize();
    u = vup.cross(n).normalize();
    v = n.cross(u);

    double[][] viewValues = {
      {u.x, u.y, u.z, 0},
      {v.x, v.y, v.z, 0},
      {n.x, n.y, n.z, 0},
      {0, 0, 0, 1}
    };
    view = Matrix.constructWithCopy(viewValues);
    Translation translation = new Translation(-eye.x, -eye.y, -eye.z);
    view = view.times(translation.getTransformMatrix());
    
    combined = projection.times(view);
    combined.print(4, 2);
  }
}
