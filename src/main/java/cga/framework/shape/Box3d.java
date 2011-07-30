package cga.framework.shape;

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Renderer;

/**
 * Eine Box im 3d Raum.
 * 
 * @author Robert Giacinto
 */
public class Box3d extends Renderable {

  private Line3d[] lines;

  public Box3d(Vector3d position, double width, double height, double depth) {
    lines = new Line3d[12];
    lines[0] = new Line3d(new Vector3d(position.x, position.y, position.z), new Vector3d(position.x + width, position.y, position.z));
    lines[1] = new Line3d(new Vector3d(position.x, position.y, position.z), new Vector3d(position.x, position.y + height, position.z));
    lines[2] = new Line3d(new Vector3d(position.x, position.y, position.z), new Vector3d(position.x, position.y, position.z - depth));
    lines[3] = new Line3d(new Vector3d(position.x, position.y + height, position.z), new Vector3d(position.x + width, position.y + height, position.z));
    lines[4] = new Line3d(new Vector3d(position.x + width, position.y, position.z), new Vector3d(position.x + width, position.y + height, position.z));
    lines[5] = new Line3d(new Vector3d(position.x, position.y + height, position.z), new Vector3d(position.x, position.y + height, position.z - depth));
    lines[6] = new Line3d(new Vector3d(position.x + width, position.y + height, position.z), new Vector3d(position.x + width, position.y + height, position.z - depth));
    lines[7] = new Line3d(new Vector3d(position.x + width, position.y, position.z), new Vector3d(position.x + width, position.y, position.z - depth));
    lines[8] = new Line3d(new Vector3d(position.x, position.y, position.z - depth), new Vector3d(position.x + width, position.y, position.z - depth));
    lines[9] = new Line3d(new Vector3d(position.x, position.y, position.z - depth), new Vector3d(position.x, position.y + height, position.z - depth));
    lines[10] = new Line3d(new Vector3d(position.x, position.y + height, position.z - depth), new Vector3d(position.x + width, position.y + height, position.z - depth));
    lines[11] = new Line3d(new Vector3d(position.x + width, position.y, position.z - depth), new Vector3d(position.x + width, position.y + height, position.z - depth));;
  }

  @Override
  public void render(Matrix transformation, Camera camera, Renderer renderer) {
    for (Line3d line : lines) {
      if (line != null) {
        line.render(transformation, camera, renderer);
      }
    }
  }
}
