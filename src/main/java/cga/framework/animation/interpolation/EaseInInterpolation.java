package cga.framework.animation.interpolation;

/**
 * Eine Interpolation mit kleinen Schritten zu Beginn, die zum Ende hin größer
 * werden. Auf diese Weise ist es zum Beispiel möglich Beschleunigungen zu
 * animieren.
 *
 * @author Robert Giacinto
 */
public class EaseInInterpolation extends Interpolation {

    public EaseInInterpolation(double start, double end, int stepCount, boolean cyclic) {
        super(start, end, stepCount, cyclic);
    }

    @Override
    public double nextValue() {
        if (stepCounter++ < stepCount) {
            double x = (stepCounter / stepCount);
            x = Math.pow(x, 2);
            return min + max * x;
        } else if (cyclic) {
            stepCounter = 0;
        }
        return max;
    }
}
