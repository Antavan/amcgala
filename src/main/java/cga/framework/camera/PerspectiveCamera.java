package cga.framework.camera;

import cga.framework.math.Matrix;
import cga.framework.math.Plane;
import cga.framework.math.Quaternion;
import cga.framework.math.Vector3d;
import cga.framework.renderer.Pixel;

/**
 * Diese Klasse implementiert eine Kamera mit perspektivischer Projektion. Aus
 * den Vektoren für Position, Blickrichtung und "oben" wird die Projektionsmatrix
 * bestimmt.
 *
 * @author Robert Giacinto
 */
public final class PerspectiveCamera implements Camera {

    private static final int PLANE_LEFT = 0;
    private static final int PLANE_RIGHT = 1;
    private static final int PLANE_BOTTOM = 2;
    private static final int PLANE_TOP = 3;
    private static final int PLANE_FAR = 4;
    private static final int PLANE_NEAR = 5;
    private static final int FRUSTUM_PLANES = 6;
    private static final int MAX_WORLD_PLANES = 6;
    /**
     * "oben" Vektor
     */
    private Vector3d vup;
    /**
     * Position der Kamera
     */
    private Vector3d location;
    /**
     * Punkt, zu dem die Kamera blickt
     */
    private Vector3d direction;
    /**
     * u-Achse des Kamerakoordinatensystems (lokale x-Achse)
     */
    private Vector3d u;
    /**
     * v-Achse des Kamerakoordinatensystems (lokale y-Achse)
     */
    private Vector3d v;
    /**
     * n-Achse des Kamerakoordinatensystems (lokale z-Achse)
     */
    private Vector3d n;
    /**
     * Abstand zur near plane
     */
    private double d;
    /**
     * Bildverhältnis
     */
    private double aspectRatio;
    private double fieldOfView;
    private double viewportLeft;
    private double viewportRight;
    private double viewportTop;
    private double viewportBottom;
    /**
     * Abstand der Kamera zur linken Frustum-Ebene
     */
    private double frustumLeft;
    /**
     * Abstand der Kamera zur rechten Frustum-Ebene
     */
    private double frustumRight;
    /**
     * Abstand der Kamera zur nahen Frustum-Ebene
     */
    private double frustumNear;
    /**
     * Abstand der Kamera zur entfernten Frustum-Ebene
     */
    private double frustumFar;
    /**
     * Abstand der Kamera zur oberen Frustum-Ebene
     */
    private double frustumTop;
    /**
     * Abstand der Kamera zur unteren Frustum-Ebene
     */
    private double frustumBottom;
    private int width;
    private int height;
    private Matrix projection;
    private Matrix view;
    private Plane[] cullingPlanes;
    private Quaternion rotation;

    public PerspectiveCamera() {
        cullingPlanes = new Plane[FRUSTUM_PLANES];
        for (int i = 0; i < cullingPlanes.length; i++) {
            cullingPlanes[i] = new Plane();
        }
    }

    public PerspectiveCamera(int width, int height) {
        this();
        this.width = width;
        this.height = height;
        location = new Vector3d(0, 0, 0);

        frustumNear = 1.0;
        frustumFar = 2.0;
        frustumLeft = -0.5;
        frustumRight = 0.5;
        frustumTop = 0.5;
        frustumBottom = -0.5;
        
        viewportLeft = 0.0;
        viewportRight = 1.0;
        viewportTop = 1.0;
        viewportBottom = 0.0;
    }

    /**
     * Erzeugt eine neue Kamera an einer Position mit einem bestimmten Blickpunkt.
     * 
     * @param vup Das Oben der Kamera
     * @param location Die Position der Kamera
     * @param direction Der Punkt, zu dem die Kamera blickt
     * @param fov Der Öffnungswinkel der Kamera
     * @param width die Breite der Bildschirmausgabe
     * @param height die Höhe der Bildschirmausgabe
     */
    public PerspectiveCamera(Vector3d location, Vector3d direction, Vector3d vup, double fov, int width, int height) {
        this();
        this.location = location;
        this.direction = direction;
        this.vup = vup;
        this.fieldOfView = fov;
        this.aspectRatio = width / height;
        this.width = width;
        this.height = height;
        update();
    }

    @Override
    public Matrix getProjection() {
        return projection;
    }

    @Override
    public CVPoint getClippingSpaceCoordinates(Vector3d vector3d) {
        Matrix point = view.times(vector3d.toMatrix());
        CVPoint cvPoint = new CVPoint(point.get(0, 0) / point.get(3, 0), point.get(1, 0) / point.get(3, 0));
        return cvPoint;
    }

    @Override
    public void update() {
        /*
         * Erste Transformation: Weltkoordinaten -> Kamerakoordinaten.
         */
        n = direction.sub(location).times(-1).normalize();
        u = vup.cross(n).normalize();
        v = n.cross(u);

        Vector3d d = new Vector3d(location.dot(u), location.dot(v), location.dot(n)).times(-1);

        double[][] viewValues = {
            {u.x, u.y, u.z, d.x},
            {v.x, v.y, v.z, d.y},
            {n.x, n.y, n.z, d.z},
            {0, 0, 0, 1}
        };
        view = Matrix.constructWithCopy(viewValues);

        /*
         * Zweite Transformation: Kamerakoordinaten -> Bildkoordinaten
         */

        projection = Matrix.identity(4, 4);
    }

    @Override
    public Pixel getImageSpaceCoordinates(Vector3d vector3d) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
