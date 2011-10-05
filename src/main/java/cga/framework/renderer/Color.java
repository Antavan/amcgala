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

    public Color(java.awt.Color color) {
        this.color = color;
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
    }

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        color = new java.awt.Color(r, g, b);
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
        color = new java.awt.Color(r, g, b);
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
        color = new java.awt.Color(r, g, b);
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
        color = new java.awt.Color(r, g, b);
    }
}
