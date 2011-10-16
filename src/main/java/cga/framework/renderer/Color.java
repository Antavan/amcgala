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
 *  Diese Klasse repräsentiert eine Farbe, mit der Pixel eingefärbt werden können.
 * 
 * @author Robert Giacinto
 */
public class Color {

    public static final Color RED = new Color(java.awt.Color.RED);
    public static final Color WHITE = new Color(java.awt.Color.WHITE);
    public static final Color GREEN = new Color(java.awt.Color.GREEN);
    public static final Color BLUE = new Color(java.awt.Color.BLUE);
    public static final Color BLACK = new Color(java.awt.Color.BLACK);
    private int r;
    private int g;
    private int b;
    protected java.awt.Color color;

    /**
     * Erzeugt eine neue Farbe von einer java.awt.Color.
     * @param color die Farbe
     */
    public Color(java.awt.Color color) {
        this.color = color;
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
    }

    /**
     * Erzeugt eine neue Farbe von den Einzelwerten der Farbkanäle.
     * @param r der rote Farbanteil
     * @param g der grüne Farbanteil
     * @param b der blaue Farbanteil
     */
    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        color = new java.awt.Color(r, g, b);
    }

    /**
     * Gibt den blauen Farbanteil der Farbe zurück.
     * @return der blaue Farbanteil
     */
    public int getB() {
        return b;
    }

    /**
     * Ändert den blauen Farbanteil der Farbe.
     * @param b der blaue Farbanteil
     */
    public void setB(int b) {
        this.b = b;
        color = new java.awt.Color(r, g, b);
    }

    /**
     * Gibt den grünen Farbanteil der Farbe zurück.
     * @return der grüne Farbanteil
     */
    public int getG() {
        return g;
    }

    /**
     * Ändert den grünen Farbanteil der Farbe.
     * @param g der grüne Farbanteil
     */
    public void setG(int g) {
        this.g = g;
        color = new java.awt.Color(r, g, b);
    }

    /**
     * Gibt den roten Farbanteil der Farbe zurück.
     * @return der rote Farbanteil
     */
    public int getR() {
        return r;
    }

    /**
     * Ändert den roten Farbanteil der Farbe
     * @param r der rote Farbanteil
     */
    public void setR(int r) {
        this.r = r;
        color = new java.awt.Color(r, g, b);
    }
}
