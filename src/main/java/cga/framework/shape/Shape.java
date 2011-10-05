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
package cga.framework.shape;

import cga.framework.animation.Animation;
import cga.framework.animation.Updatable;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Color;
import cga.framework.renderer.Renderer;
import java.util.logging.Logger;

/**
 * Diese Klasse stellt die Oberklasse aller darstellbaren Objekte dar.
 * @author Robert Giacinto
 */
public abstract class Shape implements Updatable {

    private static final Logger logger = Logger.getLogger(Shape.class.getName());
    private Animation animation;
    public Color color = Color.BLACK;

    /**
     * Diese Methode gibt das Shapeobjekt aus. Es wird von allen Unterklassen implementiert.
     * @param transformation die Transformationsmatrix, die aus den Transformationsgruppen resultiert
     * @param camera die Kamera der Szene
     * @param renderer der Renderer
     */
    public abstract void render(Matrix transformation, Camera camera, Renderer renderer);

    public void setAnimation(Animation animation) {
        this.animation = animation;
        this.animation.setShape(this);
    }

    public Animation getAnimation() {
        return animation;
    }

    @Override
    public void update() { }
}
