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
 * Wird von jedem Renderer erweitert und stellt die Funktionen putPixel und show zur Verfügung.
 */
public abstract class Renderer {

    /**
     * Die Breite der Ausgabe des Renderers
     */
    private int width;
    /**
     * Die Höhe der Ausgabe des Renderers
     */
    private int height;

    /**
     * Erzeugt einen neuen Renderer und initialisiert die gemeinsamen Felder aller Renderer. 
     * 
     * @param width die Breite der Ausgabe des Renderers
     * @param height die Höhe der Ausgabe des Renderers
     */
    protected Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Gibt die Breite der Ausgabe zurück.
     * @return die Breite der Ausgabe
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt die Höhe der Ausgabe zurück.
     * @return die Höhe der Ausgabe
     */
    public int getHeight() {
        return height;
    }

    /**
     * Diese Methode stellt einen Pixel über den Renderer auf der Ausgabe dar. 
     * Die genaue Art und Weise wie der Pixel dargestellt wird, hängt von der jeweiligen Implementierung ab.
     * @param pixel der Pixel, der dargestellt werden soll
     */
    public abstract void putPixel(Pixel pixel);

    /**
     * Diese Methode stellt einen Pixel in einer bestimmten Farbe über den Renderer auf der Ausgabe dar. 
     * Die genaue Art und Weise wie der Pixel dargestellt wird, hängt von der jeweiligen Implementierung ab.
     * 
     * @param pixel der Pixel, der dargestellt werden soll
     * @param color die Farbe des Pixels
     */
    public abstract void putPixel(Pixel pixel, Color color);

    /**
     * Weist den Renderer an, den Buffer auszugeben.
     */
    public abstract void show();
}
