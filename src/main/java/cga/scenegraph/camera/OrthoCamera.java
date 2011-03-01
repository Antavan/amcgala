package cga.scenegraph.camera;

import cga.scenegraph.math.Matrix;
import cga.scenegraph.math.Vector3d;
import cga.scenegraph.renderer.Pixel;

public class OrthoCamera extends Camera {

    public OrthoCamera() {
        super(new Vector3d(0, 0, 1), new Vector3d(0, 0, 0), new Vector3d(0, 1, 0));
    }

    public OrthoCamera(Vector3d position, Vector3d eye, Vector3d up) {
        super(position, eye, up);
    }

    @Override
    public Pixel project(Vector3d vector3d) {
        Matrix r = getProjection().times(vector3d.toMatrix());
        return new Pixel(r.get(0, 0), r.get(1, 0));
    }

    @Override
    public void initProjection() {
        double[] vals = {
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 1
        };
        setProjection(new Matrix(vals, 4));
    }
}
