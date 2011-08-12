package cga.framework.animation;


import cga.framework.shape.Shape;

public abstract class Animation<T extends Shape> {
  private T shape;

  public T getShape() {
    return shape;
  }

  public void setShape(T shape) {
    this.shape = shape;
  }

  public abstract void animate();
}
