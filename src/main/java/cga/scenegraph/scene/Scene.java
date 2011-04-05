package cga.scenegraph.scene;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.Renderer;


abstract class Scene {
  private Camera camera;
  private Renderer renderer;
  private SceneGraph scenegraph;
  private RenderVisitor renderVisitor;

  protected Scene(SceneGraph scenegraph) {
    this.scenegraph = scenegraph;
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setRenderer(Renderer renderer) {
    this.renderer = renderer;
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
