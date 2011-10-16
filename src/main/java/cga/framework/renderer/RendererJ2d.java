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
 * Eine Java2D Implementierung eines Renderers. Es wird das Bild in einem JFrame ausgegeben.
 * @author Robert Giacinto
 */
public class RendererJ2d extends Renderer {

    private JFrame frame;
    private BufferStrategy bs;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    /**
     * Erstellt einen neuen RendererJ2D.
     * @param width Breite der Ausgabe
     * @param height Höhe der Ausgabe
     */
    public RendererJ2d(int width, int height) {
        super(width, height);
        this.width = width;
        this.height = height;
        this.offsetX = width >> 1;
        this.offsetY = height >> 1;

        frame = new JFrame("Java2D Renderer");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setBackground(java.awt.Color.WHITE);
        frame.setVisible(true);

        frame.createBufferStrategy(2);
        bs = frame.getBufferStrategy();
    }

    @Override
    public void putPixel(Pixel pixel) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(java.awt.Color.BLACK);
        g.fillRect(offsetX + (int) pixel.x, (int) (-pixel.y) + offsetY, 1, 1);
        g.dispose();
    }

    @Override
    public void putPixel(Pixel pixel, cga.framework.renderer.Color color) {
        Graphics g = bs.getDrawGraphics();
        g.setColor(color.color);
        g.fillRect(offsetX + (int) pixel.x, (int) (-pixel.y) + offsetY, 1, 1);
        g.dispose();
    }

    @Override
    public void show() {
        bs.show();
        bs.getDrawGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }

    /**
     * Fügt dem JFrame der Ausgabe einen MouseListener hinzu, mit dem Interaktionen implementiert werden können.
     * @param mouseAdapter der MouseListener
     */
    public void addMouseListener(MouseAdapter mouseAdapter) {
        frame.addMouseListener(mouseAdapter);
        frame.addMouseMotionListener(mouseAdapter);
        frame.addMouseWheelListener(mouseAdapter);
    }

    /**
     * Fügt dem JFrame einen KeyListener hinzu, mit dem Interaktionen über das Keyboard implementiert werden können.
     * @param keyAdapter der KeyListener
     */
    public void addKeyListener(KeyAdapter keyAdapter) {
        frame.addKeyListener(keyAdapter);
    }
}
