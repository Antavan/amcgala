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
package cga.framework.scenegraph.visitor;

import cga.framework.camera.AbstractCamera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.scenegraph.Node;
import cga.framework.shape.Shape;

/**
 * Der RenderVisitor traversiert einmal pro Frame über den Szenengraph und
 * zeichnet jedes Shape, das gefunden wird, auf den Canvas des Fensters.
 *
 * @author Robert Giacinto
 */
public class RenderVisitor implements Visitor {

    private Renderer renderer;
    private AbstractCamera camera;

    /**
     * Setzt den Renderer, der von diesem <code>RenderVisitor</code> verwendet werden soll.
     * @param renderer der neue Renderer
     */
    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Setzt die Kamera, die vom Renderer verwendet werden soll.
     * @param camera die neue Kamera
     */
    public void setCamera(AbstractCamera camera) {
        this.camera = camera;
    }

    @Override
    public void visit(Node node) {
        synchronized (node.getGeometry()) {
            Matrix transform = node.getTransformMatrix();
            for (Shape shape : node.getGeometry()) {
                shape.setRendering(true);
                shape.render(transform, camera, renderer);
                shape.setRendering(false);
            }
        }
    }
}
