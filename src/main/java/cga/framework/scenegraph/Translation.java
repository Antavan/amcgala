package cga.framework.scenegraph;

import cga.framework.animation.Alpha;
import cga.framework.math.Matrix;

/**
 * Eine Translation entlang der x-, y- und z-Achse.
 */
public class Translation implements Transformation {

    private double translateX;
    private double translateY;
    private double translateZ;
    private Alpha alpha;
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
        updateMatrix();

    }

    public void changeX(double diffX) {
        translateX += diffX;
        updateMatrix();
    }

    public void changeY(double diffY) {
        translateY += diffY;
        updateMatrix();
    }

    public void changeZ(double diffZ) {
        translateZ += diffZ;
        updateMatrix();
    }

    private void updateMatrix() {
        double[][] values = {
            {1, 0, 0, translateX},
            {0, 1, 0, translateY},
            {0, 0, 1, translateZ},
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
       double newVal = alpha.step();
       //TODO es müssen die einzelnen Achsen berücksichtigt werden. Entweder über einzelne Klassen oder über eine Art von Stellregler?
    }
}
