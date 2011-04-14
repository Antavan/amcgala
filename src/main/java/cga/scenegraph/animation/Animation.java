package cga.scenegraph.animation;


import cga.scenegraph.shape.Renderable;

public abstract class Animation<T extends Renderable> {
  private T shape;

  public T getShape() {
    return shape;
  }

  public void setShape(T shape){
    this.shape = shape;
  }

  public abstract void animate();
}
