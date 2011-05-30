package cga.scenegraph.graph;

import cga.scenegraph.graph.visitor.Visitor;
import cga.scenegraph.shape.Renderable;

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

    public void addGeometry(String label, Renderable renderable) {
        root.addShape(label, renderable);
    }

    public void addGeometry(Renderable renderable) {
        addGeometry("root", renderable);
    }

    public void accept(Visitor visitor) {
        root.accept(visitor);
    }

    public void addTransformation(Transformation transformation) {
        root.setTransformation(transformation);
    }
}
