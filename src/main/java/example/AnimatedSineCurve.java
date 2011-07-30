package example;

import cga.framework.animation.Alpha;
import cga.framework.animation.Animation;
import cga.framework.animation.interpolation.LinearInterpolation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Point2d;
import cga.framework.shape.Renderable;

/**
 * Eine animierte Sinuskurve. <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/> <p/>
 * <p/>
 * @author Robert Giacinto
 */
public class AnimatedSineCurve extends Renderable {

  private int steps;
  private Point2d[] points;
  private double frequency;
  private double amplitude;
  private Alpha alphaFrq;
  private Alpha alphaAmp;

  public AnimatedSineCurve(int steps, double frequency, double amplitude) {
    this.steps = steps;
    this.frequency = frequency;
    this.amplitude = amplitude;

    points = new Point2d[steps];

    init();

    setAnimation(new Animation<AnimatedSineCurve>() {

      @Override
      public void animate() {
        if (alphaAmp != null) {
          setAmplitude(alphaAmp.step());
        }

        if (alphaFrq != null) {
          setFrequency(alphaFrq.step());
        }
      }
    });
    alphaFrq = new Alpha(0, 10, 200);
    alphaAmp = new Alpha(0, 20, 250);
  }

  private void init() {
    int step = 0;
    while (step < steps) {
      points[step] = new Point2d(step * (Math.PI * 10 / steps) * 10, 10 * amplitude * Math.sin(step * (Math.PI * 10 / steps) * frequency));
      step++;
    }
  }

  public Alpha getAlphaAmp() {
    return alphaAmp;
  }

  public void setAlphaAmp(Alpha alphaAmp) {
    this.alphaAmp = alphaAmp;

  }

  public Alpha getAlphaFrq() {
    return alphaFrq;
  }

  public void setAlphaFrq(Alpha alphaFrq) {
    this.alphaFrq = alphaFrq;
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
