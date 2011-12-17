/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package amcgala.framework.math;

/**
 * Ein Quaternion.
 *
 * @author Robert Giacinto
 */
public final class Quaternion {

    public static enum Axis {

        LookAt
    }
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

    /**
     * Multipliziert das Quaternion mit einem anderem Quaternion.
     *
     * @param that das zweite Quaternion
     * @return das Resultat der Multiplikation
     */
    public Quaternion times(Quaternion that) {
        x = w * that.x + x * that.w + y * that.z - z * that.y;
        y = w * that.y + y * that.w + z * that.x - x * that.z;
        z = w * that.z + z * that.w + x * that.y - y * that.x;
        w = w * that.w - x * that.x - y * that.y - z * that.z;
        return this;
    }

    /**
     * Die Länge des Quaternion.
     *
     * @return die Länge
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Die quadrierte Länge des Quaternion.
     *
     * @return die quadrierte Länge des Quaternion.
     */
    public double lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    /**
     * Setzt die Werte dieses Quaternion auf die Werte des anderen Quaternion.
     *
     * @param that das andere Quaternion
     * @return das Quaternion mit den neuen Werten
     */
    public Quaternion set(Quaternion that) {
        this.x = that.x;
        this.y = that.y;
        this.z = that.z;
        this.w = that.w;
        return this;
    }

    /**
     * Setzt die einzelnen Komponenten des Quaternions auf neue Werte.
     *
     * @param x die x-Komponente des Quaternions
     * @param y die y-Komponente des Quaternions
     * @param z die z-Komponente des Quaternions
     * @param w die w-Komponente des Quaternions
     * @return das Quaternion mit den neuen Werten
     */
    public Quaternion set(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    /**
     * Normalisiert das Quaternion auf eine Länge von 1.
     */
    public void normalize() {
        double length = length();

        if (length > 0.0) {
            x /= length;
            y /= length;
            z /= length;
            w /= length;
        }
    }

    /**
     * Gibt eine normalisierte Kopie des Quaternions zurück.
     *
     * @return die normalisierte Kopie des Quaternions.
     */
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
     *
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

    public Quaternion fromAxes() {
        return this;
    }

    /**
     * Konstruiert ein Quaternion auf Basis einer übergebenen Rotationsmatrix.
     * Der Algorithmus für diese Methode kommt aus dem Quaternion Tutorial von
     * Ken Shoemaker. Zu finden in
     * ftp://ftp.cis.upenn.edu/pub/graphics/shoemake/quatut.ps.Z
     *
     * @param m00 Komponente 1 der Matrix
     * @param m01 Komponente 2 der Matrix
     * @param m02 Komponente 3 der Matrix
     * @param m10 Komponente 4 der Matrix
     * @param m11 Komponente 5 der Matrix
     * @param m12 Komponente 6 der Matrix
     * @param m20 Komponente 7 der Matrix
     * @param m21 Komponente 8 der Matrix
     * @param m22 Komponente 9 der Matrix
     * @return das neue Quaternion
     */
    public Quaternion fromRotationMatrix(
            double m00, double m01, double m02,
            double m10, double m11, double m12,
            double m20, double m21, double m22) {
        double trace = m00 + m11 + m22;

        if (trace >= 0) {
            double s = Math.sqrt(trace + 1);
            w = 0.5 * s;
            s = 0.5 / s;
            x = (m21 - m12) * s;
            y = (m02 - m20) * s;
            z = (m10 - m01) * s;
        } else if ((m00 > m11) && (m00 > m22)) {
            double s = Math.sqrt(1 + m00 - m11 - m22);
            x = s * 0.5f;
            s = 0.5f / s;
            y = (m10 + m01) * s;
            z = (m02 + m20) * s;
            w = (m21 - m12) * s;
        } else if (m11 > m22) {
            double s = Math.sqrt(1.0f + m11 - m00 - m22); // |s|>=1
            y = s * 0.5f;
            s = 0.5f / s;
            x = (m10 + m01) * s;
            z = (m21 + m12) * s;
            w = (m02 - m20) * s;
        } else {
            double s = Math.sqrt(1.0f + m22 - m00 - m11); // |s|>=1
            z = s * 0.5f;
            s = 0.5f / s;
            x = (m02 + m20) * s;
            y = (m21 + m12) * s;
            w = (m10 - m01) * s;
        }
        return this;
    }

    /**
     * Erzeugt ein Quaternion, das das Koordinatensystem definiert, das über die
     * drei Achsen aufgespannt wird. Es wird davon ausgegangen, dass diese drei
     * Achsen orthogonal zueinander stehen. Auf eine Überprüfung wird in dieser
     * Methode daher verzichtet.
     *
     * @param xAxis die x-Achse des Koordinatensystems
     * @param yAxis die y-Achse des Koordinatensystems
     * @param zAxis die z-Achse des Koordinatensystems
     * @return das neue Quaternion
     */
    public Quaternion fromAxes(Vector3d xAxis, Vector3d yAxis, Vector3d zAxis) {
        return fromRotationMatrix(
                xAxis.x, yAxis.x, zAxis.x,
                xAxis.y, yAxis.y, zAxis.y,
                xAxis.z, yAxis.z, zAxis.z);
    }

    /**
     * Gibt das konjugierte Quaternion zurück.
     *
     * @return das konjugierte Quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    /**
     * Gibt eine Komponente des Quaternions zurück.
     *
     * @param i der Index der Komponente, die zurückgegeben werden soll. i
     * sollte zwischen 0 und 2 liegen
     * @return die i. Komponente
     */
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

    /**
     * Gibt die Matrixdarstellung des Quaternions zurück.
     * @return die Matrixdarstellung des Quaternions
     */
    public Matrix toMatrix() {
        double[][] vals = {
            {x, y, z, w},
            {-y, x, -w, z},
            {-z, w, x, -y},
            {-w, -z, y, x}
        };
        return Matrix.constructWithCopy(vals);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Quaternion other = (Quaternion) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Double.doubleToLongBits(this.w) != Double.doubleToLongBits(other.w)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        return hash;
    }
}
