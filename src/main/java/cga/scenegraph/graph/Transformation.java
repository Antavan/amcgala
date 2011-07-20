package cga.scenegraph.graph;

import cga.scenegraph.animation.Alpha;
import cga.scenegraph.math.Matrix;

/**
 * Ein Transformationsobjekt kann einem Knoten des Scenegraphs übergeben werden.
 */
public interface Transformation {

    /**
     * Gibt die Transformationsmatrix zurück, die die in Transformation repräsentiert, die
     * durch die jeweilige Klasse implementiert wird.
     * 
     * @return die Transformationsmatrix der Transformation
     */
    public Matrix getTransformMatrix();

    /**
     * Setzt ein Alphaobjekt, das für die Animation der Transformation genutzt werden kann.
     * 
     * @param alpha das Alphaobjekt
     */
    public void setAlpha(Alpha alpha);
    
    /**
     * Aktualisiert die Transformation unter Verwendung eines Alphaobjekts.
     */
    public void update();
}
