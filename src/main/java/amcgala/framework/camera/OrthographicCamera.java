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
package amcgala.framework.camera;

import amcgala.framework.math.Matrix;
import amcgala.framework.math.Vector3d;
import amcgala.framework.renderer.Pixel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementierung einer orthographischen Projektion. Es gehen jegliche
 * Tiefeninformationen verloren. Die Kamera ist besonders dafür geeignet,
 * 2D-Projektionen oder 2D GUI-Elemente zu realisieren, die über einer
 * perspektivischen Szene zu sehen sind.
 *
 * @author Robert Giacinto
 */
public final class OrthographicCamera extends AbstractCamera {

    /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten
     * Blickpunkt.
     *
     * @param vup Das Oben der Kamera
     * @param position Die Position der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     */
    public OrthographicCamera(Vector3d vup, Vector3d position, Vector3d direction) {
        this.vup = vup;
        this.position = position;
        this.direction = direction;

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
            {0, 0, 0, 1}
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
