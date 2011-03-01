package cga.scenegraph.renderer;

/**
 * Created by IntelliJ IDEA.
 * User: lichtsprung
 * Date: 2/22/11
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Renderer {
    protected int width;
    protected int height;

    protected Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void putPixel(Pixel pixel);

    public abstract void show();
}
