package example;

import cga.scenegraph.graph.Translation;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.shape.Line3d;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LineDrawingExample extends Framework2d {

  private Translation translation;

  public LineDrawingExample() {
    //renderer.addMouseListener(new MyListener());
    renderer.addKeyListener(new MyKeyListener());

    initGraph();

    translation = new Translation(0, 0, 0);
    sceneGraph.addTransformation(translation);
  }

  @Override
  public void initGraph() {
    // TODO ein Box3d Objekt wäre schon praktischer
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
    addGeometry(line3d);
    addGeometry(line3d2);
    addGeometry(line3d3);
    addGeometry(line3d4);
    addGeometry(line3d5);
    addGeometry(line3d6);
    addGeometry(line3d7);
    addGeometry(line3d8);
    addGeometry(line3d9);
    addGeometry(line3d10);
    addGeometry(line3d11);
    addGeometry(line3d12);
  }

  public static void main(String[] args) {
    LineDrawingExample example = new LineDrawingExample();
    example.start();
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

  private class MyKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        translation.changeX(0.1);
      } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        translation.changeX(-0.1);
      } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        translation.changeY(0.1);
      } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        translation.changeY(-0.1);
      } else if (e.getKeyCode() == KeyEvent.VK_A) {
        translation.changeZ(-0.1);
      } else if (e.getKeyCode() == KeyEvent.VK_Y) {
        translation.changeZ(0.1);
      }

    }
  }
}
