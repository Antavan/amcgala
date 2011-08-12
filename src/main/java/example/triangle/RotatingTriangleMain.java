package example.triangle;

import cga.Framework;
import cga.framework.shape.Point2d;

/**
 * Dies ist das Aufwärmbeispiel für die Einführungsveranstaltung. Es ist ein
 * kleines Beispiel, bei dem ein Dreieck implementiert werden soll, das um eine
 * beliebe Achse im 2d Raum rotiert wird.
 *
 * @author Robert Giacinto
 */
public class RotatingTriangleMain extends Framework {

  public RotatingTriangleMain(int width, int height) {
    super(width, height);

  }

  @Override
  public void initGraph() {
    add(new RotatingTriangle(new Triangle(new Point2d(0, 0), new Point2d(200, 0), new Point2d(100, 200))));
  }
  
  public static void main(String[] args) {
    Framework demo = new RotatingTriangleMain(1600, 800);
    demo.start();
  }
}
