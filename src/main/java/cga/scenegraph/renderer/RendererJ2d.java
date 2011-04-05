package cga.scenegraph.renderer;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class RendererJ2d extends Renderer {
  private JFrame frame;
  private BufferStrategy bs;


  public RendererJ2d(int width, int height) {
    super(width, height);

    frame = new JFrame("Java2D Renderer");
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setBackground(Color.white);
    frame.setVisible(true);

    frame.createBufferStrategy(2);
    bs = frame.getBufferStrategy();
  }

  @Override
  public void putPixel(Pixel pixel) {
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect((int) pixel.x, (int) pixel.y, 1, 1);
    g.dispose();
  }

  @Override
  public void show() {
    bs.show();
    bs.getDrawGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
  }

}
