package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Vector3d;

/**
 * Implementierung einer orthographischen Projektion.
 * Es gehen jegliche Tiefeninformationen verloren.
 * Die Kamera ist besonders dafür geeignet, 2D-Projektionen oder 2D GUI-Elemente zu 
 * realisieren, die über einer perspektivischen Szene zu sehen sind.
 * 
 * @author Robert Giacinto
 */
public class OrthographicalCamera implements Camera {

    /**
     * "oben" Vektor
     */
    private Vector3d vup;
    /**
     * Position der Kamera
     */
    private Vector3d position;
    /**
     * Punkt, zu dem die Kamera blickt
     */
    private Vector3d direction;
    private Vector3d u;
    private Vector3d v;
    private Vector3d n;
    private Vector3d p;
    private Matrix projection;

    /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten Blickpunkt.
     * 
     * @param vup Das Oben der Kamera
     * @param position Die Position der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     */
    public OrthographicalCamera(Vector3d vup, Vector3d position, Vector3d direction) {
        this.vup = vup;
        this.position = position;
        this.direction = direction;


        this.p = direction.sub(position);
        this.n = p.times(-1);
        this.u = this.vup.cross(n).normalize();
        this.v = n.cross(u);

        double[][] vdValues = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 1}
        };

        Matrix vd = Matrix.constructWithCopy(vdValues);

        double[][] kValues = {
            {u.x, u.y, u.z, 0},
            {v.x, v.y, v.z, 0},
            {n.x, n.y, n.z, 0},
            {0, 0, 0, 1}
        };
        Matrix kt = Matrix.constructWithCopy(kValues);
        projection = vd.times(kt);
    }

    @Override
    public Matrix getProjection() {
        return projection;
    }

    @Override
    public CVPoint project(Vector3d vector3d) {
        Matrix point = projection.times(vector3d.toMatrix());
        CVPoint cvPoint = new CVPoint(point.get(0, 0) / point.get(3, 0), point.get(1, 0) / point.get(3, 0));
        return cvPoint;
    }
}
