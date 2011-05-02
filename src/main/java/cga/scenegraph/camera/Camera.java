package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;

/**
 * @author Robert Giacinto
 */
public class Camera {
  public static final int PROJECTION_TYPE_ORTHOGRAPHIC = 1;
  public static final int PROJECTION_TYPE_PERSPECTIVE = 2;

  private Matrix projection;

  private Vector3d near;
  private Vector3d far;

  private int currentProjectionType;


  public Camera(Vector3d near, Vector3d far, int projectionType) {
    if (projectionType > 2) {
      throw new IllegalArgumentException("use of unknown projection type");
    }

    this.near = near;
    this.far = far;
    if (projectionType == PROJECTION_TYPE_ORTHOGRAPHIC) {
      initOrthographicProjection();
    } else if (projectionType == PROJECTION_TYPE_PERSPECTIVE) {
      initPerspectiveProjection();
    }
    this.currentProjectionType = projectionType;
  }

  private void initOrthographicProjection() {
    double left = near.x;
    double right = far.x;

    double bottom = near.y;
    double top = far.y;

    double n = near.z;
    double f = far.z;

    double[] values = {
        2 / (right - left), 0, 0, -(right + left) / (right - left),
        0, 2 / (top - bottom), 0, -(top + bottom) / (top - bottom),
        0, 0, 2 / (f - n), -(f + n) / (f - n),
        0, 0, 0, 1
    };
    projection = new Matrix(values, 4);
  }

  private void initPerspectiveProjection() {
    double left = near.x;
    double right = far.x;

    double bottom = near.y;
    double top = far.y;

    double n = near.z;
    double f = far.z;

    double[] values = {
        (2 * n) / (right - left), 0, -(right + left) / (right - left),
        0, (2 * n) / (top - bottom), -(top + bottom) / (top - bottom), 0,
        0, 0, (f + n) / (f - n), -(2 * f * n) / (f - n),
        0, 0, 1, 0
    };
    projection = new Matrix(values, 4);
  }

  public CVPoint project(Vector3d vector3d) {
    Matrix point = projection.times(vector3d.toMatrix());
    CVPoint cvPoint = new CVPoint(point.get(0, 0), point.get(1, 0));
    return cvPoint;
  }


  public Matrix getProjection() {
    return projection;
  }

  private void setProjection(Matrix projection) {
    this.projection = projection;
  }

  public Vector3d getNear() {
    return near;
  }

  public void setNear(Vector3d near) {
    this.near = near;

    if (currentProjectionType == PROJECTION_TYPE_ORTHOGRAPHIC) {
      initOrthographicProjection();
    } else if (currentProjectionType == PROJECTION_TYPE_PERSPECTIVE) {
      initPerspectiveProjection();
    }
  }

  public Vector3d getFar() {
    return far;
  }

  public void setFar(Vector3d far) {
    this.far = far;

    if (currentProjectionType == PROJECTION_TYPE_ORTHOGRAPHIC) {
      initOrthographicProjection();
    } else if (currentProjectionType == PROJECTION_TYPE_PERSPECTIVE) {
      initPerspectiveProjection();
    }
  }
}
