package cga.scenegraph.graph;

import cga.scenegraph.graph.visitor.Visitor;
import cga.scenegraph.shape.Renderable;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 3:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SceneGraph {
    private Node root = new Node("root");

    public void addNode(Node node) {
    }

    public void removeNode(Node node) {
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
}
