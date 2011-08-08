package example;

import cga.Framework;
import cga.framework.math.Vector3d;
import cga.framework.scenegraph.Translation;
import cga.framework.shape.Line3d;
import cga.framework.shape.Point2d;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class TestMain extends Framework {

    private Translation translation;

    public TestMain() {
        super(800, 800);
        renderer.addKeyListener(new MyKeyListener());
        translation = new Translation(0, 0, 0);
        //translation.setInterpolationX(new LinearInterpolation(-500, 500, 200, true));
        //translation.setInterpolationY(new LinearInterpolation(0, 150, 300, true));
        scenegraph.addTransformation(translation);


        initGraph();
    }

    @Override
    public void initGraph() {
        // add(new Box3d(new Vector3d(0, 0, 0), 100, 100, 100));

//    for (double x = 0; x < 2 * Math.PI; x += 0.01) {
//      add(new Point2d(x * 50, 50 * Math.sin(x)));
//    }

        //add(new AnimatedSineCurve(1500, 1, 1));
        add(new Point2d(100, 0));
//    add(new Line3d(new Vector3d(400, 0, -0.5), new Vector3d(400, 400, -0.5)));
//    add(new Line3d(new Vector3d(0, 0, -0.5), new Vector3d(0, 400, -0.5)));
//    add(new Line3d(new Vector3d(0, 400, -0.5), new Vector3d(400, 400, -0.5)));
//    
//    add(new Line3d(new Vector3d(0, 0, -0.5), new Vector3d(0, 0, -400.5)));
//    add(new Line3d(new Vector3d(400, 0, -0.5), new Vector3d(400, 0, -400.5)));
    }

    public static void main(String[] args) {
        TestMain example = new TestMain();
        example.start();
    }

    private class MyListener extends MouseAdapter {

        private int oldX = Integer.MIN_VALUE, oldY = Integer.MIN_VALUE;

        @Override
        public void mouseMoved(MouseEvent e) {
            if (oldX == Integer.MIN_VALUE || oldY == Integer.MIN_VALUE) {
                oldX = e.getPoint().x;
                oldY = e.getPoint().y;
            }

            int diffX = e.getPoint().x - oldX;
            int diffY = e.getPoint().y - oldY;
            double transX = 0;
            double transY = 0;

            if (diffX > 0) {
                transX = -0.1;
            } else if (diffX < 0) {
                transX = 0.1;
            }

            if (diffY > 0) {
                transY = -0.1;
            } else if (diffX < 0) {
                transY = 0.1;
            }

            translation.changeX(transX);
            translation.changeY(transY);
        }
    }

    private class MyKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                translation.changeX(1);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                translation.changeX(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                translation.changeY(1);
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                translation.changeY(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_A) {
                translation.changeZ(-1);
            } else if (e.getKeyCode() == KeyEvent.VK_Y) {
                translation.changeZ(1);
            }

        }
    }
}
