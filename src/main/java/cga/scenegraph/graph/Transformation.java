package cga.scenegraph.graph;

import cga.scenegraph.math.Matrix;

/**
 * Ein Transformationsobjekt kann einem Knoten des Scenegraphs übergeben werden.
 */
public interface Transformation {
  public Matrix getTransformMatrix();
}
