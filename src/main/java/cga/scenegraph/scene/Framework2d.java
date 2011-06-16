package cga.scenegraph.scene;

import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.shape.Renderable;

/**
 *
 * @author Robert Giacinto
 */
public abstract class Framework2d {

  public SceneGraph sceneGraph;
  public Scene2d scene2d;
  public RendererJ2d renderer;

  public Framework2d() {
    sceneGraph = new SceneGraph();

    RenderVisitor visitor = new RenderVisitor();
    renderer = new RendererJ2d(800, 800);
    visitor.setRenderer(renderer);

    scene2d = new Scene2d();
    scene2d.setScenegraph(sceneGraph);
    scene2d.setRenderVisitor(visitor);

    Animator animator = new Animator(20);
    scene2d.setAnimator(animator);
  }

  public abstract void initGraph();

  public void start() {
    scene2d.start();
  }

  public void addGeometry(Renderable renderable) {
    sceneGraph.addGeometry(renderable);
  }
}
