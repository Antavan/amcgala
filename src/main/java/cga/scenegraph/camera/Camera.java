package cga.scenegraph.camera;

import cga.scenegraph.Renderer.Pixel;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Camera {
    public abstract Pixel project();
}
