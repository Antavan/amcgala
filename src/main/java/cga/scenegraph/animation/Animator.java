package cga.scenegraph.animation;

import cga.scenegraph.scene.Scene;

public class Animator {
  private Timer timer;
  private Scene scene;

  private int framesPerSecond;

  public Animator(int framesPerSecond) {
    this.framesPerSecond = framesPerSecond;
    timer = new Timer(framesPerSecond);
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public void start() {
    if (scene != null) {
      Thread animation = new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
            timer.start();
            scene.update();
            scene.show();
            timer.stop();
            try {
              Thread.sleep((long) timer.getSleepTime());
            } catch (InterruptedException e) {
            }
          }
        }
      });
      animation.start();
    }

  }
}
