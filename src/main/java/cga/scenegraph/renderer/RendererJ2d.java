package cga.scenegraph.renderer;

import cga.scenegraph.camera.CVPoint;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class RendererJ2d extends Renderer {
  
  private JFrame frame;
  private BufferStrategy bs;
  private int width;
  private int height;
  private int offsetX;
  private int offsetY;
  
  public RendererJ2d(int width, int height) {
    super(width, height);
    this.width = width;
    this.height = height;
    this.offsetX = width >> 1;
    this.offsetY = height >> 1;
    
    frame = new JFrame("Java2D Renderer");
    frame.setSize(width, height);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.setBackground(Color.WHITE);
    frame.setVisible(true);
    
    frame.createBufferStrategy(2);
    bs = frame.getBufferStrategy();
  }
  
  @Override
  public void putPixel(Pixel pixel) {
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(offsetX + (int) pixel.x, (int) (-pixel.y) + offsetY, 1, 1);
    g.dispose();
  }
  
  @Override
  public void show() {
    bs.show();
    bs.getDrawGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
  }
  
  @Override
  public Pixel toPixel(CVPoint cvPoint) {
    return new Pixel((int) Math.round(cvPoint.x * width), (int) Math.round(cvPoint.y * height));
  }
  
  public void addMouseListener(MouseAdapter mouseAdapter) {
    frame.addMouseListener(mouseAdapter);
    frame.addMouseMotionListener(mouseAdapter);
    frame.addMouseWheelListener(mouseAdapter);
  }
  
  public void addKeyListener(KeyAdapter keyAdapter) {
    frame.addKeyListener(keyAdapter);
  }
}
