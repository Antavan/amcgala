package cga.framework.scenegraph.visitor;

import cga.framework.scenegraph.Node;
import cga.framework.shape.Renderable;

public class AnimationVisitor implements Visitor {

  @Override
  public void visit(Node node) {
    for (Renderable renderable : node.getGeometry()) {
      if (renderable.getAnimation() != null) {
        renderable.getAnimation().animate();
      }
    }
  }
}
