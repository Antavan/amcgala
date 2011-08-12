package example.triangle;

import cga.framework.animation.interpolation.EaseInInterpolation;
import cga.framework.scenegraph.Node;
import cga.framework.scenegraph.transform.RotationY;
import cga.framework.scenegraph.transform.RotationZ;

/**
 *
 * @author Robert Giacinto
 */
public class RotatingTriangle extends Node {

    private Node rotationZNode;

    public RotatingTriangle(Triangle triangle) {
        super("Rotating Triangle");
        
        RotationZ rotationZ = new RotationZ();
        rotationZ.setInterpolationPhi(new EaseInInterpolation(0, 6 * Math.PI, 1000, true));
        rotationZNode = new Node("Rotating z-axis");
        rotationZNode.setTransformation(rotationZ);
        rotationZNode.addShape(triangle);
        
        RotationY rotationY = new RotationY();
        rotationY.setInterpolationPhi(new EaseInInterpolation(0, 6*Math.PI, 1000, true));
        setTransformation(rotationY);
        addChild(rotationZNode);
    }
}
