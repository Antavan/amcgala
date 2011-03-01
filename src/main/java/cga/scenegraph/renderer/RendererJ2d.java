package cga.scenegraph.renderer;


import javax.swing.*;
import java.awt.*;

public class RendererJ2d extends Renderer {
    private JFrame frame;
    private RenderPanel panel;


    public RendererJ2d(int width, int height) {
        super(width, height);

        frame = new JFrame("Java2D Renderer");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new RenderPanel(width, height);
        frame.setContentPane(panel);

        frame.setVisible(true);
    }

    @Override
    public void putPixel(Pixel pixel) {
        Graphics g = panel.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect((int) pixel.x, (int) pixel.y, 1, 1);
    }

    @Override
    public void show() {
        panel.swap();
    }

    private class RenderPanel extends JPanel {
        private Image backBuffer, frontBuffer;
        private Graphics drawingGraphics;

        public RenderPanel(int width, int height) {
            setSize(width, height);
            setPreferredSize(getSize());

            backBuffer = createImage(width, height);
            frontBuffer = createImage(width, height);
        }

        public Graphics getGraphics() {
            if (drawingGraphics == null) {
                swap();
            }
            return drawingGraphics;
        }

        @Override
        public void paint(Graphics g) {
            if (frontBuffer != null) {
                g.drawImage(frontBuffer, 0, 0, null);
            }
        }


        public void swap() {
            if (drawingGraphics == null || frontBuffer == null || backBuffer == null) {
                backBuffer = createImage(width, height);
                frontBuffer = createImage(width, height);
                drawingGraphics = backBuffer.getGraphics();
                drawingGraphics.setColor(Color.WHITE);
                drawingGraphics.fillRect(0, 0, width, height);
            }
            Image tmp = backBuffer;
            backBuffer = frontBuffer;
            frontBuffer = tmp;
            drawingGraphics = backBuffer.getGraphics();
            drawingGraphics.setColor(Color.WHITE);
            drawingGraphics.fillRect(0, 0, width, height);
            repaint();
        }
    }
}
