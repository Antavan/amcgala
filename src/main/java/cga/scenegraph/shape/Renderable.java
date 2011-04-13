package cga.scenegraph.shape;

import cga.scenegraph.animation.Animation;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.renderer.Renderer;


public abstract class Renderable {
  private Animation animation;

  public abstract void render(Camera camera, Renderer renderer);

  public void setAnimation(Animation animation) {
    this.animation = animation;
  }

  public Animation getAnimation() {
    return animation;
  }
}
