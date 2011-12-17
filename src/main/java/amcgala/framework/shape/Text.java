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
package amcgala.framework.shape;

import amcgala.framework.camera.AbstractCamera;
import amcgala.framework.camera.Camera;
import amcgala.framework.math.Matrix;
import amcgala.framework.renderer.Renderer;

/**
 * Eine Klasse, die Text darstellen kann. Die grundlegenden Zeichen werden
 * unterstützt und als Vektorbuchstaben ausgegeben.
 *
 * @author Robert Giacinto
 */
public class Text extends Shape {

    private static final double SPACING = 15;
    private String text;
    private double width;
    private double x;
    private double y;
    private Letter[] letters;

    /**
     * Erzeugt ein Text-Shape, das den Text an der Position (x,y) mit der
     * Gesamtbreite width darstellt.
     *
     * @param text der Text
     * @param x die x-Koordinate der Position
     * @param y die y-Kootdinate der Position
     * @param width die Breite des Texts
     */
    public Text(String text, double x, double y, double width) {
        this.width = width / text.length();
        this.text = text;
        this.letters = new Letter[text.length()];
        for (int i = 0; i < letters.length; i++) {
            char c = text.toUpperCase().charAt(i);
            switch (c) {
                case 'A':
                    letters[i] = Letter.getA(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'B':
                    letters[i] = Letter.getB(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'C':
                    letters[i] = Letter.getC(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'D':
                    letters[i] = Letter.getD(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'E':
                    letters[i] = Letter.getE(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'F':
                    letters[i] = Letter.getF(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'G':
                    letters[i] = Letter.getG(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'H':
                    letters[i] = Letter.getH(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'I':
                    letters[i] = Letter.getI(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'J':
                    letters[i] = Letter.getJ(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'K':
                    letters[i] = Letter.getK(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'L':
                    letters[i] = Letter.getL(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'M':
                    letters[i] = Letter.getM(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'N':
                    letters[i] = Letter.getN(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'O':
                    letters[i] = Letter.getO(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'P':
                    letters[i] = Letter.getP(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'Q':
                    letters[i] = Letter.getQ(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'R':
                    letters[i] = Letter.getR(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'S':
                    letters[i] = Letter.getS(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'T':
                    letters[i] = Letter.getT(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'U':
                    letters[i] = Letter.getU(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'V':
                    letters[i] = Letter.getV(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'W':
                    letters[i] = Letter.getW(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'X':
                    letters[i] = Letter.getX(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'Y':
                    letters[i] = Letter.getY(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                case 'Z':
                    letters[i] = Letter.getZ(x + (i * (this.width + SPACING)), y, this.width);
                    break;
                default:
                    letters[i] = Letter.getBlank(x + (i * (this.width + SPACING)), y, this.width);
                    break;

            }


        }
    }

    @Override
    public void render(Matrix transformation, Camera camera, Renderer renderer) {
        for (Shape s : letters) {
            s.render(transformation, camera, renderer);
        }
    }
}
