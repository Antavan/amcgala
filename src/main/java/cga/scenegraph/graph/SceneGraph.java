package cga.scenegraph.graph;

import cga.scenegraph.graph.visitor.Visitor;
import cga.scenegraph.shape.Renderable;
import com.sun.istack.internal.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 3:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class SceneGraph {
    private Node root = new Node("root");

    @NotNull
    public void addNode(Node node) {
    }

    @NotNull
    public void removeNode(Node node) {
    }

    @NotNull
    public Node findNode(String label) {
        return null;
    }

    @NotNull
    public void addGeometry(String label, Renderable renderable) {

    }

    @NotNull
    public void accept(Visitor visitor) {
        root.accept(visitor);
    }
}
