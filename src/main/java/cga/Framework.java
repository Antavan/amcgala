package cga;

import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Renderable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Giacinto
 */
public abstract class Framework {
    
    public SceneGraph sceneGraph;
    public Scene2d scene2d;
    public RendererJ2d renderer;
    private static final Logger logger = Logger.getLogger(Framework.class.getName());
    
    public Framework() {
        logger.log(Level.INFO, "Initialisiere Framework.");
        logger.log(Level.INFO, "Erstelle Scenegraph.");
        sceneGraph = new SceneGraph();
        
        logger.log(Level.INFO, "Erstelle RenderVisitor.");
        RenderVisitor visitor = new RenderVisitor();
        
        logger.log(Level.INFO, "Erstelle Java2D Renderoutput.");
        renderer = new RendererJ2d(800, 800);
        visitor.setRenderer(renderer);
        
        logger.log(Level.INFO, "Erstelle 2d Szene.");
        scene2d = new Scene2d();
        
        logger.log(Level.INFO, "Übergebe Scenegraph an Szene.");
        scene2d.setScenegraph(sceneGraph);
        
        logger.log(Level.INFO, "Übergebe RenderVisitor an Szene.");
        scene2d.setRenderVisitor(visitor);
        
        logger.log(Level.INFO, "Erstelle Animator.");
        Animator animator = new Animator(20);
        
        logger.log(Level.INFO, "Übergebe Animator an Szene.");
        scene2d.setAnimator(animator);
    }
    
    public abstract void initGraph();
    
    public void start() {
        scene2d.start();
    }
    
    public void add(Renderable renderable) {
        sceneGraph.addGeometry(renderable);
    }
}
