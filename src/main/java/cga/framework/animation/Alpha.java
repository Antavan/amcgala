package cga.framework.animation;

import cga.framework.animation.interpolation.Interpolation;
import cga.framework.animation.interpolation.LinearInterpolation;

/**
 * Ein Alphaobjekt ermöglicht die lineare Interpolation zwischen zwei Werten.
 * 
 * @author Robert Giacinto
 */
public class Alpha {

  private double min;
  private double max;
  private int stepCount;
  private boolean cyclic;
  private Interpolation interpolation;

  public Alpha(double min, double max) {
    this(min, max, 1, true);
  }

  public Alpha(double min, double max, int stepCount) {
    this(min, max, stepCount, true);
  }

  public Alpha(double min, double max, int stepCount, boolean cyclic) {
    this.min = min;
    this.max = max;
    this.stepCount = stepCount;
    this.cyclic = cyclic;
    // TODO Interpolation sollte mit übergeben werden an Alpha...oder Alpha verschwindet.
    this.interpolation = new LinearInterpolation(min, max, stepCount, cyclic);
  }

  public double step() {
    //TODO die Abfrage, ob die Interpolation unendlich ist muss noch behandelt werden.
    return interpolation.nextValue();
  }
}
