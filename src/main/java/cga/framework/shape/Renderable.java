package cga.framework.shape;

import cga.framework.animation.Animation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;


public abstract class Renderable {
  private Animation animation;

  public abstract void render(Matrix transformation, Camera camera, Renderer renderer);

  public void setAnimation(Animation animation) {
    this.animation = animation;
    this.animation.setShape(this);
  }

  public Animation getAnimation() {
    return animation;
  }
}
