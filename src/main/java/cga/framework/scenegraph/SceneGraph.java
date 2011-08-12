package cga.framework.scenegraph;

import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.shape.Shape;

/**
 * Szenengraph des Frameworks.
 */
public class SceneGraph {

  private Node root = new Node("root");

  public void addNode(Node node) {
    root.addChild(node);
  }

  public void addNode(Node node, String label) {
    root.findNode(label).addChild(node);
  }

  public void removeNode(Node node) {
    root.removeNode(node.label);
  }

  public Node findNode(String label) {
    return root.findNode(label);
  }

  public void addGeometry(String label, Shape renderable) {
    root.addShape(label, renderable);
  }

  public void addGeometry(Shape renderable) {
    addGeometry("root", renderable);
  }

  public void accept(Visitor visitor) {
    root.accept(visitor);
  }

  public void addTransformation(Transformation transformation) {
    root.setTransformation(transformation);
  }
}
