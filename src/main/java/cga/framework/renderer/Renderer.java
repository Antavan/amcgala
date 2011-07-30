package cga.framework.renderer;

import cga.framework.camera.CVPoint;

/**
 * Wird von jedem Renderer erweitert und stellt die Funktionen putPixel und show zur Verfügung.
 */
public abstract class Renderer {
  private int width;
  private int height;

  protected Renderer(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public abstract void putPixel(Pixel pixel);

  public abstract void show();

  public abstract Pixel toPixel(CVPoint cvPoint);
}
