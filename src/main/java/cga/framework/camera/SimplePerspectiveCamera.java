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
import cga.framework.renderer.Pixel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Eine naive Implementierung einer perspektivischen Kamera.
 *
 * @author Robert Giacinto
 */
public final class SimplePerspectiveCamera extends AbstractCamera {

    private double d;

    /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten
     * Blickpunkt.
     *
     * @param vup Das Oben der Kamera
     * @param position Die Position der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     * @param d der Abstand der Kamera zur Projektionsebene. Umso kleiner der Wert desto größer die perspektivische Wirkung
     */
    public SimplePerspectiveCamera(Vector3d vup, Vector3d position, Vector3d direction, double d) {
        this.vup = vup;
        this.position = position;
        this.direction = direction;
        this.d = d;

        update();
    }

    @Override
    public Matrix getProjection() {
        return projection;
    }

    @Override
    public void update() {
        this.n = direction.sub(position).times(-1);
        this.u = vup.cross(n).normalize();
        this.v = n.cross(u).normalize();

        double[][] vdValues = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 1.0 / d, 1}
        };

        Matrix vd = Matrix.constructWithCopy(vdValues);

        Vector3d d = new Vector3d(position.dot(u), position.dot(v), position.dot(n));

        double[][] viewValues = {
            {u.x, u.y, u.z, d.x},
            {v.x, v.y, v.z, d.y},
            {n.x, n.y, n.z, d.z},
            {0, 0, 0, 1}
        };
        Matrix kt = Matrix.constructWithCopy(viewValues);
        projection = vd.times(kt);
    }

    /**
     * Gibt den Abstand der Kamera zur Projektionsebene zurück.
     *
     * @return der aktuelle Abstand
     */
    public double getD() {
        return d;
    }

    /**
     * Ändert den Abstand der Kamera zur Projektionsebene.
     *
     * @param d der neue Abstand
     */
    public void setD(double d) {
        this.d = d;
        update();
    }

    @Override
    public CVPoint getClippingSpaceCoordinates(Vector3d vector3d) {
        Matrix point = projection.times(vector3d.toMatrix());
        CVPoint cvPoint = new CVPoint(point.get(0, 0) / point.get(3, 0), point.get(1, 0) / point.get(3, 0));
        return cvPoint;
    }

    @Override
    public Pixel getImageSpaceCoordinates(Vector3d vector3d) {
        Matrix point = projection.times(vector3d.toMatrix());
        Pixel pixel = new Pixel(point.get(0, 0) / point.get(3, 0), point.get(1, 0) / point.get(3, 0));
        return pixel;
    }
    private static final Logger log = LoggerFactory.getLogger(OrthographicCamera.class);
}
