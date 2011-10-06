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

    public boolean isNearPlane(Vector3d point) {
        double distance = distance(point);
        
        if (distance < MathConstants.NEAR_EPSILON && distance > -MathConstants.NEAR_EPSILON) {
            return true;
        } else {
            return false;
        }
    }
}
