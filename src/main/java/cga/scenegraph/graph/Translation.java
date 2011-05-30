package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Eine Translation entlang der x-, y- und z-Achse.
 */
public class Translation implements Transformation {

    private double translateX;
    private double translateY;
    private double translateZ;
    private Matrix transformMatrix;

    /**
     * Erstellt eine neue Translation.
     *
     * @param translateX Translation entlang der x-Achse
     * @param translateY Translation entlang der y-Achse
     * @param translateZ Translation entlang der z-Achse
     */
    public Translation(double translateX, double translateY, double translateZ) {
        this.translateX = translateX;
        this.translateY = translateY;
        this.translateZ = translateZ;
        double[] values = {
            1, 0, 0, translateX,
            0, 1, 0, translateY,
            0, 0, 1, translateZ,
            0, 0, 0, 1
        };
        // TODO hier stehen die falschen werte drin
        transformMatrix = new Matrix(values, 4);
    }

    @Override
    public Matrix getTransformMatrix() {
        return transformMatrix;
    }
}
