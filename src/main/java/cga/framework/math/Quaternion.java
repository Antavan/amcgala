package cga.framework.math;

/**
 * Ein Quaternion.
 *
 * @author Robert Giacinto
 */
public final class Quaternion {

  private double x, y, z, w;

  public Quaternion(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public Quaternion(Vector3d axis, double angle) {
    double angleRad = Math.toRadians(angle);
    double sin = Math.sin(angleRad / 2);
    double cos = Math.cos(angleRad / 2);
    x = axis.x * sin;
    y = axis.y * sin;
    z = axis.z * sin;
    w = cos;
    normalize();
  }

  public Quaternion times(Quaternion that) {
    x = w * that.x + x * that.w + y * that.z - z * that.y;
    y = w * that.y + y * that.w + z * that.x - x * that.z;
    z = w * that.z + z * that.w + x * that.y - y * that.x;
    w = w * that.w - x * that.x - y * that.y - z * that.z;
    return this;
  }

  public double length() {
    return Math.sqrt(lengthSquared());
  }

  public double lengthSquared() {
    return x * x + y * y + z * z + w * w;
  }

  public Quaternion set(Quaternion that) {
    this.x = that.x;
    this.y = that.y;
    this.z = that.z;
    this.w = that.w;
    return this;
  }

  public Quaternion set(double x, double y, double z, double w) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
    return this;
  }

  public void normalize() {
    double length = length();

    if (length > 0.0) {
      x /= length;
      y /= length;
      z /= length;
      w /= length;
    }
  }

  public Quaternion getNormalized() {
    double length = length();
    Quaternion tmp = null;

    if (length > 0.0) {
      double lx = x / length;
      double ly = y / length;
      double lz = z / length;
      double lw = w / length;
      tmp = new Quaternion(lx, ly, lz, lw);
    }
    return tmp;
  }

  /**
   * Setzt das Quaternion auf die übergebenen Eulerwinkel.
   * @param yaw der Scherwinkel
   * @param pitch der Kantenwinkel
   * @param roll der Rollwinkel
   * @return das Quaternion, das den Eulerwinkeln entspricht
   */
  public Quaternion fromAngles(double yaw, double pitch, double roll) {
    // Entnommen aus der JMonkeyEngine 3.0 und leicht verändert.
    double angle;
    double sinRoll, sinPitch, sinYaw, cosRoll, cosPitch, cosYaw;
    angle = pitch * 0.5f;
    sinPitch = Math.sin(angle);
    cosPitch = Math.cos(angle);
    angle = roll * 0.5f;
    sinRoll = Math.sin(angle);
    cosRoll = Math.cos(angle);
    angle = yaw * 0.5f;
    sinYaw = Math.sin(angle);
    cosYaw = Math.cos(angle);

    double cosRollXcosPitch = cosRoll * cosPitch;
    double sinRollXsinPitch = sinRoll * sinPitch;
    double cosRollXsinPitch = cosRoll * sinPitch;
    double sinRollXcosPitch = sinRoll * cosPitch;

    w = (cosRollXcosPitch * cosYaw - sinRollXsinPitch * sinYaw);
    x = (cosRollXcosPitch * sinYaw + sinRollXsinPitch * cosYaw);
    y = (sinRollXcosPitch * cosYaw + cosRollXsinPitch * sinYaw);
    z = (cosRollXsinPitch * cosYaw - sinRollXcosPitch * sinYaw);

    normalize();
    return this;
  }

  public Quaternion conjugate() {
    return new Quaternion(-x, -y, -z, w);
  }
  
  public Vector3d getRotationColumn(int i) {
       

        double norm = lengthSquared();
        Vector3d store = new Vector3d(0, 0, 0);

        double xx = x * x * norm;
        double xy = x * y * norm;
        double xz = x * z * norm;
        double xw = x * w * norm;
        double yy = y * y * norm;
        double yz = y * z * norm;
        double yw = y * w * norm;
        double zz = z * z * norm;
        double zw = z * w * norm;

        switch (i) {
            case 0:
                store.x = 1 - 2 * (yy + zz);
                store.y = 2 * (xy + zw);
                store.z = 2 * (xz - yw);
                break;
            case 1:
                store.x = 2 * (xy - zw);
                store.y = 1 - 2 * (xx + zz);
                store.z = 2 * (yz + xw);
                break;
            case 2:
                store.x = 2 * (xz + yw);
                store.y = 2 * (yz - xw);
                store.z = 1 - 2 * (xx + yy);
                break;
            default:
                throw new IllegalArgumentException("Invalid column index. " + i);
        }

        return store;
    }

  public Matrix toMatrix() {
    double[][] vals = {
      {x, y, z, w},
      {-y, x, -w, z},
      {-z, w, x, -y},
      {-w, -z, y, x}
    };
    return Matrix.constructWithCopy(vals);
  }

}
