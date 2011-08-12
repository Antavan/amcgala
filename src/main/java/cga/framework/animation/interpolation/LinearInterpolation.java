/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cga.framework.animation.interpolation;

/**
 * Eine lineare Interpolation zwischen zwei Werten.
 * <p/>
 * <p/>
 * @author Robert Giacinto
 */
public class LinearInterpolation extends Interpolation {

    public LinearInterpolation(double start, double end, int stepCount, boolean cyclic) {
        super(start, end, stepCount, cyclic);
    }

    @Override
    public double nextValue() {
        if (stepCounter++ < stepCount) {
            double x = (stepCounter / stepCount);
            return min + max * x;
        } else if (cyclic) {
            stepCounter = 0;
        }
        return max;
    }
}
