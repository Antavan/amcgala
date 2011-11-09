/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package cga.framework.scenegraph;

import cga.framework.scenegraph.transform.Transformation;
import cga.framework.scenegraph.transform.Translation;
import cga.framework.scenegraph.visitor.Visitor;
import cga.framework.math.Matrix;
import cga.framework.shape.Shape;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Eine Node ist Teil des Scenegraphs und kann beliebig viele Kindsknoten und
 * Geometrieobjekte zugewiesen bekommen.
 */
public class Node {

    private static final Logger logger = Logger.getLogger(Node.class.getName());
    protected String label = "none";
    /**
     * Der übergeordnete Knoten, an dem dieser Knoten hängt. Null, wenn es sich
     * um den Rootknoten handelt.
     */
    private Node parent;
    /**
     * Die Kindsknoten, die an diesem Knoten hängen.
     */
    private Collection<Node> children;
    /**
     * Die Geometrieobjekte, die an diesem Knoten hängen und von dem Renderer
     * dargestellt werden.
     */
    private Collection<Shape> geometry;
    /**
     * Ein Transformationsobjekt, das sich auf die Geometrie dieses Knotens und
     * der Kindsknoten auswirkt.
     */
    private Transformation transformation;

    /**
     * Erstellt eine neue Node mit einem Label, über das die Node innerhalb des Graphens gefunden werden kann.
     *
     * @param label das Label des Knotens
     */
    public Node(String label) {
        this.label = label;
        transformation = new Translation(0, 0, 0);
        geometry = new ArrayList<Shape>();
        children = new ArrayList<Node>();
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
        this.parent.addChild(this);
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
    public boolean addShape(String label, Shape newShape) {
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
     * Fügt ein neues Geometrieobjekt dieser Node hinzu.
     * @param shape das neue Objekt
     * @return true,  wenn es erfolgreich hinzugefügt wurde
     */
    public boolean addShape(Shape shape) {
        geometry.add(shape);
        return true;
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
//        String names[] = {visitor.getClass().getName(), label};
//        logger.log(Level.INFO, "Visitor {0} visiting node {1}", names);
        visitor.visit(this);
        for (Node n : children) {
            n.accept(visitor);
        }
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
    public Collection<Shape> getGeometry() {
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
     * TODO Die Reihenfolge der Transformation spielt eine Rolle - ist es sinnvoll, das so zu gestalten, dass man nur eine Transformation pro Node zulässt?
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
