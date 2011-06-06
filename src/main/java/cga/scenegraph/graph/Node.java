package cga.scenegraph.graph;

import cga.scenegraph.graph.visitor.Visitor;
import cga.scenegraph.math.Matrix;
import cga.scenegraph.shape.Renderable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Eine Node ist Teil des Szenengraphens und kann beliebig viele Kindsknoten und Geometrieobjekte zugewiesen bekommen.
 */
public class Node {

    protected String label = "none";
    private Node parent;
    private Collection<Node> children = new ArrayList<Node>();
    private Collection<Renderable> geometry = new ArrayList<Renderable>();
    private Transformation transformation;

    /**
     * Erstellt eine neue Node mit einem Label, über das die Node innerhalb des Graphens gefunden werden kann.
     *
     * @param label das Label des Knotens
     */
    public Node(String label) {
        this.label = label;
        transformation = new Translation(0, 0, 0);
    }

    /**
     * Erzeugt eine neue Node mit einem Label und einem zugewiesenen Elternknoten.
     *
     * @param label  das Label des Knotens
     * @param parent der Elternknoten
     */
    public Node(String label, Node parent) {
        this(label);
        this.parent = parent;
    }

    /**
     * Fügt dem Knoten einen neuen Kindsknoten hinzu.
     *
     * @param childNode der neue Knoten
     * @return gibt Referenz auf sich selbst zurück um verschachtelte Aufrufe zu ermöglichen
     */
    public Node addChild(Node childNode) {
        childNode.parent = this;
        children.add(childNode);
        return this;
    }

    /**
     * Entfernt einen Kindsknoten mit einem gegebenen Label.
     *
     * @param label das Label des zu löschenden Knoten
     * @return true, wenn Knoten gefunden und entfernt wurde
     */
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

    /**
     * Fügt einem Knoten mit einem bestimmten Label ein neues Geometrieobjekt hinzu.
     *
     * @param label    das Label des Knoten, dem das neue Objekt hinzugefügt werden soll
     * @param newShape das neue Geometrieobjekt
     * @return true, wenn es hinzugefügt werden konnte
     */
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

    /**
     * Gibt einen Knoten mit einem bestimmten Label zurück.
     *
     * @param label Label des Knoten, der gefunden werden soll
     * @return true, wenn Knoten gefunden wurde
     */
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

    /**
     * Übergibt einen neuen Visitor an den Knoten.
     *
     * @param visitor der Visitor, der den Knoten besuchen soll
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Gibt das Label des Knoten zurück.
     *
     * @return das Label des Knoten
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gibt den Parentknoten zurück.
     *
     * @return der Parentknoten
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Gibt die Kindsknoten zurück.
     *
     * @return die Kindsknoten
     */
    public Collection<Node> getChildren() {
        return Collections.unmodifiableCollection(children);
    }

    /**
     * Gibt die Geometrieobjekt zurück, die an dem Knoten hängen.
     *
     * @return die Geometrieobjekte
     */
    public Collection<Renderable> getGeometry() {
        return Collections.unmodifiableCollection(geometry);
    }

    /**
     * Gibt die Transformationsmatrix dieses Knotens zurück.
     * 
     * @return  die Transformationsmatrix
     */
    public Transformation getTransformation() {
        return transformation;
    }

    /**
     * Setzt die aktuelle Transformationsmatrix dieses Knotens.
     * 
     * @param transformation die neue Transformationsmatrix
     */
    public void setTransformation(Transformation transformation) {
        this.transformation = transformation;
    }

    /**
     * Gibt die gesamte Transformationsmatrix zurück.
     * @return 
     */
    public Matrix getTransformMatrix() {
        Matrix b = transformation.getTransformMatrix();

        if (parent != null) {
            Matrix c = parent.getTransformMatrix();
            b = b.times(c);
        }
        
        return b;
    }

    @Override
    public String toString() {
        return "Node{"
                + "label='" + label + '\''
                + '}';
    }
}
