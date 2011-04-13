package cga.scenegraph.graph;

import cga.scenegraph.graph.visitor.Visitor;
import cga.scenegraph.shape.Renderable;

import java.util.ArrayList;
import java.util.Collection;


public class Node {
  protected String label = "none";
  private Node parent;
  private Collection<Node> children = new ArrayList<Node>();
  private Collection<Renderable> geometry = new ArrayList<Renderable>();

  public Node(String label) {
    this.label = label;
  }

  public Node(String label, Node parent) {
    this.label = label;
    this.parent = parent;
  }

  public Node addChild(Node childNode) {
    children.add(childNode);
    return this;
  }

  public boolean removeNode(String label) {
    for (Node n : children) {
      if (n.label.equalsIgnoreCase(label)) {
        children.remove(n);
        return true;
      }
    }

    for (Node n : children) {
      return n.removeNode(label);
    }

    return false;
  }

  public boolean addShape(String label, Renderable newShape) {
    if (this.label.equalsIgnoreCase(label)) {
      geometry.add(newShape);
      return true;
    } else {
      for (Node n : children) {
        n.addShape(label, newShape);
      }
    }
    return false;
  }

  public Node findNode(String label) {
    if (this.label.equalsIgnoreCase(label)) {
      return this;
    } else {
      for (Node n : children) {
        return n.findNode(label);
      }
    }
    return null;
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }

  public String getLabel() {
    return label;
  }

  public Node getParent() {
    return parent;
  }

  public Collection<Node> getChildren() {
    return children;
  }

  public Collection<Renderable> getGeometry() {
    return geometry;
  }

  @Override
  public String toString() {
    return "Node{" +
      "label='" + label + '\'' +
      '}';
  }
}
