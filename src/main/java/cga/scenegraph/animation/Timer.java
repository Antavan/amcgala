package cga.scenegraph.animation;


public class Timer {
  private double framesPerSecond;
  private double startTime;
  private double stopTime;
  private double timePerFrame;
  private double duration;


  public Timer(double framesPerSecond) {
    this.framesPerSecond = framesPerSecond;
    timePerFrame = 1000 / framesPerSecond;
  }

  public void start() {
    startTime = System.currentTimeMillis();
  }

  public void stop() {
    stopTime = System.currentTimeMillis();
    duration = stopTime - startTime;
  }

  public double getSleepTime() {
    return (timePerFrame - duration < 0) ? 1 : (timePerFrame - duration);
  }


}
