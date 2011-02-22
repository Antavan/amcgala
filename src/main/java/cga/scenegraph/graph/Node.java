package cga.scenegraph.graph;

import cga.scenegraph.cga.scenegraph.shape.Renderable;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 2:40 AM
 */
public class Node {
    private String label = "none";
    private Node parent;
    private Collection<Node> children = new ArrayList<Node>();
    private Collection<Renderable> geometry = new ArrayList<Renderable>();

    public Node(String label) {
        this.label = label;
    }

    @NotNull
    public Node(String label, Node parent) {
        this.label = label;
        this.parent = parent;
    }

    @NotNull
    public Node addChild(Node childNode) {
        children.add(childNode);
        return this;
    }

    public boolean removeNode(String label) {
        return false;
    }

    public void addShape(Renderable newShape) {
    }
}
