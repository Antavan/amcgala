package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Pixel;

/**
 *
 * @author Robert Giacinto
 */
public interface Camera {

    /**
     * Aktualisiert die Projektionsmatrix des Kameraobjekts. Diese Methode
     * sollte immer dann aufgerufen werden, wenn etwas an der Kamera verändert
     * wurde.
     */
    void update();

    /**
     * Gibt die Projektionsmatrix der Kamera zurück.
     * 
     * @return die aktuelle Projektionsmatrix
     */
    Matrix getProjection();

    /**
     * Gibt die Koordinaten im kanonischen Sichtvolumen zurück.
     * 
     * @param vector3d der zu projezierende Vektor
     * @return der transformierte Vektor in den Clipping Raum
     */
    CVPoint getClippingSpaceCoordinates(Vector3d vector3d);

    /**
     * Gibt die Koordinaten des Vektors im Bildraum zurück.
     * @param vector3d der zu projezierende Vektor
     * @return die Koordinaten des Vektors im Bildraum
     */
    Pixel getImageSpaceCoordinates(Vector3d vector3d);
}
