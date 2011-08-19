package cga.framework.math;

/**
 * Eine Ebene im Raum. Sie wird für das Culling der Kamera benötigt. Sie ist
 * definiert gemäß der Gleichung n * (x,y,z) = c.
 *
 * @author Robert Giacinto
 */
public class Plane {

    public static enum Side {

        None,
        Positive,
        Negative
    }
    private Vector3d normal;
    private double constant;

    public Plane() {
    }

    public Plane(Vector3d normal, double constant) {
        this.normal = normal;
        this.constant = constant;
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }

    public Vector3d getNormal() {
        return normal;
    }

    public void setNormal(Vector3d normal) {
        if (normal == null) {
            throw new IllegalArgumentException("Normale darf nicht null sein!");
        }
        this.normal = normal;
    }

    public void setNormal(double x, double y, double z) {
        normal = new Vector3d(x, y, z);
    }

    public double distance(Vector3d point) {
        return normal.dot(point) - constant;
    }

    public Side whichSide(Vector3d point) {
        double distance = distance(point);
        if (distance < 0) {
            return Side.Negative;
        } else if (distance > 0) {
            return Side.Positive;
        } else {
            return Side.None;
        }
    }

    public boolean isOnPlane(Vector3d point) {
        double distance = distance(point);
        
        if (distance < MathConstants.EPSILON && distance > -MathConstants.EPSILON) {
            return true;
        } else {
            return false;
        }
    }
}
