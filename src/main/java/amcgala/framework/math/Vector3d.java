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
 * Repräsentation von 3D-Vektoren.
 */
public class Vector3d {

    public static final Vector3d UNIT_X = new Vector3d(1, 0, 0);
    public static final Vector3d UNIT_Y = new Vector3d(0, 1, 0);
    public static final Vector3d UNIT_Z = new Vector3d(0, 0, 1);
    public static final Vector3d ZERO = new Vector3d(0, 0, 0);
    public double x, y, z;

    @Override
    public String toString() {
        return "Vector3d{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

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
                this.z - that.z);
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
                this.z + that.z);
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
        x *= s;
        y *= s;
        z *= s;
        return this;
    }

    /**
     * Gibt den Vektor normalisiert zurück.
     *
     * @return der normalisierte Vektor
     */
    public Vector3d normalize() {
        double norm = 1.0 / length();
        times(norm);
        return this;
    }

    /**
     * Gibt den Vektor in einer 4d Matrix zurück. Diese wird für viele Transformationen benötigt.
     * @return die Matrixrepräsentation des Vektors
     */
    public Matrix toMatrix() {
        double[] vals = {x, y, z, 1};
        return new Matrix(vals, 4);
    }

    /**
     * Transformiert den Vektor mithilfe einer Transformationsmatrix und gibt eine Kopie des transformierten Vektors zurück.
     * @param transformation die Transformationsmatrix
     * @return der transformierte Vektor
     */
    public Vector3d transform(Matrix transformation) {
        Matrix tmp = transformation.times(toMatrix());
        return new Vector3d(tmp.get(0, 0), tmp.get(1, 0), tmp.get(2, 0));
    }
}
