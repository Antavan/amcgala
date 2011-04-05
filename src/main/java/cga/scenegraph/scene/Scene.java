package cga.scenegraph.scene;

import cga.scenegraph.animation.Timer;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.Renderer;


abstract class Scene {
  private Camera camera;
  private Renderer renderer;
  private SceneGraph scenegraph;
  private RenderVisitor renderVisitor;
  private Timer timer;

  protected Scene() {
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setRenderer(Renderer renderer) {
    this.renderer = renderer;
  }

  public void setScenegraph(SceneGraph scenegraph) {
    this.scenegraph = scenegraph;
  }

  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  public void setRenderVisitor(RenderVisitor renderVisitor) {
    this.renderVisitor = renderVisitor;
    renderVisitor.setCamera(camera);
    renderer = renderVisitor.getRenderer();
  }

  public void update() {
    if (camera != null) {
      scenegraph.accept(renderVisitor);
    }
  }

  public void show() {
    if (renderer != null) {
      renderer.show();
    }
  }
}
