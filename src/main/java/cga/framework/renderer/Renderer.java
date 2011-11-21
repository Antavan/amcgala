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

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 * Wird von jedem Renderer erweitert und stellt die Funktionen putPixel und show
 * zur Verfügung.
 */
public class Renderer {

    /**
     * Die Breite der Ausgabe des Renderers
     */
    private int width;
    /**
     * Die Höhe der Ausgabe des Renderers
     */
    private int height;
    private BufferStrategy bs;
    private int offsetX;
    private int offsetY;
    private JFrame frame;

    /**
     * Erzeugt einen neuen Renderer und initialisiert die gemeinsamen Felder
     * aller Renderer.
     *
     * @param width die Breite der Ausgabe des Renderers
     * @param height die Höhe der Ausgabe des Renderers
     */
    public Renderer(int width, int height, JFrame frame) {
        this.width = width;
        this.height = height;
        this.width = width;
        this.height = height;
        this.offsetX = width >> 1;
        this.offsetY = height >> 1;
        this.frame = frame;

        frame.createBufferStrategy(2);
        bs = frame.getBufferStrategy();
    }

    /**
     * Gibt die Breite der Ausgabe zurück.
     *
     * @return die Breite der Ausgabe
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gibt die Höhe der Ausgabe zurück.
     *
     * @return die Höhe der Ausgabe
     */
    public int getHeight() {
        return height;
    }

    /**
     * Diese Methode stellt einen Pixel über den Renderer auf der Ausgabe dar.
     * Die genaue Art und Weise wie der Pixel dargestellt wird, hängt von der
     * jeweiligen Implementierung ab.
     *
     * @param pixel der Pixel, der dargestellt werden soll
     */
    public void putPixel(Pixel pixel) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(offsetX + pixel.x, -pixel.y + offsetY, 1, 1);
        g.dispose();
    }

    /**
     * Diese Methode stellt einen Pixel in einer bestimmten Farbe über den
     * Renderer auf der Ausgabe dar. Die genaue Art und Weise wie der Pixel
     * dargestellt wird, hängt von der jeweiligen Implementierung ab.
     *
     * @param pixel der Pixel, der dargestellt werden soll
     * @param color die Farbe des Pixels
     */
    public void putPixel(Pixel pixel, cga.framework.renderer.Color color) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(color.color);
        g.fillRect(offsetX + pixel.x, -pixel.y + offsetY, 1, 1);
        g.dispose();
    }

    /**
     * Weist den Renderer an, den Buffer auszugeben.
     */
    public void show() {
        bs.show();
        bs.getDrawGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    /**
     * Fügt dem JFrame der Ausgabe einen MouseListener hinzu, mit dem
     * Interaktionen implementiert werden können.
     *
     * @param mouseAdapter der MouseListener
     */
    public void addMouseListener(MouseAdapter mouseAdapter) {
        frame.addMouseListener(mouseAdapter);
        frame.addMouseMotionListener(mouseAdapter);
        frame.addMouseWheelListener(mouseAdapter);
    }

    /**
     * Fügt dem JFrame einen KeyListener hinzu, mit dem Interaktionen über das
     * Keyboard implementiert werden können .
     *
     * @param keyAdapter der KeyListener
     *
     */
    public void addKeyListener(KeyAdapter keyAdapter) {
        frame.addKeyListener(keyAdapter);
    }
}
