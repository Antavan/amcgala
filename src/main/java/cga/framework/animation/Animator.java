package cga.framework.animation;

import cga.Framework;

public class Animator {

    private Timer timer;
    private Framework framework;
    private int framesPerSecond;

    public Animator(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        timer = new Timer(framesPerSecond);
    }

    public void setScene(Framework framework) {
        this.framework = framework;
  }

    public void start() {

        if (framework != null) {
            Thread animation = new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        timer.start();
                        framework.update();
                        framework.show();
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
