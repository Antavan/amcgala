package cga.scenegraph.animation;


import cga.scenegraph.shape.Renderable;

public abstract class Animation<T extends Renderable> {
  private T shape;

  public Animation(T shape) {
    this.shape = shape;
  }

  public T getShape() {
    return shape;
  }

  public abstract void animate();
}
