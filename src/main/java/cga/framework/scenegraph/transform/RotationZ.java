package cga.framework.scenegraph.transform;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.math.Matrix;
import cga.framework.scenegraph.Transformation;

/**
 * Eine Rotation um die z-Achse.
 * <p/>
 * <p/>
 * @author Robert Giacinto
 */
public class RotationZ implements Transformation {

    private double phi;
    private Interpolation interpolationPhi;
    private Matrix transformMatrix;

    /**
     * Erzeugt eine neues Rotationsobjekt.
     */
    public RotationZ() {
        this(0);
    }

    /**
   * Erzeugt eine neue Rotation entlang der y-Achse.
   *
   * @param phi der Winkel der Rotation
   */
    public RotationZ(double phi) {
        this.phi = phi;
        updateMatrix();
    }

    /**
     * Aktualisiert die Transformationsmatrix.
     */
    private void updateMatrix() {
        double[][] values = {
            {Math.cos(phi), -Math.sin(phi), 0, 0},
            {Math.sin(phi), Math.cos(phi), 0, 0},
            {0, 0, 1, 0},
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
