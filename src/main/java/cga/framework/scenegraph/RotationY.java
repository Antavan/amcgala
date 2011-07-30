package cga.framework.scenegraph;

import cga.framework.animation.Alpha;
import cga.framework.math.Matrix;

/**
 * Eine Rotation entlang der y-Achse.
 */
public class RotationY implements Transformation {

    private double phi;

    /**
     * Erzeugt eine neues Rotationsobjekt.
     */
    public RotationY() {
        this(0);
    }

    /**
   * Erzeugt eine neue Rotation entlang der y-Achse.
   *
   * @param phi der Winkel der Rotation
   */
    public RotationY(double phi) {
        this.phi = phi;
    }

    @Override
    public Matrix getTransformMatrix() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setAlpha(Alpha alpha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
