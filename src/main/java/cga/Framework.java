package cga;

import cga.framework.animation.Animator;
import cga.framework.camera.Camera;
import cga.framework.camera.PerspectiveCamera;
import cga.framework.scenegraph.SceneGraph;
import cga.framework.scenegraph.visitor.AnimationVisitor;
import cga.framework.scenegraph.visitor.RenderVisitor;
import cga.framework.math.Vector3d;
import cga.framework.renderer.RendererJ2d;
import cga.framework.scenegraph.visitor.InterpolationVisitor;
import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.shape.Renderable;
import java.util.ArrayList;
import java.util.List;
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
  private Animator animator;
  private List<Visitor> visitors;

  public Framework() {

    logger.log(Level.INFO, "Initialisiere Framework.");
    logger.log(Level.INFO, "Erstelle Scenegraph.");
    visitors = new ArrayList<Visitor>();
    scenegraph = new SceneGraph();

    camera = new PerspectiveCamera(new Vector3d(0, 1, 0), new Vector3d(0, 0, 1), new Vector3d(0, 0, 0));


    logger.log(Level.INFO, "Erstelle Java2D Renderoutput.");
    renderer = new RendererJ2d(800, 800);

    logger.log(Level.INFO, "Füge InterpolationVisitor hinzu.");
    visitors.add(new InterpolationVisitor());

    logger.log(Level.INFO, "Erstelle Animator.");
    animator = new Animator(20);
    AnimationVisitor animationVisitor = new AnimationVisitor();
    visitors.add(animationVisitor);


    logger.log(Level.INFO, "Erstelle RenderVisitor.");
    RenderVisitor renderVisitor = new RenderVisitor();
    renderVisitor.setCamera(camera);
    renderVisitor.setRenderer(renderer);
    visitors.add(renderVisitor);



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
      for (Visitor v : visitors) {
        scenegraph.accept(v);
      }
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
      animator.setFramework(this);
      animator.start();
    }
  }
}
