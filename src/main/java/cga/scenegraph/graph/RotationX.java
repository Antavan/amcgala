package cga.scenegraph.graph;

import cga.scenegraph.animation.Alpha;
import cga.scenegraph.math.Matrix;

/**
 * Eine Rotation entlang der x-Achse.
 */
public class RotationX implements Transformation {

    private double phi;
    private Matrix transformMatrix;
    private Alpha alpha;

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
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        transformMatrix = Matrix.constructWithCopy(values);
    }

    @Override
    public Matrix getTransformMatrix() {
        return transformMatrix;
    }

    @Override
    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    @Override
    public void update() {
        //TODO Aktualisierung der Rotation mithilfe des Alphaobjekts muss implementiert werden.
    }
}
