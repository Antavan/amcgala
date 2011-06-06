package example;

import cga.scenegraph.animation.Animator;
import cga.scenegraph.graph.SceneGraph;
import cga.scenegraph.graph.Translation;
import cga.scenegraph.graph.visitor.RenderVisitor;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.RendererJ2d;
import cga.scenegraph.scene.Scene2d;
import cga.scenegraph.shape.Line2d;
import cga.scenegraph.shape.Line3d;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LineDrawingExample {

  private SceneGraph sceneGraph;
  private Translation translation;

  public LineDrawingExample() {
    sceneGraph = new SceneGraph();
    initGraph();

    translation = new Translation(-4, 0, 0);
    sceneGraph.addTransformation(translation);

    RenderVisitor visitor = new RenderVisitor();
    RendererJ2d renderer = new RendererJ2d(1600, 800);
    //renderer.addMouseListener(new MyListener());
    renderer.addKeyListener(new MyKeyListener());

    visitor.setRenderer(renderer);

    Scene2d scene2d = new Scene2d();
    scene2d.setScenegraph(sceneGraph);
    scene2d.setRenderVisitor(visitor);

    Animator animator = new Animator(20);
    scene2d.setAnimator(animator);

    scene2d.start();
  }

  private void initGraph() {
    Line3d line3d = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(7, -3, -1));
    Line3d line3d2 = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(-3, -3, -11));
    Line3d line3d3 = new Line3d(new Vector3d(-3, -3, -1), new Vector3d(-3, 7, -1));

    Line3d line3d4 = new Line3d(new Vector3d(-3, -3, -11), new Vector3d(-3, 7, -11));
    Line3d line3d5 = new Line3d(new Vector3d(-3, -3, -11), new Vector3d(7, -3, -11));

    Line3d line3d6 = new Line3d(new Vector3d(-3, 7, -1), new Vector3d(-3, 7, -11));
    Line3d line3d7 = new Line3d(new Vector3d(-3, 7, -11), new Vector3d(7, 7, -11));

    Line3d line3d8 = new Line3d(new Vector3d(7, -3, -1), new Vector3d(7, 7, -1));
    Line3d line3d9 = new Line3d(new Vector3d(7, -3, -11), new Vector3d(7, -3, -1));

    Line3d line3d10 = new Line3d(new Vector3d(7, 7, -11), new Vector3d(7, 7, -1));
    Line3d line3d11 = new Line3d(new Vector3d(7, -3, -11), new Vector3d(7, 7, -11));

    Line3d line3d12 = new Line3d(new Vector3d(-3, 7, -1), new Vector3d(7, 7, -1));

    // sceneGraph.addGeometry(line);
    sceneGraph.addGeometry(line3d);
    sceneGraph.addGeometry(line3d2);
    sceneGraph.addGeometry(line3d3);
    sceneGraph.addGeometry(line3d4);
    sceneGraph.addGeometry(line3d5);
    sceneGraph.addGeometry(line3d6);
    sceneGraph.addGeometry(line3d7);
    sceneGraph.addGeometry(line3d8);
    sceneGraph.addGeometry(line3d9);
    sceneGraph.addGeometry(line3d10);
    sceneGraph.addGeometry(line3d11);
    sceneGraph.addGeometry(line3d12);
  }

  public static void main(String[] args) {
    LineDrawingExample example = new LineDrawingExample();
  }

  private class MyListener extends MouseAdapter {

    private int oldX = Integer.MIN_VALUE, oldY = Integer.MIN_VALUE;

    @Override
    public void mouseMoved(MouseEvent e) {
      if (oldX == Integer.MIN_VALUE || oldY == Integer.MIN_VALUE) {
        oldX = e.getPoint().x;
        oldY = e.getPoint().y;
      }

      int diffX = e.getPoint().x - oldX;
      int diffY = e.getPoint().y - oldY;
      double transX = 0;
      double transY = 0;

      if (diffX > 0) {
        transX = -0.1;
      } else if (diffX < 0) {
        transX = 0.1;
      }

      if (diffY > 0) {
        transY = -0.1;
      } else if (diffX < 0) {
        transY = 0.1;
      }

      translation.changeX(transX);
      translation.changeY(transY);
    }
  }
  
  private class MyKeyListener extends KeyAdapter{

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        translation.changeX(0.1);
      } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
        translation.changeX(-0.1);
      }else if(e.getKeyCode() == KeyEvent.VK_UP){
        translation.changeY(0.1);
      }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
        translation.changeY(-0.1);
      }else if(e.getKeyCode() == KeyEvent.VK_A){
        translation.changeZ(-0.1);
      }else if(e.getKeyCode() == KeyEvent.VK_Y){
        translation.changeZ(0.1);
      }
      
    }
    
  }
}
