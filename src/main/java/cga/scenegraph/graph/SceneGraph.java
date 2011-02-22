package cga.scenegraph.graph;

import cga.scenegraph.Renderer.Renderer;
import cga.scenegraph.camera.Camera;
import cga.scenegraph.cga.scenegraph.shape.Renderable;
import cga.scenegraph.graph.Node;
import com.sun.istack.internal.Nullable;

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

    @Nullable
    public Node findNode(String label) {
        return null;
    }

    public void addGeometry(String label, Renderable renderable) {

    }

    public void render(Camera camera, Renderer renderer) {
    }
}
