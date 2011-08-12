package cga.framework.animation.interpolation;

/**
 * Interface, das alle Klassen implementieren, die eine Interpolation
 * darstellen.
 * <p/>
 * @author Robert Giacinto
 */
public abstract class Interpolation {

    protected double min;
    protected double max;
    protected double stepCount;
    protected double stepCounter;
    protected boolean cyclic;

    public Interpolation(double start, double end, int stepCount, boolean cyclic) {
        this.min = start;
        this.max = end;
        this.stepCount = stepCount;
        this.cyclic = cyclic;
    }

    public abstract double nextValue();
}
