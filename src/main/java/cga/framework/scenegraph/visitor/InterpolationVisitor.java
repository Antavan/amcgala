package cga.framework.scenegraph.visitor;

import cga.framework.scenegraph.Node;

/**
 * Ein AlphaVisitor besucht in der Updatephase des Scenegraphs jeden Knoten und
 * aktualisiert die dort verwendeten Alphaobjekte.
 * <p/>
 * @author Robert Giacinto
 */
public class InterpolationVisitor implements Visitor {

  @Override
  public void visit(Node node) {
    if (node.getTransformation() != null) {
      node.getTransformation().update();
    }
  }
}
