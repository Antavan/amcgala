package cga.scenegraph.animation;

/**
 * Ein Alphaobjekt ermöglicht die lineare Interpolation zwischen zwei Werten.
 * 
 * @author Robert Giacinto
 */
public class Alpha {

    private double start;
    private double end;
    private double step;
    private boolean cyclic;
    private double current;

    public Alpha(double start, double end) {
        this(start, end, 1, true);
    }

    public Alpha(double start, double end, double step) {
        this(start, end, step, true);
    }

    public Alpha(double start, double end, double step, boolean cyclic) {
        this.start = start;
        this.end = end;
        this.step = step;
        this.cyclic = cyclic;
        this.current = start;
    }

    public double step() {
        if (current + step > end && !cyclic) {
            return end;
        } else if (current + step > end && cyclic) {
            current = start;
            return current;
        } else {
            current += step;
            return current;
        }
    }
}
