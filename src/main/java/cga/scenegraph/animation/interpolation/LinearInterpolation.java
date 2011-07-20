/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cga.scenegraph.animation.interpolation;

/**
 * Eine lineare Interpolation zwischen zwei Werten. <p/>
 * <p/>
 * @author Robert Giacinto
 */
public class LinearInterpolation implements Interpolation {

  private double start;
  private double end;
  private int stepCount;
  private double step;
  private int stepCounter;
  private boolean cyclic;

  public LinearInterpolation(double start, double end, int stepCount, boolean cyclic) {
    this.start = start;
    this.end = end;
    this.stepCount = stepCount;
    this.cyclic = cyclic;
    step = (end - start) / stepCount;
  }

  @Override
  public double nextValue() {
    if (stepCounter++ <= stepCount) {
      return start + stepCounter * step;
    } else {
      if (cyclic) {
        stepCounter = -1;
      }
      return end;
    }
  }
}
