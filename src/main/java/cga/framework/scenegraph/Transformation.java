package cga.framework.scenegraph;

import cga.framework.math.Matrix;

/**
 * Ein Transformationsobjekt kann einem Knoten des Scenegraphs übergeben werden.
 */
public interface Transformation{

    /**
     * Gibt die Transformationsmatrix zurück, die die in Transformation repräsentiert, die
     * durch die jeweilige Klasse implementiert wird.
     * 
     * @return die Transformationsmatrix der Transformation
     */
    public Matrix getTransformMatrix();

    
    /**
     * Aktualisiert die Transformation unter Verwendung von Interpolationsobjekten..
     */
    public void update();
}
