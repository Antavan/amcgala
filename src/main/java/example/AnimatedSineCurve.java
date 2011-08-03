package example;

import cga.framework.animation.Animation;
import cga.framework.animation.interpolation.Interpolation;
import cga.framework.animation.interpolation.LinearInterpolation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Point2d;
import cga.framework.shape.Renderable;

/**
 * Eine animierte Sinuskurve.
 * <p/>
 * @author Robert Giacinto
 */
public class AnimatedSineCurve extends Renderable {

  private int steps;
  private Point2d[] points;
  private double frequency;
  private double amplitude;
  private Interpolation interpolationFrq;
  private Interpolation interpolationAmp;

  public AnimatedSineCurve(int steps, double frequency, double amplitude) {
    this.steps = steps;
    this.frequency = frequency;
    this.amplitude = amplitude;

    points = new Point2d[steps];

    init();

    setAnimation(new Animation<AnimatedSineCurve>() {

      @Override
      public void animate() {
        if (interpolationAmp != null) {
          setAmplitude(interpolationAmp.nextValue());
        }

        if (interpolationFrq != null) {
          setFrequency(interpolationFrq.nextValue());
        }
      }
    });
    interpolationFrq = new LinearInterpolation(15, 20, steps, true);
    interpolationAmp = new LinearInterpolation(15, 20, steps, true);
  }

  private void init() {
    int step = 0;
    while (step < steps) {
      points[step] = new Point2d(step * (Math.PI * 10 / steps) * 100, 10 * amplitude * Math.sin(step * (Math.PI * 10 / steps) * frequency*10));
      step++;
    }
  }

  public Interpolation getInterpolationAmp() {
    return interpolationAmp;
  }

  public void setInterpolationAmp(Interpolation interpolationAmp) {
    this.interpolationAmp = interpolationAmp;
  }

  public Interpolation getInterpolationFrq() {
    return interpolationFrq;
  }

  public void setInterpolationFrq(Interpolation interpolationFrq) {
    this.interpolationFrq = interpolationFrq;
  }

  public double getAmplitude() {
    return amplitude;
  }

  public void setAmplitude(double amplitude) {
    this.amplitude = amplitude;
    init();
  }

  public double getFrequency() {
    return frequency;
  }

  public void setFrequency(double frequency) {
    this.frequency = frequency;
    init();
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    for (Point2d p : points) {
      p.render(transformation, camera, renderer);
    }
  }
}
