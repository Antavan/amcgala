package cga.scenegraph.shape;

import cga.scenegraph.camera.Camera;
import cga.scenegraph.renderer.Renderer;


public interface Renderable {
    void render(Camera camera, Renderer renderer);
}
