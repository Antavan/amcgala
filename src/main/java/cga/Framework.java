package cga;

import cga.scenegraph.animation.Animator;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.camera.PerspectiveCamera;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.AnimationVisitor;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.shape.Renderable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Giacinto
 */
public abstract class Framework {

  private static final Logger logger = Logger.getLogger(Framework.class.getName());
  public SceneGraph scenegraph;
  public RendererJ2d renderer;
  private Camera camera;
  private RenderVisitor renderVisitor;
  private AnimationVisitor animationVisitor;
  private Animator animator;

  public Framework() {
    logger.log(Level.INFO, "Initialisiere Framework.");
    logger.log(Level.INFO, "Erstelle Scenegraph.");
    scenegraph = new SceneGraph();

    camera = new PerspectiveCamera(new Vector3d(0, 1, 0), new Vector3d(0, 0, 1), new Vector3d(0, 0, 0));


    logger.log(Level.INFO, "Erstelle Java2D Renderoutput.");
    renderer = new RendererJ2d(800, 800);


    logger.log(Level.INFO, "Erstelle RenderVisitor.");
    renderVisitor = new RenderVisitor();
    renderVisitor.setCamera(camera);
    renderVisitor.setRenderer(renderer);

    logger.log(Level.INFO, "Erstelle Animator.");
    animator = new Animator(20);
    animationVisitor = new AnimationVisitor();
  }

  public abstract void initGraph();

  public void add(Renderable renderable) {
    scenegraph.addGeometry(renderable);
  }

  public void setCamera(Camera camera) {
    this.camera = camera;
  }

  public void setRenderer(RendererJ2d renderer) {
    this.renderer = renderer;
  }

  public void setScenegraph(SceneGraph scenegraph) {
    this.scenegraph = scenegraph;
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
