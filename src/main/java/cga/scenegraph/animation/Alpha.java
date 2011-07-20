package cga.scenegraph.animation;

import cga.scenegraph.animation.interpolation.Interpolation;
import cga.scenegraph.animation.interpolation.LinearInterpolation;

/**
 * Ein Alphaobjekt ermöglicht die lineare Interpolation zwischen zwei Werten.
 * 
 * @author Robert Giacinto
 */
public class Alpha {

  private double start;
  private double end;
  private int stepCount;
  private boolean cyclic;
  private Interpolation interpolation;

  public Alpha(double start, double end) {
    this(start, end, 1, true);
  }

  public Alpha(double start, double end, int stepCount) {
    this(start, end, stepCount, true);
  }

  public Alpha(double start, double end, int stepCount, boolean cyclic) {
    this.start = start;
    this.end = end;
    this.stepCount = stepCount;
    this.cyclic = cyclic;
    this.interpolation = new LinearInterpolation(start, end, stepCount, cyclic);
  }

  public double step() {
    //TODO die Abfrage, ob die Interpolation unendlich ist muss noch behandelt werden.
    return interpolation.nextValue();
  }
}
