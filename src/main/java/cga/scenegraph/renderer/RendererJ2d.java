package cga.scenegraph.renderer;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class RendererJ2d extends Renderer {
    private JFrame frame;
    private BufferStrategy bufferStrategy;
    private Graphics g;

    public RendererJ2d(int width, int height) {
        super(width, height);
        frame = new JFrame("Java2D Renderer");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        bufferStrategy = frame.getBufferStrategy();

        g = bufferStrategy.getDrawGraphics();
    }

    @Override
    public void putPixel(Pixel pixel) {
        g.setColor(Color.BLACK);
        g.fillRect((int) pixel.x, (int) pixel.y, 1, 1);
    }

    @Override
    public void show() {
        bufferStrategy.show();
    }
}
