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
package cga.framework.scenegraph.transform;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.math.Matrix;
import cga.framework.scenegraph.Transformation;

/**
 * Eine Rotation entlang der x-Achse.
 */
public class RotationX implements Transformation {

    private double phi;
    private Matrix transformMatrix;
    private Interpolation interpolationPhi;

    /**
     * Erstellt eine neues Rotationsobjekt.
     */
    public RotationX() {
        this(0);
    }

    /**
     * Erstellt ein Rotationsobjekt, das eine Rotation, um den Winkel phi beschreibt.
     * 
     * @param phi der Winkel phi der Rotation
     */
    public RotationX(double phi) {
        this.phi = phi;
        updateMatrix();
    }

    /**
     * Aktualisiert die Transformationsmatrix.
     */
    private void updateMatrix() {
        double[][] values = {
            {1, 0, 0, 0},
            {0, Math.cos(phi), -Math.sin(phi), 0},
            {0, Math.sin(phi), Math.cos(phi), 0},
            {0, 0, 0, 1}
        };
        transformMatrix = Matrix.constructWithCopy(values);
    }

    @Override
    public Matrix getTransformMatrix() {
        return transformMatrix;
    }

    public Interpolation getInterpolationPhi() {
        return interpolationPhi;
    }

    public void setInterpolationPhi(Interpolation interpolationPhi) {
        this.interpolationPhi = interpolationPhi;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
        updateMatrix();
    }

    @Override
    public void update() {
        if (interpolationPhi != null) {
            phi = interpolationPhi.nextValue();
            updateMatrix();
        }
    }
}
