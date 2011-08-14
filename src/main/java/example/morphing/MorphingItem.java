package example.morphing;

import cga.framework.animation.Animation;
import cga.framework.animation.interpolation.Interpolation;
import cga.framework.animation.interpolation.LinearInterpolation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Line2d;
import cga.framework.shape.Shape;

/**
 *
 * @author Robert Giacinto
 */
public class MorphingItem extends Shape {

    private Line2d line1;
    private Line2d line2;
    private int stepcount;

    public MorphingItem(Line2d start, Line2d end, int steps) {
        line1 = start;
        line2 = end;
        this.stepcount = steps;

        setAnimation(new Animation<Line2d>() {

            private Interpolation interpolationX1 = new LinearInterpolation(line1.x1, line2.x1, stepcount, true);
            private Interpolation interpolationX2 = new LinearInterpolation(line1.x2, line2.x2, stepcount, true);
            private Interpolation interpolationY1 = new LinearInterpolation(line1.y1, line2.y1, stepcount, true);
            private Interpolation interpolationY2 = new LinearInterpolation(line1.y2, line2.y2, stepcount, true);

            @Override
            public void animate() {
                line1.x1 = interpolationX1.nextValue();
                line1.x2 = interpolationX2.nextValue();
                line1.y1 = interpolationY1.nextValue();
                line1.y2 = interpolationY2.nextValue();
            }
        });
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        line1.render(transformation, camera, renderer);
    }
}
