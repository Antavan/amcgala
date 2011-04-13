package cga.scenegraph.graph.visitor;

import cga.scenegraph.graph.Node;
import cga.scenegraph.shape.Renderable;

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
