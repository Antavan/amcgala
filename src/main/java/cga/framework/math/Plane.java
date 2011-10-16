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

    /**
     * Erzeugt eine neue Ebene.
     * @param normal die Normale der Ebene
     * @param constant die Konstant der Ebene
     */
    public Plane(Vector3d normal, double constant) {
        this.normal = normal;
        this.constant = constant;
    }

    /**
     * Gibt die Konstante der Ebenengleichung zurück.
     * @return die Konstante
     */
    public double getConstant() {
        return constant;
    }

    /**
     * Setzt die Konstante der Ebenengleichung.
     * @param constant die neue Konstante
     */
    public void setConstant(double constant) {
        this.constant = constant;
    }

    /**
     * Gibt die Normale der Ebene zurück.
     * @return die Normale der Ebene
     */
    public Vector3d getNormal() {
        return normal;
    }

    /**
     * Ändert die Normale der Ebene.
     * @param normal die Normale
     */
    public void setNormal(Vector3d normal) {
        if (normal == null) {
            throw new IllegalArgumentException("Normale darf nicht null sein!");
        }
        this.normal = normal;
    }

    /**
     * Ändert die Normale der Ebene.
     * @param x die x-Komponente der Normalen
     * @param y die y-Komponente der Normalen
     * @param z die z-Komponente der Normalen
     */
    public void setNormal(double x, double y, double z) {
        normal = new Vector3d(x, y, z);
    }

    /**
     * Gibt die Entfernung des Punktes von der Ebene zurück.
     * @param point der Punkt, der überprüft werden soll
     * @return die Entfernung von der Ebene
     */
    public double distance(Vector3d point) {
        return normal.dot(point) - constant;
    }

    /**
     * Überprüft, auf welcher Seite der Ebene der gegebene Punkt liegt.
     * Es gibt drei Möglichkeiten.
     * 
     * 1. Unter der Ebene (Side.Negative)
     * 2. Über der Ebene (Side.Positive)
     * 3. In der Ebene (Side.None)
     * 
     * @param point der Punkt, der überprüft werden soll
     * @return Lage des Punktes
     */
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

    /**
     * Überprüft, ob ein Punkt auf dieser Ebene liegt.
     * @param point der Punkt, der überprüft werden soll
     * @return true, wenn Punkt auf Ebene liegt
     */
    public boolean isOnPlane(Vector3d point) {
        double distance = distance(point);

        if (distance < MathConstants.EPSILON && distance > -MathConstants.EPSILON) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prüft, ob ein Punkt in der neuer dieser Ebene liegt. Die Standardentfernung kann in der Klasse
     * MathConstants.NEAR_EPSILON nachgeschaut werden.
     * 
     * @param point der Punkt der überprüft werden soll
     * @return true, wenn Punkt in der Nähe der Ebene
     */
    public boolean isNearPlane(Vector3d point) {
        double distance = distance(point);
        if (distance < MathConstants.NEAR_EPSILON && distance > -MathConstants.NEAR_EPSILON) {
            return true;
        } else {
            return false;
        }
    }
}
