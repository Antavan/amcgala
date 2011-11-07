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

import cga.framework.camera.Camera;
import cga.framework.math.Matrix;
import cga.framework.renderer.Renderer;

/**
 * Eine Klasse, die Text darstellen kann. Die grundlegenden Zeichen werden
 * unterstützt und als Vektorbuchstaben ausgegeben.
 *
 * @author Robert Giacinto
 */
public class Text extends Shape {

    private String text;
    private double height;
    private Shape[] letters;

    public Text(String text, double height) {
        this.text = text;
        this.height = height;
        this.letters = new Shape[text.length()];
        letters[0] = Letter.getA(height);
        
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (Shape s : letters) {
            s.render(transformation, camera, renderer);
        }
    }
}
