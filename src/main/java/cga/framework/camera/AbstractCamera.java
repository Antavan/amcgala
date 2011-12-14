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
package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;

/**
 * Eine abstrakte Implementierung des {@code Camera} Interface.
 *
 * @author Robert Giacinto
 */
public abstract class AbstractCamera implements Camera {

    /**
     * "oben" Vektor
     */
    protected Vector3d vup;
    /**
     * Position der Kamera
     */
    protected Vector3d position;
    /**
     * Punkt, zu dem die Kamera blickt
     */
    protected Vector3d direction;
    /**
     * lokale x-Achse der Kamera
     */
    protected Vector3d u;
    /**
     * lokale y-Achse der Kamera
     */
    protected Vector3d v;
    /**
     * lokale z-Achse der Kamera
     */
    protected Vector3d n;
    /**
     * Die Projektionsmatrix
     */
    protected Matrix projection;

    /**
     * Gibt die Projektionsmatrix der Kamera zurück.
     *
     * @return die aktuelle Projektionsmatrix
     */
    protected abstract Matrix getProjection();

    /**
     * Gibt die Blickrichtung der Kamera zurück.
     *
     * @return die aktuelle Blickrichtung der Kamera
     */
    @Override
    public Vector3d getDirection() {
        return direction;
    }

    /**
     * Ändert die Blickrichtung der Kamera.
     *
     * @param direction die neue Blickrichtung
     */
    @Override
    public void setDirection(Vector3d direction) {
        this.direction = direction;
        update();
    }

    /**
     * Gibt die Position der Kamera zurück.
     *
     * @return die Position der Kamera
     */
    @Override
    public Vector3d getPosition() {
        return position;
    }

    /**
     * Ändert die Position der Kamera.
     *
     * @param position die neue Position
     */
    @Override
    public void setPosition(Vector3d position) {
        this.position = position;
        update();
    }

    /**
     * Gibt den Oben-Vektor der Kamera zurück.
     *
     * @return der Oben-Vektor
     */
    @Override
    public Vector3d getVup() {
        return vup;
    }

    /**
     * Ändert den Oben-Vektor der Kamera.
     *
     * @param vup der neue Oben-Vektor
     */
    @Override
    public void setVup(Vector3d vup) {
        this.vup = vup;
        update();
    }
}
