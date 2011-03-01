package cga.scenegraph.math;

public class Vector3d {
    private double x, y, z;

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public double dot(Vector3d that) {
        return this.x * that.x + this.y * that.y + this.z * that.z;
    }

    public Vector3d cross(Vector3d that) {
        return new Vector3d(
                this.y * that.z - this.z * that.y,
                this.z * that.x - this.x * that.z,
                this.x * that.y - this.y * that.x);
    }

    public double length() {
        return Math.sqrt(dot(this));
    }

    public double lengthSquared() {
        return dot(this);
    }

    public Vector3d times(double s) {
        return new Vector3d(
                s * this.x,
                s * this.y,
                s * this.z);
    }

    public Matrix toMatrix() {
        double[] vals = {x, y, z, 1};
        return new Matrix(vals, 4);
    }
}
