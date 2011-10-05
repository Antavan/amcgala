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
package cga.example.morphing;

import cga.framework.animation.Animation;
import cga.framework.animation.interpolation.Interpolation;
import cga.framework.animation.interpolation.LinearInterpolation;
import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;
import cga.framework.shape.Line2d;
import cga.framework.shape.Shape;

/**
 * Eine sich morphende Linie. Es ist auch möglich dieses Prinzip auf beliebige Formen zu erweitern. In dem Fall müssen
 * Startobjekt und Zielobjekt über die gleiche Anzahl von Punkten definiert sein. In dem Fall kann das Startobjekt in das Endobjekt  gemorpht werden.
 * 
 * @author Robert Giacinto
 */
public class MorphingItem extends Shape {

    private Line2d line1;
    private Line2d line2;
    private int stepcount;

    public MorphingItem(Line2d start, Line2d end, int steps) {
        line1 = start;
        line2 = end;
        this.stepcount = steps;

        setAnimation(new Animation<Line2d>() {

            private Interpolation interpolationX1 = new LinearInterpolation(line1.x1, line2.x1, stepcount, true);
            private Interpolation interpolationX2 = new LinearInterpolation(line1.x2, line2.x2, stepcount, true);
            private Interpolation interpolationY1 = new LinearInterpolation(line1.y1, line2.y1, stepcount, true);
            private Interpolation interpolationY2 = new LinearInterpolation(line1.y2, line2.y2, stepcount, true);

            @Override
            public void animate() {
                line1.x1 = interpolationX1.nextValue();
                line1.x2 = interpolationX2.nextValue();
                line1.y1 = interpolationY1.nextValue();
                line1.y2 = interpolationY2.nextValue();
            }
        });
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        line1.render(transformation, camera, renderer);
    }
}
