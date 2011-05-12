package cga.scenegraph.scene;

import cga.scenegraph.animation.Animator;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.AnimationVisitor;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.Renderer;

public abstract class Scene {

  public Animator getAnimator() {
    return animator;
  }

  public Camera getCamera() {
    return camera;
  }

  public Renderer getRenderer() {
    return renderer;
  }

  public SceneGraph getScenegraph() {
    return scenegraph;
  }
  private Camera camera;
  private Renderer renderer;
  private SceneGraph scenegraph;
  private RenderVisitor renderVisitor;
  private AnimationVisitor animationVisitor;
  private Animator animator;

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

  public void setAnimator(Animator animator) {
    this.animator = animator;
    animationVisitor = new AnimationVisitor();
  }

  public void setRenderVisitor(RenderVisitor renderVisitor) {
    this.renderVisitor = renderVisitor;
    renderVisitor.setCamera(camera);
    renderer = renderVisitor.getRenderer();
  }

  public void update() {
    if (camera != null) {
      scenegraph.accept(renderVisitor);
      scenegraph.accept(animationVisitor);
    }
  }

  public void show() {
    if (renderer != null) {
      renderer.show();
    }
  }

  public void start() {
    if (animator == null) {
      update();
      show();
    } else {
      animator.setScene(this);
      animator.start();
    }
  }
}
