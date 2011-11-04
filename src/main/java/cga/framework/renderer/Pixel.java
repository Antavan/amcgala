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
package cga.framework.renderer;

/**
 * Ein Pixel stellt einen Punkt in der Ausgabe dar. 
 * Es wird während des Renderings verwendet, um ein Shape über einen Renderer 
 * auszugeben.
 * @author Robert Giacinto
 */
public class Pixel {

    public int x;
    public int y;

    /**
     * Erzeugt einen neuen Pixel an der Stelle (x,y)
     * @param x die x-Koordinate des Pixels
     * @param y die y-Koordinate des Pixels
     */
    public Pixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Erzeugt einen neuen Pixel an der Stelle (x,y). 
     * Die doubles werden entsprechend auf die Integerpositionen des Pixels gerundet.
     * @param x die x-Koordinate des Pixels
     * @param y die y-Koordinate des Pixels
     */
    public Pixel(double x, double y) {
        float nx = Math.round(x);
        float ny = Math.round(y);
        this.y = Math.round(ny);
        this.x = Math.round(nx);
    }

    /**
     * Gibt die x-Koordinate des Pixels zurück.
     * @return die x-Koordinate des Pixels
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt die y-Koordinate des Pixels zurück.
     * @return die y-Koordinate des Pixels
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pixel{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
