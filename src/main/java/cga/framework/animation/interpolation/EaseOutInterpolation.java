package cga.framework.animation.interpolation;

/**
 * Eine Interpolation, bei der die Schritt zu Beginn groß sind und zum Ende hin
 * kleiner werden. <p/> <p/>
 * <p/>
 * @author Robert Giacinto
 */
public class EaseOutInterpolation implements Interpolation {

  private double min;
  private double max;
  private double easeOut;
  private int stepCount;
  private double stepCounter;
  private int stepsPhase1;
  private int stepsPhase2;
  private int currentPhase;
  private boolean cyclic;
  private LinearInterpolation interpolationPhase1;

  public EaseOutInterpolation(double min, double max, int stepCount) {
    this(min, max, 0.5, stepCount, false);

  }

  public EaseOutInterpolation(double min, double max, double easeOut, int stepCount) {
    this(min, max, easeOut, stepCount, false);
  }

  public EaseOutInterpolation(double min, double max, double easeOut, int stepCount, boolean cyclic) {
    this.min = min;
    this.max = max;
    this.stepCount = stepCount;
    this.easeOut = easeOut;
    stepsPhase1 = (int) (stepCount * easeOut);
    stepsPhase2 = stepCount - stepsPhase1;
    currentPhase = 1;
    interpolationPhase1 = new LinearInterpolation(min, max * easeOut, stepsPhase1, false);
  }

  @Override
  public double nextValue() {
    if (currentPhase == 1) {
      return interpolationPhase1.nextValue();
    } else {
      //TODO EaseOut-Phase fehlt noch!
      return 0;
    }
  }
}
